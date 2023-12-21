# 도서 관리 프로그램 

### 🔗Deploy site

[실제 배포 사이트 바로가기](http://library-env.eba-wgbtarnw.ap-northeast-2.elasticbeanstalk.com/)

##  ✨ 프로젝트 소개
``RESTful``, ``CI/CD``를 적용한 도서관리 프로그램을 요구사항에 따라 개발하였습니다.<br>
기간 : 2023.12.16 ~ 2023.12.21 약 6일 소요

## 시스템 구성도
![시스템구성도](https://github.com/amung9914/book_management/assets/137124338/e78547a8-498a-408c-8d80-2aff703a9893)

## 🛠 Languages and Tools:
- JDK 17
- Spring Boot v3.2.0
- MySQL v8.0.33
- MyBatis v3.5.14
- Spring Cloud AWS Starter v2.2.6
- Thymeleaf v6.3.1
- Thymeleaf Layout Dialect v3.3.0
- lombok v1.18.28
- Junit v5.10.1
- Bootstrap v4.1.3
- TinyMCE v6

## 💡 ERD : 
![ERD](https://github.com/amung9914/book_management/assets/137124338/e1c056f7-06d1-449f-afab-4bc809ba0ad9)

## 📌 구현 기능 : 

### Github Action과 AWS Elastic Beanstalk를 이용한 CI/CD 구현
Github에 push를 하면 바로 배포될 수 있도록 CI/CD를 구현하였습니다.

### 초기화면 - 도서 목록 확인
![image](https://github.com/amung9914/book_management/assets/137124338/fdb902aa-3a6d-4784-9ed3-ed17451369c2)
저장된 도서 목록 호출
 - 최근 저장된순서로 도서를 호출
 - [보러가기] 버튼 클릭 시 상세 이동

### 도서 상세정보 확인
![image](https://github.com/amung9914/book_management/assets/137124338/60c0db5e-3288-4dbb-be2c-4c907ce66266)
도서 상세정보 호출
 - 도서 이미지, 도서 소개글, 도서 제목, 저자, 대출 가능여부 표시
 - [수정하기] 버튼 클릭 시 '도서 정보 수정' 페이지로 이동
 - [대출 이력 보기] 버튼 클릭 시 대출이력 호출
 - [대출신청] 버튼 클릭 시 '대출가능'인 경우 신청form출력, '대출불가'인 경우 신청form을 출력하지 않음

### 도서 대출 이력 확인
![image](https://github.com/amung9914/book_management/assets/137124338/724f5243-1d32-493d-b2bb-bddc2b5b7426)
도서 대출 이력 호출
 - 최근 저장된 순서로 해당 도서의 대출 이력을 호출
 - 대출자, 대출시간, 반납시간 확인 가능
 - 대출 중인 도서는 반납 시간에 '현재 대출중'으로 출력
   
### 도서 대출 처리
![image](https://github.com/amung9914/book_management/assets/137124338/53d24f9d-9bb9-4db0-8007-4f19975f53de)

아이디 입력 후 도서 대출처리
 - 신청자 아이디 입력 및 [대여하기]버튼 클릭 시 대출처리
 - 존재하지 않는 아이디인경우 도서 대출 실패, 존재 시 대출 성공
   
### 도서 정보 수정
![image](https://github.com/amung9914/book_management/assets/137124338/3c93f804-62d1-49b7-a627-b1c2ed7e4c14)
![image](https://github.com/amung9914/book_management/assets/137124338/e9738908-b23c-4cf9-8083-2ff64b686322)
기존 도서 상세정보 호출 및 정보 수정 처리
 - 도서 제목, 저자, 도서 소개글, 도서 이미지 변경 가능

### 도서등록
![image](https://github.com/amung9914/book_management/assets/137124338/f17f3447-4ad1-4b7c-9a60-40df83871af2)
![image](https://github.com/amung9914/book_management/assets/137124338/ee7f4d8e-36ff-44d7-9017-727aee17b28b)
등록할 도서 정보 입력
 -  도서 제목, 저자, 도서 소개글, 도서 이미지 등록 가능
 -  도서 소개글은 오픈소스 에디터(TinyMCE) 적용

### 현재 대출중인 도서 목록 확인
![image](https://github.com/amung9914/book_management/assets/137124338/8e334ac5-2389-45e5-9bf9-264aa5325ed1)
현재 대출중인 도서 목록 호출
 - 총 대출중인 도서 수 및 도서 정보 출력
 - 도서등록순서기준 내림차순으로 정렬
 - [반납하기]버튼 클릭 시 반납처리 진행
 - 신청자 아이디입력 및 [검색]버튼 클릭 시 특정회원의 대출중인 도서 목록 호출
   
### 회원 아이디로 대출중인 도서 검색
![image](https://github.com/amung9914/book_management/assets/137124338/0bc315eb-a3c2-4c22-820f-cc5ccb121cd6)
입력한 아이디의 회원이 대출중인 도서 목록 호출
 - 신청자의 현재 대출중인 도서 정보 출력
 - 대출신청순서기준 내림차순으로 정렬
   
### 회원가입
![image](https://github.com/amung9914/book_management/assets/137124338/188af052-7d25-47ef-9799-13d655a6e8c8)
아이디와 비밀번호를 입력하여 회원가입
- 아이디 중복 시 가입 실패
