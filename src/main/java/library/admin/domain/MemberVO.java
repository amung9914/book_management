package library.admin.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberVO implements UserDetails {

    private int memberId; // 식별자
    private String memberName; // 회원 아이디(email)
    private String password; // 비밀번호
    private String authority; // 권한 USER,ADMIN

    @Builder
    public MemberVO(String name, String password,String authority) {
        this.memberName = name;
        this.password = password;
        this.authority = authority;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.authority));
    }

    @Override // 사용자의 email 반환
    public String getUsername() {
        return memberName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


