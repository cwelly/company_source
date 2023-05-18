const asdasd = "<수익성분석>\n1. 개념\n    - 손익확대효과(재무레버리지효과)를 가져다 주는 타인자본 의존도\n    - 어느 정도 타인자본에 의존하고 있는지를 측정하는 비율, 부채성비율(안전성비율)\n    1) 부채비율 : 타인자본(부채)을 자기자본으로 나눈 비율, 일반적으로 100% 이하를 표준비율로 보고있으며, 이는 여신자측의 채권회수의 안전성만 고려한 것\n    2) 자기자본비율 : 총자본 중에서 자기자본이 차지하고 있는 비중을 나타내는 비율, 기업의 안전성을 측정하는 비율로 이용\n    3) 차입금의존도 : 총자본 중에서 차입금이 차지하는 비중, 기업의 안전성을 측정하는 비율로 이용\n        - 차입금 대 매출액 비율 : 차입금이 매출액에 비해 얼마인가 나타내는 지표\n    4) 차입금평균이자율 : 장단기차입금과 사채 등과 같은 이자발생부채에 대한 이자비용의 비율(분모는 기초와 기말 금액의 평균치 사용)\n    5) 이자보상비율 : 영업이익이 타인자본을 사용하여 발생하는 이자비용의 몇 배에 해당하는지를 나타내는 비율, 기업의 부채수용능력을 보여줌\n    6) EBITDA / 이자비용비율과 고정재무비보상비율\n        - EBITDA : 이자 및 법인세 차감전 순이익에 감가상각비 및 무형자산 상각비를 합친것(한국은행의 경우 EBIT = 세전순이익 + 이자비용)\n        - 고정재무비보상비율 : 이자보상비율을 확대시킨 비율, 영업이익을 이자비용 및 고정재무비의 합으로 나눈 비율, 임차나 리스를 많이 이용하는 기업의 이자비용 및 고정재무비 지급능력을 측정하는 데 유용한 비율\n2. 해석법\n    1) 부채비율 : 산업평균과 비교, ex) 국내 제조업 평균 = 100.8%\n    2) 자기자본비율 : 국제평균과 비교 ex) 한국 49.8%, 미국 42.3%, 일본 41.0%, 독일 29.3%\n    3) 차입금의존도 : 산업평균 추이\n        - 차입금 대 매출액 비율 : 1 초과 = 기업의 신용상태가 극히 불량(IMF 구제금융 신청 당시 1을 초과하는 기업들이 다수 존재)\n    4) 차입금평균이자율 : 산업평균 비교, 추이\n        - ex) 제조업 96~98 : 11.22%, 10.59%, 13.51%\n        - 2000년대 중반      : 7.6%, 6.8%, 5.8%, 7.2%\n    5) 이자보상비율 : 일반적으로 적어도 3배 정도가 되어야 정상적인 지급활동 가능\n    6) EBIDTA/이자비용비율 ex) 9.1배\n        - 고정재무비보상비율 \n3. 계산법\n    1) 부채비율 = 부채/자기자본 * 100\n    2) 자기자본비율 = 자기자본/총자본 * 100\n    3) 차입금의존도 = (장단기 차입금 + 사채)/총자본 * 100\n        - 차입금 대 매출액 비율 = 차입금/매출액\n    4) 차입금평균이자율 = 이자비용/(장단기차입금+사채)의 평잔 * 100\n    5) 이자보상비율 = 영업이익/이자비용 > (영업이익/총자본)/(이자비용/차입금) = 총자본영업이익률/차입금평균이자율\n    6) EBITDA/이자비용비율 = EBITDA/이자비용 = (세전순이익 + 이자비용 + 감가상각비 및 무형자산상각비) / 이자비용\n        - 고정재무비보상비율 = 영업이익/(이자비용 + 고정재무비)\n4. 출처\n    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"

