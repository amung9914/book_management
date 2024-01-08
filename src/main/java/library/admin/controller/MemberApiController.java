package library.admin.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import library.admin.domain.MemberVO;
import library.admin.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/member")
    public Result<String> saveMember(@RequestBody @Valid CreateMemberRequest request){
        MemberVO newMember = MemberVO.builder()
                .name(request.getMemberName())
                .password(request.getPassword())
                .build();
        memberService.join(newMember);
        return new Result(newMember.getMemberName());
    }

    /**
     * 아이디 중복 검증
     */
    @GetMapping("/nameCheck/{memberName}")
    public Result<Boolean> isCheck(@PathVariable String memberName){
        memberService.isCheckForMemberName(memberName);
        return new Result(true);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    static private class CreateMemberRequest{
        @NotEmpty
        private String memberName;
        @NotEmpty
        private String password;
    }
}
