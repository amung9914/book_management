## AWS 구축 내용

### Elastic Beanstalk
    CI/CD 구축을 위해 간편하게 배포가 가능한 AWS의 Elastic Beanstalk를 사용하였습니다.
    - 플랫폼 : Java Corretto 17 (프로덕션용 무료 멀티 플랫폼, JDK 17 지원)
    - RDS : 
        1) 데이터베이스 엔진 : MySQL v8.0.33
        2) 데이터베이스 인스턴스 클래스 : db.t2.micro
    - EC2:
        이미지 : Amazon Linux
        인스턴스 유형 : t2.micro
    - 환경속성 :
        애플리케이션에 필요한 비밀키 정보를 Elastic Beanstalk의 환경속성 기능을 통해 주입
### IAM(Identity and Access Management)
    - CI/CD 적용을 위해 github action 연결에 필요한 사용자 자격 증명 설정
    - S3와 로컬 환경 연결에 필요한 사용자 자격 증명 설정
    - S3와 Elastic Beanstalk 연결에 필요한 사용자 자격 증명 설정

### Amazon S3
    - 이미지 파일 저장을 위해 S3 버킷을 사용하였습니다.
    - 로컬 개발 시 library9914 버킷을 생성하여 개발진행
    - 실제 배포 시 Elastic Beanstalk에서 자동으로 생성한 버킷에 'library'폴더를 추가하여
      해당 경로에 이미지 파일이 저장되도록 설정