// const InfoContent = {
//   title: "수익성분석",
//   smallTitleOne: "1. 개념",
//   smallTitleTwo: "2. 해석법",
//   smallTitleThree: "3. 계산법",
//   smallTitleFour: "4. 출처",
//   contentOne: "- 손익확대효과(재무레버리지효과)를 가져다 주는 타인자본 의존도\n    - 어느 정도 타인자본에 의존하고 있는지를 측정하는 비율, 부채성비율(안전성비율)\n    1) 부채비율 : 타인자본(부채)을 자기자본으로 나눈 비율, 일반적으로 100% 이하를 표준비율로 보고있으며, 이는 여신자측의 채권회수의 안전성만 고려한 것\n    2) 자기자본비율 : 총자본 중에서 자기자본이 차지하고 있는 비중을 나타내는 비율, 기업의 안전성을 측정하는 비율로 이용\n    3) 차입금의존도 : 총자본 중에서 차입금이 차지하는 비중, 기업의 안전성을 측정하는 비율로 이용\n        - 차입금 대 매출액 비율 : 차입금이 매출액에 비해 얼마인가 나타내는 지표\n    4) 차입금평균이자율 : 장단기차입금과 사채 등과 같은 이자발생부채에 대한 이자비용의 비율(분모는 기초와 기말 금액의 평균치 사용)\n    5) 이자보상비율 : 영업이익이 타인자본을 사용하여 발생하는 이자비용의 몇 배에 해당하는지를 나타내는 비율, 기업의 부채수용능력을 보여줌\n    6) EBITDA / 이자비용비율과 고정재무비보상비율\n        - EBITDA : 이자 및 법인세 차감전 순이익에 감가상각비 및 무형자산 상각비를 합친것(한국은행의 경우 EBIT = 세전순이익 + 이자비용)\n        - 고정재무비보상비율 : 이자보상비율을 확대시킨 비율, 영업이익을 이자비용 및 고정재무비의 합으로 나눈 비율, 임차나 리스를 많이 이용하는 기업의 이자비용 및 고정재무비 지급능력을 측정하는 데 유용한 비율\n",
//   contentTwo: " 1) 부채비율 : 산업평균과 비교, ex) 국내 제조업 평균 = 100.8%\n    2) 자기자본비율 : 국제평균과 비교 ex) 한국 49.8%, 미국 42.3%, 일본 41.0%, 독일 29.3%\n    3) 차입금의존도 : 산업평균 추이\n        - 차입금 대 매출액 비율 : 1 초과 = 기업의 신용상태가 극히 불량(IMF 구제금융 신청 당시 1을 초과하는 기업들이 다수 존재)\n    4) 차입금평균이자율 : 산업평균 비교, 추이\n        - ex) 제조업 96~98 : 11.22%, 10.59%, 13.51%\n        - 2000년대 중반      : 7.6%, 6.8%, 5.8%, 7.2%\n    5) 이자보상비율 : 일반적으로 적어도 3배 정도가 되어야 정상적인 지급활동 가능\n    6) EBIDTA/이자비용비율 ex) 9.1배\n        - 고정재무비보상비율 \n",
//   contentThree: "1) 부채비율 = 부채/자기자본 * 100\n    2) 자기자본비율 = 자기자본/총자본 * 100\n    3) 차입금의존도 = (장단기 차입금 + 사채)/총자본 * 100\n        - 차입금 대 매출액 비율 = 차입금/매출액\n    4) 차입금평균이자율 = 이자비용/(장단기차입금+사채)의 평잔 * 100\n    5) 이자보상비율 = 영업이익/이자비용 > (영업이익/총자본)/(이자비용/차입금) = 총자본영업이익률/차입금평균이자율\n    6) EBITDA/이자비용비율 = EBITDA/이자비용 = (세전순이익 + 이자비용 + 감가상각비 및 무형자산상각비) / 이자비용\n        - 고정재무비보상비율 = 영업이익/(이자비용 + 고정재무비)\n",
//   contentFour: "1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
// }

