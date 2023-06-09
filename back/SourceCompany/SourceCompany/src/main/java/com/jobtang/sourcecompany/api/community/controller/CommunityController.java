package com.jobtang.sourcecompany.api.community.controller;


import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.community.service.CommunityService;
import com.jobtang.sourcecompany.api.exception.customerror.CustomException;
import com.jobtang.sourcecompany.api.exception.customerror.ErrorCode;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@CrossOrigin(originPatterns = "http://k8b107.p.ssafy.io")
//@CrossOrigin(originPatterns = "http://comapny-source.com")
@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
@Api("게시판 API")
public class CommunityController {
  private final CommunityService communityService;
  // 테스트 용 유저 가져오기
  private final JwtService jwtService;

  /**
   *
   * 마이페이지 조회
   *
   */

  /**
   *  조회수 추가하는 api
   */
  @ApiOperation(
          value = "게시글 조회수 올리기",
          notes = "조회수만 올리기 , 올라간 조회수   리턴"

  )
  @PostMapping("/add/view/{communityId}")
  public ResponseEntity<?> addViewCommunity( @PathVariable Long communityId) {

    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    int response = communityService.addViewCommunity(communityId);
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * 전체 검색
   *
   */
  /**
   * /community/all/search?type={type}&content={content}&free_size={free_size}&free_page={free_page}&corp_size={corp_size}&corp_page={corp_page} GET
   * 전체 게시판의 글들을 검색하는 메소드
   * is_active ==0 인 것들은 거르는 것 필요
   * type == (글쓴이, 내용 ,제목) 으로 거르기
   */
  @ApiOperation(
          value = "전체 게시글 검색",
          notes = "전체 게시글들을 검색, 기업과 자유게시판을 나눠서 리턴"

  )
  @GetMapping("/all/search")
  public ResponseEntity<?> searchAllCommunity(@RequestParam String content, @RequestParam String type,
                                               @ApiParam(value = "사이즈", required = true, defaultValue = "5", example = "5") @RequestParam(value = "size", required = true, defaultValue = "20") Integer size,
                                               @ApiParam(value = "페이지", required = true, defaultValue = "0", example = "0") @RequestParam(value = "page", required = true, defaultValue = "0") Integer page
  ) {
    Pageable free_pageable = PageRequest.of(page, size);

    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    PagingCommunityResponse response = communityService.searchCommunity("all", content, type, free_pageable);

    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

  // view : 조회수순
  // likes : 좋아요 순

  /**
   * /community/randing GET
   * // 랜딩 게시판을 리턴해주는 메소드
   * // 기업  / 자유 게시판의  최근 게시글
   * // 기업  / 자유 게시판의  어제자 조회수 높은 게시글
   */
  @ApiOperation(
          value = "게시글 랜딩 페이지",
          notes = "기업 및 자유게시판들의 랜딩게시판 조회"

  )
  @GetMapping("/randing")
  public ResponseEntity<?> readRandingCommunity(@ApiParam(value = "인기글 컷 추천수", required = true, defaultValue = "10", example = "10") @RequestParam(value = "standard", required = true, defaultValue = "10") Integer standard,
                                                @ApiParam(value = "리턴할 게시글 수", required = true, defaultValue = "8", example = "8") @RequestParam(value = "pageCnt", required = true, defaultValue = "8") Integer pageCnt) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    Pageable pageable = PageRequest.of( 0, pageCnt);
    ReadRandingCommunityResponse response = communityService.readRandingCommunity(standard , pageable);
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

  /**
   * 기업 메소드들 -----------------------------------------------------------------------------------------------

   */
  @ApiOperation(
          value = "기업분석 게시글 작성",
          notes = "현재 로그인한 유저 명의로 기업분석 게시글 작성"
  )
  @PostMapping("/corp")
  public ResponseEntity<?> createCorpCommunity(@RequestHeader("Authorization") String token ,@RequestBody CreateCommunityRequest createCommunityRequest) throws Exception {
    /*
    jwt token을 통해 User 객체 가져오는 코드로 대체
    User user = token.getLoginedUser();
     */
//    User user = userRepository.findById(10L).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    Long userId = jwtService.userPkByToken(token);

    HashMap<String, Object> result = new HashMap<>();
    HttpHeaders headers = new HttpHeaders();
    Long communityId =communityService.createCommunity("기업",userId, createCommunityRequest);
    result.put("data", communityId);
    return new ResponseEntity<>(result, HttpStatus.CREATED);

  }

  /**
   * /community/corp/{community_id} GET
   * 기업 분석 게시판을 상세조회하는 메소드
   * redis에 조회수를 추가해야함
   */
  @ApiOperation(
          value = "기업 분석 게시글 상세조회",
          notes = "해당 게시판의 detail한 정보와 달린 댓글들을 리턴해주고 , 조회수를 늘려주는 메소드"
  )
  @GetMapping("/corp/{communityId}")
  public ResponseEntity<?> findCorpCommunityDetail(@RequestHeader HttpHeaders header , @PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    // HTTP 요청 헤더에서 'Auth' 항목이 있는지 확인
    String token = header.getFirst("Authorization");
    Long userId = 0L;
    if (token != null && !token.isEmpty()) {
      // 'Auth' 헤더가 존재하고 값이 비어있지 않은 경우
      // TODO: 헤더 값을 이용한 작업 수행
      userId = jwtService.userPkByToken(token);
    }
    ReadCommunityDetailResponse response = communityService.readCommunityDetail(userId, "기업", communityId);

    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }


  /**
   * /community/corp/search?type={type}&content={content} GET
   * 기업 분석 게시판의 글들을 검색하는 메소드
   * is_active ==0 인 것들은 거르는 것 필요
   * type == (글쓴이, 내용 ,제목) 으로 거르기
   */
  @ApiOperation(
          value = "기업 분석 게시글 전체조회",
          notes = "기업 분석 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/corp/search")
  public ResponseEntity<?> searchCorpCommunity(@RequestParam String content, @RequestParam String type,
                                               @ApiParam(value = "페이지 번호", required = true, defaultValue = "0", example = "0") @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                               @ApiParam(value = "페이지 크기", required = true, defaultValue = "5", example = "5") @RequestParam(value = "size", required = true, defaultValue = "20") Integer size) {
    Pageable pageable = PageRequest.of(page, size);

    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    PagingCommunityResponse response = communityService.searchCommunity("기업", content, type, pageable);

    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * /community/corp GET
   * 기업 분석 게시판을 전체조회하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 전체조회",
          notes = "기업 분석 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/corp")
  public ResponseEntity<?> findAllCorpCommunity(
          @ApiParam(value = "페이지 번호", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
          @ApiParam(value = "페이지 크기", required = true, defaultValue = "5", example = "5")  @RequestParam(value = "size", required = true, defaultValue = "20") Integer size,
          @ApiParam(value = "정렬 방식", required = true, defaultValue = "all", example = "all")  @RequestParam(value = "sort", required = true, defaultValue = "All") String sort

  ) {
    Sort sorting;
    if(sort.equals("all")){
      sorting = Sort.by(Sort.Direction.DESC,"createdDate");
    }
    else if(sort.equals("view")){
      sorting = Sort.by(Sort.Direction.DESC,"totalView");
    } else if (sort.equals("likes")) {
      sorting = Sort.by(Sort.Direction.DESC,"likesCnt");
    } else{
      throw  new CustomException(ErrorCode.WRONG_INPUT_DATA);
    }
    Pageable pageable = PageRequest.of(page, size,sorting);
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    System.out.println(pageable);
    PagingCommunityResponse response = communityService.readAllCommunity("기업",sort , pageable);
    result.put("data", response.getReadAllCommunityResponses());
    result.put("totalPage" , response.getPageTotal());
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * /community/corp/{communityId} PUT
   * 기업 분석 게시글을 수정하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 수정",
          notes = "해당 게시글을 수정하는 메소드"
  )
  @PutMapping("/corp")
  public ResponseEntity<?> updateCorpCommunity(@RequestHeader("Authorization") String token ,@RequestBody UpdateCommunityRequest updateCommunityRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    Long userId = jwtService.userPkByToken(token);
    result.put("data", communityService.updateCommunity(userId ,updateCommunityRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }

  /**
   * /community/corp/{communityId} DELETE
   * 기업 분석 게시글을 삭제하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 삭제",
          notes = "해당 게시글을 삭제하는 메소드"
  )
  @DeleteMapping("/corp/{communityId}")
  public ResponseEntity<?> removeCorpCommunity(@RequestHeader("Authorization") String token ,@PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    Long userId =jwtService.userPkByToken(token);
    communityService.deleteCommunity(userId ,communityId);
    result.put("data", "success");
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }

  /**
   *
   *  기업 메소드들 -----------------------------------------------------------------------------------------------
   */


  /**
<<<<<<< HEAD
   * 자유 게시판 메소드들 -----------------------------------------------------------------------------------------------
=======
   *
   *  자유 게시판 메소드들 -----------------------------------------------------------------------------------------------
>>>>>>> 9c9a9bc595360ecacd476fc640356fa6232f797c
   */

  @ApiOperation(
          value = "자유 게시글 작성",
          notes = "현재 로그인한 유저 명의로 자유 게시글 작성"
  )
  @PostMapping("/free")
  public ResponseEntity<?> createFreeCommunity(@RequestHeader("Authorization") String token , @RequestBody CreateCommunityRequest createCommunityRequest) throws Exception {
    /*
    jwt token을 통해 User 객체 가져오는 코드로 대체
    User user = token.getLoginedUser();
     */
//    User user = userRepository.findById(10L).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    Long userId= jwtService.userPkByToken(token);

    HashMap<String, Object> result = new HashMap<>();
    HttpHeaders headers = new HttpHeaders();

    Long communityId = communityService.createCommunity("자유", userId, createCommunityRequest);

    result.put("data", communityId);
    return new ResponseEntity<>(result, HttpStatus.CREATED);

  }

  /**
   * /community/free/{community_id} GET
   * 자유 게시판을 상세조회하는 메소드
   * redis에 조회수를 추가해야함
   */
  @ApiOperation(
          value = "자유 게시글 상세조회",
          notes = "해당 게시판의 detail한 정보와 달린 댓글들을 리턴해주고 , 조회수를 늘려주는 메소드"
  )
  @GetMapping("/free/{communityId}")
  public ResponseEntity<?> findFreeCommunityDetail(@RequestHeader HttpHeaders header , @PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    // HTTP 요청 헤더에서 'Auth' 항목이 있는지 확인
    String token = header.getFirst("Authorization");
    Long userId = 0L;
    if (token != null && !token.isEmpty()) {
      // 'Auth' 헤더가 존재하고 값이 비어있지 않은 경우
      // TODO: 헤더 값을 이용한 작업 수행
      userId = jwtService.userPkByToken(token);
    }
    ReadCommunityDetailResponse response = communityService.readCommunityDetail(userId ,"자유", communityId);

    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }


  /**
   * /community/free/search?type={type}&content={content} GET
   * 자유 게시판의 글들을 검색하는 메소드
   * is_active ==0 인 것들은 거르는 것 필요
   * type == (글쓴이, 내용 ,제목) 으로 거르기
   */
  @ApiOperation(
          value = "자유 게시글 전체조회",
          notes = "자유 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/free/search")
  public ResponseEntity<?> searchFreeCommunity(@RequestParam String content, @RequestParam String type,
                                               @ApiParam(value = "페이지 번호", required = true, defaultValue = "0", example = "0") @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                               @ApiParam(value = "페이지 크기", required = true, defaultValue = "5", example = "5") @RequestParam(value = "size", required = true, defaultValue = "20") Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    PagingCommunityResponse response = communityService.searchCommunity("자유", content, type, pageable);
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * /community/free GET
   * 자유 게시판을 전체조회하는 메소드
   */
  @ApiOperation(
          value = "자유 게시글 전체조회",
          notes = "자유 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/free")
  public ResponseEntity<?> findAllFreeCommunity(
          @ApiParam(value = "페이지 번호", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
          @ApiParam(value = "페이지 크기", required = true, defaultValue = "5", example = "5")  @RequestParam(value = "size", required = true, defaultValue = "20") Integer size,
          @ApiParam(value = "정렬 방식", required = true, defaultValue = "all", example = "all , view , likes")  @RequestParam(value = "sort", required = true, defaultValue = "all") String sort) {
    Sort sorting;
    if(sort.equals("all")){
      sorting = Sort.by(Sort.Direction.DESC,"createdDate");
    }
    else if(sort.equals("view")){
      sorting = Sort.by(Sort.Direction.DESC,"totalView");
    } else if (sort.equals("likes")) {
      sorting = Sort.by(Sort.Direction.DESC,"likesCnt");
    } else{
      throw  new CustomException(ErrorCode.WRONG_INPUT_DATA);
    }
    Pageable pageable = PageRequest.of(page, size,sorting);
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    PagingCommunityResponse response = communityService.readAllCommunity("자유",sort , pageable);
    result.put("data", response.getReadAllCommunityResponses());
    result.put("totalPage" , response.getPageTotal());
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * /community/free/{communityId} PUT
   * 자유 게시글 수정하는 메소드
   */
  @ApiOperation(
          value = "자유 게시글 수정",
          notes = "해당 게시글을 수정하는 메소드"
  )
  @PutMapping("/free")
  public ResponseEntity<?> updateFreeCommunity(@RequestHeader("Authorization") String token ,@RequestBody UpdateCommunityRequest updateCommunityRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    Long userId = jwtService.userPkByToken(token);
    result.put("data", communityService.updateCommunity(userId, updateCommunityRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }

  /**
   * /community/free/{communityId} DELETE
   * 자유 게시판을 삭제하는 메소드
   */
  @ApiOperation(
          value = "자유 게시글 삭제",
          notes = "해당 게시글을 삭제하는 메소드"
  )
  @DeleteMapping("/free/{communityId}")
  public ResponseEntity<?> removeFreeCommunity(@RequestHeader("Authorization") String token ,@PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    Long userId = jwtService.userPkByToken(token);
    communityService.deleteCommunity(userId ,communityId);
    result.put("data", "success");
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }
  /**
   *
   *  자유 게시판 메소드들 -----------------------------------------------------------------------------------------------
   */


  /**
   * test
   */
//  @GetMapping("/test")
//  public ResponseEntity<?> Testtest() {
//    HttpHeaders headers = new HttpHeaders();
//    HashMap<String, Object> result = new HashMap<>();
//    communityService.schedule();
//    result.put("data", "success");
//    return new ResponseEntity<>(result, headers, HttpStatus.OK);
//
//  }
}
