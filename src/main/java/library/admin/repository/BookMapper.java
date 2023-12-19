package library.admin.repository;

import library.admin.domain.BookVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Delete("DELETE FROM book")
    void deleteAll();

    /**
     * 도서 목록 전달
     */
    @Select("SELECT * FROM book ORDER BY book_id DESC")
    List<BookVO> bookList();

    @Select("SELECT * FROM book WHERE book_id = #{bookId}")
    BookVO detailBook(int bookId);

    /**
     * 도서를 등록한다
     */
    @Insert("INSERT INTO book(book_name,author,status) VALUES(#{bookName},#{author},'AVAILABLE')")
    int register(BookVO book);

    /**
     * 등록된 도서를 수정한다
     */
    @Update("UPDATE book SET book_name = #{bookName}, author = #{author} WHERE book_id = #{bookId}")
    int update(BookVO book);

    /**
     * 도서 상태 변경
     */
    @Update("UPDATE book SET status = #{status} WHERE book_id = #{bookId}")
    int changeStatus(BookVO book);



}
