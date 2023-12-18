package library.admin.controller;

import library.admin.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final MemberMapper memberMapper;

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @GetMapping("/member")
    public List<String> findAll(){
        return memberMapper.findAll();
    }

}
