## Getting Started

### 0. 프로젝트 구성

module-server 모듈과 module-data 모듈로 구성

module-server 모듈에는 api 패키지와 batch 패키지가 존재함.

module-sever 를 module-api 와 module-batch 모듈로 분리하고 싶었으나, 

In-memory DB 를 사용해야하는 제약사항으로 분리하지 못함. (docker-container 로 MySql 와 같은 DB 를 구성하면 나눌수 있었을거라고 생각)

module-data 모듈에는 dataSource 에 대한 정보와 Entity / Repository / domain service 가 존재


### 1. Spring Boot App Run

base 디렉토리에 있는 module-server-0.0.1-SNAPSHOT.jar 파일 실행

```
  java -jar module-server-0.0.1-SNAPSHOT.jar
```
App 이 기동되며 In-memory H2 DB 와 module-data 모듈 resource 에 있는 schema.sql 과 data.sql 스크립트 실행 (테이블 3개 생성 및 테스트 데이터 insert)


DB 콘솔 접속
아래 url 접속후, 캡쳐화면과 같이 정보를 입력
```
  http://localhost:4000/h2-console 
```
![image](https://user-images.githubusercontent.com/39793010/226915093-7c5fdc28-33e1-4c7d-bf76-41e803f9ba1c.png)


### 3. API 테스트  

Postman 으로 테스트 가능
```
  /module-server/api-test/search_api_test_collection.postman_collection.json - 해당 파일을 Postman 으로 import 후 사용 가능
  
  총 4개의 Positive Case, 7개의 Negative Case 에 대한 테스트가 작성되어 있음
  
```
API Spec - https://www.notion.so/Search-API-Docs-928b662c5c1a453b96be7050ed632fb9


### 4. 사용 외부 Library
1. Spring Boot Cloud Open Feign - 카카오, 네이버 외부 API 연동 위함
2. Spring Boot Cloud Slueth - Enhanced Loggin 위함
3. Spring Boot Validation - Parameter Validation 위함
4. Spotless - code formatting 위함
5. Lombok - boilerplate 코드 생성
