## Getting Started

### 0. 프로젝트 구성

module-server 모듈과 module-data 모듈로 구성

module-server 모듈에는 api 패키지와 batch 패키지가 존재함.

module-sever 를 module-api 와 module-batch 모듈로 분리하고 싶었으나 제약사항으로 패키지로 분리

module-data 모듈에는 dataSource 에 대한 정보와 Entity / Repository / domain service 가 존재

검색 Service API 호출시 프로세스는 다음과 같음

api 서버
1. SEARCH_WORD 테이블에 검색어 적재 (없는 경우)
2. SEARCH_WORD_HISTORY 테이블에 이력 적재
3. 카카오 API 호출
4. 3번이 실패하거나, 결과가 없는경우 NAVER API 호출
5. 응답

batch
1. 1분주기로 실행
2. SEARCH_BATCH 테이블에서 이전 BATCH 시작시간 get, 현재 BATCH 시작시간 write
3. 이전 BATCH 시작시간 < 검색이력 < 현재 BATCH 시작시간 인 이력들 count(*) ~ group by (검색어) - 이력테이블은 이력생성시간으로 INDEXING 되어있음.
4. SEARCH_WORD count 칼럼 업데이트 


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
Positive
1. [/search/blog] (query = 서울, sorting = null, pageNumber = null, pageSize = 1) - sorting, pageNumber 디폴트 Case
2. [/search/blog] (query = 서울, sorting = RECENCY, pageNumber = 2, pageSize = 10) - 최신순 데이터 조회 Case
3. [/search/blog] (query = 서울, sorting = ACCURACY, pageNumber = 2, pageSize = 10) - 정확도순 데이터 조회 Case
4. [/search/words] 검색어 상위 10개 조회 성공 - 검색순위 상위 10개 검색어/검색횟수 조회

Negative
1. [/search/blog] (query = 서울, sorting = WRONGSORT, pageNumber = 2, pageSize = 10) - ACCURACY / RECENCY 가 아닌 정렬조건 Case
2. [/search/blog] (query = 서울, sorting = ACCURACY, pageNumber = -1, pageSize = 10) - pageNumber invalid Case
3. [/search/blog] (query = 서울, sorting = ACCURACY, pageNumber = 1, pageSize = 100000) - pageSize invalid Case
4. [/search/blog] (query = null, sorting = ACCURACY, pageNumber = 1, pageSize = 10) - 검색어 null Case
5. [/search/blog] (query = blank, sorting = ACCURACY, pageNumber = 1, pageSize = 10) - 검색어 공백 Case
6. [/search/blog] (query = !!!!!!!!, sorting = ACCURACY, pageNumber = 1, pageSize = 1) - 데이터 존재하지 않는 Case (200 Ok + StatusCode EMPTY_RESULT)
7. [/search/blog] (query = longlonglong, sorting = ACCURACY, pageNumber = 1, pageSize = 1) - 검색어 길이 초과 Case 

API Spec - https://www.notion.so/Search-API-Docs-928b662c5c1a453b96be7050ed632fb9

### 4. Jmeter 성능테스트 결과
3000 Number of Threads(users) / 30초 / 동일한 검색조건 (키워드 - "서울") 아래와 같이 중간구간 기준 100 TPS 안정적으로 유지하는것 볼수 있었음
검색 횟수도 유실되지 않고 기존보다 +3000 결과적인 동기화 된것 확인함 
<img width="1087" alt="image" src="https://user-images.githubusercontent.com/39793010/226936451-1e885b8f-696d-41e3-8b15-1f205075a9b5.png">


### 5. 사용 외부 Library
1. Spring Boot Cloud Open Feign - 카카오, 네이버 외부 API 연동 위함
2. Spring Boot Cloud Slueth - Enhanced Loggin 위함
3. Spring Boot Validation - Parameter Validation 위함
4. Spotless - code formatting 위함
5. Lombok - boilerplate 코드 생성
