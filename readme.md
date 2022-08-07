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
