# member-management
간단한 멤버관리

## 밸리데이션 커스터마이징 노트

- 제약 조건(`@Size`·`@NotEmpty`·`@Email`)의 메시지를 하드코딩 대신 `{Size.user.ten}` 같은 메시지 키로 선언했다.
- `WebMvcConfig`에서 `LocalValidatorFactoryBean`에 `ReloadableResourceBundleMessageSource`(`classpath:messages`, UTF-8)를 주입해 메시지를 `messages.properties`로 중앙 관리한다.
- 검증 실패는 필드 단위 에러를 담은 `ValidCustomException`(`@ResponseStatus(BAD_REQUEST)`)로 승격해 400 응답으로 통일한다.
- 검증 자체는 `ValidationTest`·`MemberAcceptanceTest`(인수 테스트)로 회귀를 보장한다.

## URI

| 메소드 | 경로                      | 설명      |
| ------ | ------------------------- | --------- |
| POST   | /members                | 사용자등록  |
| GET   | /members					       | 목록조회    |
| GET    | /members/{id}           | 상세조회    |

## 개발 환경

- OS - Ubuntu 18.04.1 LTS
- IDE - IntelliJ
- Language - Java 8
- Framework - Spring boot 2.0.6
- Database - H2
