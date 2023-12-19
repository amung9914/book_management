package library.admin.controller;

import library.admin.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/signup")
    public String signup(){
        return "/member/createMemberForm";
    }

    @GetMapping("/createBook")
    public String createBook(){
        return "/book/createBookForm";
    }

    @GetMapping("/detailBook/{id}")
    public String detailBook(@PathVariable int id, Model model){
        model.addAttribute("bookId",id);
        return "/book/detailBook";
    }


    @GetMapping("/updateForm/{bookId}")
    public String updateForm(@PathVariable int bookId, Model model){
        model.addAttribute("bookId",bookId);
        return "/book/updateBookForm";
    }

    @GetMapping("/returnForm")
    public String returnForm(){
        return "/book/returnForm";
    }

}
