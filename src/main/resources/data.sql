CREATE TABLE member 	  -- 회원 테이블
(
    member_id    INT primary key AUTO_INCREMENT,
    member_name  VARCHAR(20) NOT NULL unique,
    password     VARCHAR(20) NOT NULL
);
CREATE TABLE book 	  -- 책 테이블
(
    book_id      INT primary key AUTO_INCREMENT,
    book_name    VARCHAR(40) NOT NULL unique,
    author       VARCHAR(20) NOT NULL,
    book_status       VARCHAR(10) NOT NULL
);
CREATE TABLE record  -- 대출이력 테이블
(
    record_id     INT primary key AUTO_INCREMENT,
    book_id       INT NOT NULL,
    member_id     INT NOT NULL,
    borrow_time   TIMESTAMP,
    return_time   TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);