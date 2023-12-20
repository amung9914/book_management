package library.admin.repository;

import library.admin.domain.BookVO;
import library.admin.domain.MemberVO;
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
    @Insert("INSERT INTO book(book_name,author,book_status,content,url) VALUES(#{bookName},#{author},'AVAILABLE',#{content},#{url})")
    int register(BookVO book);

    /**
     * 등록된 도서를 수정한다
     */
    @Update("UPDATE book SET book_name = #{bookName}, author = #{author}, content = #{content}, url = #{url} WHERE book_id = #{bookId}")
    int update(BookVO book);

    /**
     * 도서 상태 변경
     */
    @Update("UPDATE book SET book_status = #{bookStatus} WHERE book_id = #{bookId}")
    int changeStatus(BookVO book);

    @Select("SELECT book_id FROM book WHERE book_name = #{bookName}")
    int findBookIdByName(BookVO book);


    @Select("SELECT * FROM book WHERE book_status = 'BORROWED'")
    List<BookVO> borrowList();


    /**
     * 특정인이 현재 대출중인 도서
     */
    @Select("SELECT b.* FROM record r, book b WHERE  r.book_id = b.book_id " +
            " AND r.return_time IS NULL AND r.member_id = #{memberId}")
    List<BookVO> borrowListByMember(MemberVO member);
}
