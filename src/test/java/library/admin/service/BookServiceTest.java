package library.admin.service;

import library.admin.domain.BookVO;
import library.admin.domain.MemberVO;
import library.admin.repository.BookMapper;
import library.admin.repository.RecordMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceTest {

    @Autowired BookService bookService;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired MemberService memberService;


    @BeforeEach
    public void clearMemberRepository(){
        recordMapper.deleteAll();
        bookMapper.deleteAll();

    }

    @DisplayName("도서를 등록한다")
    @Test
    public void register() throws Exception {
        // given
        BookVO newBook = BookVO.builder()
                .bookName("testbook")
                .author("testauthor")
                .build();
        // when
        bookService.register(newBook);

        // then
        Assertions.assertThat(bookService.bookList().get(0).getBookName()).isEqualTo(newBook.getBookName());
    }

    @DisplayName("도서를 수정한다")
    @Test
    public void testMethodNameHere() throws Exception {
        // given
        BookVO newBook = BookVO.builder()
                .bookName("testbook")
                .author("testauthor")
                .build();
        bookService.register(newBook);

        // when
        BookVO findBook = bookService.bookList().get(0);
        int findId = findBook.getBookId();

        BookVO updateBook = BookVO.builder()
                            .bookId(findId)
                            .bookName("newBook")
                            .author("testauthor")
                            .build();
        bookService.update(updateBook);

        // then
        Assertions.assertThat(bookService.bookList().get(0).getBookName()).isEqualTo(updateBook.getBookName());
    }

    @DisplayName("도서를 대출한다")
    @Test
    public void borrow() throws Exception {
        // given
        BookVO newBook = BookVO.builder()
                .bookName("testbook")
                .author("testauthor")
                .build();
        bookService.register(newBook);
        int bookId = bookService.bookList().get(0).getBookId();

        MemberVO member = memberService.findMemberbyName("test1");

        // when
        bookService.borrow(bookId,member);

        // then
        Assertions.assertThat(bookService.bookList().get(0).getBookStatus()).isEqualTo("BORROWED");
        Assertions.assertThat(recordMapper.findByBookId(bookId).getBorrowTime()).isBefore(LocalDateTime.now());
    }

    @DisplayName("도서를 반납한다")
    @Test
    public void returnBook() throws Exception {
        // given
        BookVO newBook = BookVO.builder()
                .bookName("testbook")
                .author("testauthor")
                .build();
        bookService.register(newBook);
        int bookId = bookService.bookList().get(0).getBookId();

        MemberVO member = memberService.findMemberbyName("test1");
        bookService.borrow(bookId,member);

        // when
        bookService.returnBook(bookId);

        // then
        Assertions.assertThat(bookService.bookList().get(0).getBookStatus()).isEqualTo("AVAILABLE");
        Assertions.assertThat(recordMapper.findByBookId(bookId).getReturnTime()).isBefore(LocalDateTime.now());
    }

}