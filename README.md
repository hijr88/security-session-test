## 테스트
### Runner를 통해 user1, user2의 계정을 생성했다.  패스워드는 1234
* 브라우저 2개 준비 
  - http://localhost:8080 접속시 익명사용자 입니다. 출력
   
* http://localhost:8080/login user1,2 각각 로그인
  - http://localhost:8080 접속시 안녕하세요. user1님 출력
* http://localhost:8080/all 접속한 유저 목록 확인
* http://localhost:8080/delete user2의 세션을 만료 시킴
* user2의 브라우저에서 http://localhost:8080 접속시 http://localhost:8080/expire 이동 시킴
