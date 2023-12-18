package library.admin.repository;

import library.admin.domain.MemberVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("select member_name from member")
    List<String> findAll();


    @Delete("DELETE FROM member")
    void deleteAll();

    @Insert("INSERT INTO member(member_name,password) VALUES(#{memberName},#{password})")
    int join(MemberVO member);

    @Select("SELECT * FROM member WHERE member_name = #{memberName}")
    MemberVO findByName(MemberVO member);
}
