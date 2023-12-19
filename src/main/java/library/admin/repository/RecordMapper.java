package library.admin.repository;

import library.admin.domain.RecordVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {

    @Delete("DELETE FROM record")
    void deleteAll();

    @Select("SELECT * FROM record WHERE book_id = #{bookId} ORDER BY record_id DESC")
    List<RecordVO> recordList(int bookId);

    /**
     * 도서 대출 기록
     */
    @Insert("INSERT INTO record(book_id,member_id,borrow_time) VALUES(#{bookId},#{memberId},NOW())")
    int borrow(RecordVO record);

    /**
     * 도서 반납 기록
     */
    @Update("UPDATE record SET return_time = NOW() WHERE record_id = #{recordId}")
    int returnRecord(RecordVO record);

    @Select("SELECT * FROM record WHERE book_id = #{bookId} AND return_time IS NULL")
    RecordVO findByBookId(int bookId);
}