const InfoContent = {
  "analysisInfo": {
    "analysis_name": "수익성분석",
    "analysis_id": "108",
    "analysis_source": "1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저",
    "analysis_detail": [
      {
        "category": "1. 개념",
        "value": [
          {
            "title": "",
            "content": "- 손익확대효과(재무레버리지효과)를 가져다 주는 타인자본 의존도"
          },
          {
            "title": "",
            "content": "- 어느 정도 타인자본에 의존하고 있는지를 측정하는 비율, 부채성비율(안전성비율)"
          },
          {
            "title": "1) 부채비율",
            "content": ""
          },
          {
            "title": "2) 자기자본비율",
            "content": "총자본 중에서 자기자본이 차지하고 있는 비중을 나타내는 비율, 기업의 안전성을 측정하는 비율로 이용"
          },
          {
            "title": "3) 차입금의존도",
            "content": " 총자본 중에서 차입금이 차지하는 비중, 기업의 안전성을 측정하는 비율로 이용\n        - 차입금 대 매출액 비율 : 차입금이 매출액에 비해 얼마인가 나타내는 지표\n"
          },
          {
            "title": "4) 차입금평균이자율",
            "content": "산업평균 비교, 추이\n        - ex) 제조업 96~98 : 11.22%, 10.59%, 13.51%\n        - 2000년대 중반      : 7.6%, 6.8%, 5.8%, 7.2%\n"
          },
          {
            "title": "5) 이자보상비율",
            "content": "영업이익이 타인자본을 사용하여 발생하는 이자비용의 몇 배에 해당하는지를 나타내는 비율, 기업의 부채수용능력을 보여줌\n"
          },
          {
            "title": "6) EBIDTA/이자비용비율",
            "content": "- EBITDA : 이자 및 법인세 차감전 순이익에 감가상각비 및 무형자산 상각비를 합친것(한국은행의 경우 EBIT = 세전순이익 + 이자비용)\n        - 고정재무비보상비율 : 이자보상비율을 확대시킨 비율, 영업이익을 이자비용 및 고정재무비의 합으로 나눈 비율, 임차나 리스를 많이 이용하는 기업의 이자비용 및 고정재무비 지급능력을 측정하는 데 유용한 비율\n"
          },
        ]
      },
      {
        "category": "2. 해석법",
        "value": [
          {
            "title": "",
            "content": "- 손익확대효과(재무레버리지효과)를 가져다 주는 타인자본 의존도\n "
          },
          {
            "title": "",
            "content": "- 어느 정도 타인자본에 의존하고 있는지를 측정하는 비율, 부채성비율(안전성비율)\n"
          },
          {
            "title": "1) 부채비율",
            "content": "타인자본(부채)을 자기자본으로 나눈 비율, 일반적으로 100% 이하를 표준비율로 보고있으며, 이는 여신자측의 채권회수의 안전성만 고려한 것\n"
          },
          {
            "title": "2) 자기자본비율",
            "content": "총자본 중에서 자기자본이 차지하고 있는 비중을 나타내는 비율, 기업의 안전성을 측정하는 비율로 이용\n"
          },
          {
            "title": "3) 차입금의존도",
            "content": " 총자본 중에서 차입금이 차지하는 비중, 기업의 안전성을 측정하는 비율로 이용\n        - 차입금 대 매출액 비율 : 차입금이 매출액에 비해 얼마인가 나타내는 지표\n"
          },
          {
            "title": "4) 차입금평균이자율",
            "content": "산업평균 비교, 추이\n        - ex) 제조업 96~98 : 11.22%, 10.59%, 13.51%\n        - 2000년대 중반      : 7.6%, 6.8%, 5.8%, 7.2%\n"
          },
          {
            "title": "5) 이자보상비율",
            "content": "영업이익이 타인자본을 사용하여 발생하는 이자비용의 몇 배에 해당하는지를 나타내는 비율, 기업의 부채수용능력을 보여줌\n"
          },
          {
            "title": "6) EBIDTA/이자비용비율",
            "content": "- EBITDA : 이자 및 법인세 차감전 순이익에 감가상각비 및 무형자산 상각비를 합친것(한국은행의 경우 EBIT = 세전순이익 + 이자비용)\n        - 고정재무비보상비율 : 이자보상비율을 확대시킨 비율, 영업이익을 이자비용 및 고정재무비의 합으로 나눈 비율, 임차나 리스를 많이 이용하는 기업의 이자비용 및 고정재무비 지급능력을 측정하는 데 유용한 비율\n"
          },
        ]
      },
      {
        "category": "3. 계산법",
        "value": [
          {
            "title": "",
            "content": "- 손익확대효과(재무레버리지효과)를 가져다 주는 타인자본 의존도\n "
          },
          {
            "title": "",
            "content": "- 어느 정도 타인자본에 의존하고 있는지를 측정하는 비율, 부채성비율(안전성비율)\n"
          },
          {
            "title": "1) 부채비율",
            "content": "타인자본(부채)을 자기자본으로 나눈 비율, 일반적으로 100% 이하를 표준비율로 보고있으며, 이는 여신자측의 채권회수의 안전성만 고려한 것\n"
          },
          {
            "title": "2) 자기자본비율",
            "content": "총자본 중에서 자기자본이 차지하고 있는 비중을 나타내는 비율, 기업의 안전성을 측정하는 비율로 이용\n"
          },
          {
            "title": "3) 차입금의존도",
            "content": " 총자본 중에서 차입금이 차지하는 비중, 기업의 안전성을 측정하는 비율로 이용\n        - 차입금 대 매출액 비율 : 차입금이 매출액에 비해 얼마인가 나타내는 지표\n"
          },
          {
            "title": "4) 차입금평균이자율",
            "content": "산업평균 비교, 추이\n        - ex) 제조업 96~98 : 11.22%, 10.59%, 13.51%\n        - 2000년대 중반      : 7.6%, 6.8%, 5.8%, 7.2%\n"
          },
          {
            "title": "5) 이자보상비율",
            "content": "영업이익이 타인자본을 사용하여 발생하는 이자비용의 몇 배에 해당하는지를 나타내는 비율, 기업의 부채수용능력을 보여줌\n"
          },
          {
            "title": "6) EBIDTA/이자비용비율",
            "content": "- EBITDA : 이자 및 법인세 차감전 순이익에 감가상각비 및 무형자산 상각비를 합친것(한국은행의 경우 EBIT = 세전순이익 + 이자비용)\n        - 고정재무비보상비율 : 이자보상비율을 확대시킨 비율, 영업이익을 이자비용 및 고정재무비의 합으로 나눈 비율, 임차나 리스를 많이 이용하는 기업의 이자비용 및 고정재무비 지급능력을 측정하는 데 유용한 비율\n"
          },
        ]
      },
    ]
  }
}

export default InfoContent