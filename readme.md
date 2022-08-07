# pringboot-mybatis-h2db-sample

해당문서는 markdown 형식으로 작성 됐습니다.
ref : [markdown guide](https://www.markdownguide.org/)


## 실행방법

## 사용된 기술
- SpringBoot
- mybatis
- lombock
- h2 Database

### SpringBoot

### Mybatis
Mybatis를 사용했지만 그 외의 다른 기술을 사용해도 상관 없음.

### Lombok

롬복이란? 쉽게 설명하면 getter setter 같이 매번 반복해서 작업해야 되는 코드들.
이런 boilerplate code(상용구 코드)를 사람이 매번 단순반복해서 코딩하는 것이 아니라
라이브러리가 대신 처리하도록 만들기 위함. 

ref : [Project Lombok](https://projectlombok.org/)

## h2 Database
ref : [h2 Database](https://www.h2database.com/)

---

개발 툴의 사용은 자유롭게 사용가능. 예) VSCode, Eclipse, Intellij 등
해당 문서는 가이드일 뿐.

---

### Test
1. Component 도 @Transactional 이 동작하는지 확인  
-> 동작한다. @Transactional 에 Exception 발생 시 Rollback 시키게 하니 Rollback 됨  
2. @Transactional 을 붙이지 않으면, 중간에 Exception이 발생해도 Commit 될까?  
-> DB Update 문 실행 후 Exception을 발생시켰는데도 Commit 되는 것 확인  
3. Transaction 의 isolation 레벨을 Isolation.READ_UNCOMMITTED 로 했을 때 정말 Commit 하지 않은 것을 읽을까?  
-> Commit하지 않은 정보를 읽어온다.  
4. @Transactional이 선언되지 않은 메서드에서 @Transactional이 선언된 메서드 호출시 Transaction이 적용될까?  
-> 주입받은 클래스에서는 Transaction이 적용된다.  
-> 동일한 클래스에 있는 Trnasactional이 붙은 메서드 호출 시에는 Transacion이 동작하지 않는다.  
5. REPEATABLE READ 격리 테스트 - 시나리오 5초 간격으로 Thread sleep을 걸어서 그 사이에 update 쿼리 실행 이 때 하나의 트랜잭션에 값이 변경되어서는 안된다.  
-> 하나의 @Transactional 에서는 조회 -> update -> 동일조회 해봤자 두번째 조회시 동일한 데이터가 조회되지 않음 
-> 하나의 다른 Transaction에서 수정한 데이터를 읽어오지 않는 것을 확인할 수 있음. 정말 똑같은 쿼리 실행 시 똑같은 데이터가 조회 됨
6. SERIALIZABLE 격리 테스트 - 시나리오 5초 간격으로 Thread sleep(X 조회 쿼리 Timeout을 걸어야 함) 을 걸어서 그 사이에 update 쿼리 실행 이 때 update 쿼리 테이블은 Lock이 걸려서 Update가 실행되어서는 안된다.  
-> slow 쿼리를 만들려면 시간이 너무 많이 걸려서 다른 DB에서 테스트 하는 것으로!  
7. Propagation RequiredNew
-> 개별 트랜잭션 단계로 전파

