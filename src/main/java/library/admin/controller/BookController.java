package library.admin.controller;

import library.admin.domain.MemberVO;
import library.admin.dto.BookDto;
import library.admin.dto.BorrowRequestDto;
import library.admin.service.BookService;
import library.admin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final MemberService memberService;
    private final BookService bookService;

    @PostMapping("/book")
    public String create(BookDto from){
        // 작성하기
        return "redirect:/";
    }

    @PostMapping
    public String borrow(@RequestBody BorrowRequestDto request){
        MemberVO findMember = memberService.findMemberbyName(request.getMemberName());
        bookService.borrow(request.getBookId(),findMember);
        return "";
    }
}
