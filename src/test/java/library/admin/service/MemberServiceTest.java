package library.admin.service;

import library.admin.domain.MemberVO;
import library.admin.repository.MemberMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberMapper memberMapper;

    @DisplayName("회원가입")
    public void joinMember() throws Exception {
        // given
        MemberVO testMember = MemberVO.builder().name("test4").password("1234").build();

        // when
        memberService.join(testMember);

        // then
        //Assertions.assertThat(memberService.findMemberbyName("test1").getMemberName()).isEqualTo(testMember.getMemberName());
    }

    @DisplayName("중복회원으로 가입")

    public void duplicatedMember() throws Exception {
        // given
        MemberVO testMember1 = MemberVO.builder().name("test1").password("1234").build();
        MemberVO testMember2 = MemberVO.builder().name("test1").password("1234").build();

        // when
        memberService.join(testMember1);


        // then
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            memberService.join(testMember2);
        });
        assertEquals("이미 존재하는 회원입니다.",exception.getMessage());


    }
}
