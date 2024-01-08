package library.admin.service;

import library.admin.domain.MemberVO;
import library.admin.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    /**
     * 회원가입
     */
    public void join(MemberVO member){
        //중복확인
        validateDuplicateMember(member);
        memberMapper.join(member);
    }

    /**
     * 아이디로 회원찾기
     */
    public MemberVO findMemberbyName(String name){
        MemberVO findMember = memberMapper.findByName(MemberVO.builder().name(name).build());
        if(findMember == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }else return findMember;
    }

    /**
     *  회원가입가능여부
     */
    public void isCheckForMemberName(String name){
        MemberVO findMember = memberMapper.findByName(MemberVO.builder().name(name).build());
        if(findMember != null){
            throw new IllegalArgumentException("사용중인 이메일입니다.");
        }
    }

    /**
     * 식별자로 회원 조회
     */
    public MemberVO findMemberbyId(int memberId){
        return memberMapper.findById(memberId);
    }

    public void validateDuplicateMember(MemberVO member){
        if(memberMapper.findByName(member) != null){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }


}
