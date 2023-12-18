package library.admin.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

    @Delete("DELETE FROM book")
    void deleteAll();

    /**
     * 도서를 등록한다
     */

    /**
     * 등록된 도서를 수정한다
     */

    /**
     * 도서에 대한 대출처리를 한다
     */

    /**
     * 도서에 대하여 반납 처리한다
     */

}
