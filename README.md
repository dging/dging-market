# 디깅마켓

디깅마켓은 CD, 바이닐, 카세트, LP 중고 음반 제품에 대해 **여러 거래 서비스를 제공하는 음반 중고 거래 플랫폼**입니다.

<div align="center">
  
  | 구분 | 디깅마켓 |
  |:---:|:---:|
  | 로고 | <img src="/backend/images/logo.png" width="144"> |
  | 상태 | **개발중** |
  | API 문서 | [링크](https://api.dev.dgingmarket.com/swagger-ui/index.html) |

</div>

<br/>

## 기능 요구사항

- 카카오, 네이버, 구글, 페이스북 SNS 계정을 이용해 회원가입을 진행합니다.
- 사용자는 하나의 상점을 생성하여 관리할 수 있습니다.
  - 상품을 등록 및 관리합니다.
  - 후기를 조회합니다.
  - 찜한 상품을 조회합니다.
  - 팔로잉 및 팔로워를 조회합니다.
  - 주문 내역을 조회 및 관리합니다.
- 다른 상점의 상품을 조회합니다.
- 다른 상점에 대해 팔로우, 신고할 수 있습니다.
  - 팔로우 시 해당 상점의 소식을 받을 수 있습니다.
- 상품을 결제합니다.
- 게시한 상품에 대해 판매자와 구매를 원하는 사용자가 1:1 채팅을 진행합니다.
<br/>

## 개발환경

### Backend
- Java 21
- Spring Boot 3.3
- Spring Security
- Gradle 8.7
- JPA + Querydsl
- Redis
- JUnit5 + Mockito
- Spring REST Docs + Swagger UI
<br/>

## 아키텍처
<br/>
