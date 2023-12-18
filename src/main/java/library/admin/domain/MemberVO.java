package library.admin.domain;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberVO {

    private int memberId; // 식별자
    private String memberName; // 회원 아이디
    private String password; // 비밀번호

    @Builder
    public MemberVO(String name, String password) {
        this.memberName = name;
        this.password = password;
    }
}


