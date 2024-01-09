# 도서 관리 프로그램 

### 🔗Deploy site

[실제 배포 사이트 바로가기](http://library-env.eba-wgbtarnw.ap-northeast-2.elasticbeanstalk.com/)

##  ✨ 프로젝트 소개
``RESTful``, ``Spring Security``,``CI/CD``를 적용한 도서관리 프로그램을 개발하였습니다.<br>
기간 : 2023.12.16 ~ 2023.12.21, 2024.01.08 ~ 2024.01.09 총 8일 소요

### Github Action과 AWS Elastic Beanstalk를 이용한 CI/CD 구현
Github에 push를 하면 바로 배포될 수 있도록 CI/CD를 구현하였습니다.

## 시스템 구성도
![시스템구성도](https://github.com/amung9914/book_management/assets/137124338/e78547a8-498a-408c-8d80-2aff703a9893)

## 🛠 Languages and Tools:
- JDK 17
- Spring Boot v3.2.0
- Spring Security v6.2.0
- MySQL v8.0.33
- MyBatis v3.5.14
- Spring Cloud AWS Starter v2.2.6
- Thymeleaf v6.3.1
- Thymeleaf Layout Dialect v3.3.0
- lombok v1.18.28
- Junit v5.10.1
- Bootstrap v4.1.3
- TinyMCE v6
- Jakarta Mail v2.0.2

## 💡 ERD : 
![image](https://github.com/amung9914/book_management/assets/137124338/2e2f51f0-e70c-406f-bc3e-519d1ed4bd61)

## 📌 구현 기능 : 

### SMTP 이메일 인증을 통한 회원가입
![회원가입](https://github.com/amung9914/book_management/assets/137124338/7fbaff3b-0911-4d82-840d-eee6491daae0)
<b>이메일 인증 및 정규표현식을 이용한 회원가입 검증</b>

 - Google SMTP Mail을 이용하여 인증 번호를 포함한 메일 발송
 ![image](https://github.com/amung9914/book_management/assets/137124338/8b11c114-f932-4489-9e16-320436402c89)
 - 정규표현식을 사용하여 올바른 이메일 및 비밀번호 양식 검증

### 초기화면 - 도서 목록 확인
![image](https://github.com/amung9914/book_management/assets/137124338/9dfd01d9-549d-4ed2-b85c-89732c1f595c)

저장된 도서 목록 호출
 - 최근 저장된순서로 도서를 호출
 - [보러가기] 버튼 클릭 시 상세 이동

### 도서 상세정보 확인
![image](https://github.com/amung9914/book_management/assets/137124338/a5789c73-447c-4fb5-afcd-bc7547adbaf5)
도서 상세정보 호출
 - 도서 이미지, 도서 소개글, 도서 제목, 저자, 대출 가능여부 표시
 - [수정하기] 버튼 클릭 시 '도서 정보 수정' 페이지로 이동(로그인한 사용자가 관리자인 경우 버튼출력)
 - [대출 이력 보기] 버튼 클릭 시 대출이력 호출
 - [대출신청] 버튼 클릭 시 '대출가능'인 경우 신청form출력, '대출불가'인 경우 신청form을 출력하지 않음

### 도서 대출 이력 확인
![대출이력](https://github.com/amung9914/book_management/assets/137124338/729a15a6-8549-455e-93f7-eba0004326bc)

도서 대출 이력 호출
 - 최근 저장된 순서로 해당 도서의 대출 이력을 호출
 - 대출자, 대출시간, 반납시간 확인 가능
 - 대출 중인 도서는 반납 시간에 '현재 대출중'으로 출력
   
### 도서 대출 처리
![대출신청](https://github.com/amung9914/book_management/assets/137124338/507b5d86-f0e7-4501-9196-7a8250d02118)

 - [대출신청] -> [대여하기]버튼 클릭 시 대출처리
   
### 도서 정보 수정
![image](https://github.com/amung9914/book_management/assets/137124338/5384cc68-4bd6-461d-a848-5d553391f15f)
![image](https://github.com/amung9914/book_management/assets/137124338/6d2a6852-045a-4df2-8291-b0643a4f895c)
기존 도서 상세정보 호출 및 정보 수정 처리
 - 관리자만 접근 가능(Spring Security 적용)
 - 도서 제목, 저자, 도서 소개글, 도서 이미지 변경 가능
 - 도서 소개글은 오픈소스 에디터(TinyMCE) 적용

### 도서등록
![image](https://github.com/amung9914/book_management/assets/137124338/800daf52-4d98-4a57-85c9-ef7808f61742)
![image](https://github.com/amung9914/book_management/assets/137124338/e7607a55-e9da-4574-8e7f-01b5c3185401)
등록할 도서 정보 입력
 -  관리자만 접근 가능(Spring Security 적용)
 -  도서 제목, 저자, 도서 소개글, 도서 이미지 등록 가능
 -  도서 소개글은 오픈소스 에디터(TinyMCE) 적용

### 대출중인 도서 목록 확인 및 도서 반납 처리
![대출도서](https://github.com/amung9914/book_management/assets/137124338/d836ec48-606d-422d-a0d9-fe44522db282)

로그인한 사용자의 현재 대출중인 도서 목록 호출
 - 총 대출중인 도서 수 및 도서 정보 출력
 - [반납하기]버튼 클릭 시 반납처리 진행
