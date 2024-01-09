package library.admin.service;

import library.admin.domain.MemberVO;
import library.admin.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 스프링 시큐리티 관련 인터페이스 구현
public class UserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;
    @Override // 사용자 정보를 가져온다
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        MemberVO findMember = memberMapper.findByNameForLogin(memberName);
        if(findMember==null){
            new IllegalArgumentException(memberName);
        }
        return User.builder()
                .username(findMember.getUsername())
                .password(findMember.getPassword())
                .roles(findMember.getAuthority())
                .build();
    }
}
