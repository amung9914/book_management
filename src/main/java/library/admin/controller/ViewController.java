package library.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/signup")
    public String signup(){
        return "createMemberForm";
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/createBook")
    public String createBook(){
        return "createBookForm";
    }

    @GetMapping("/detailBook/{id}")
    public String detailBook(@PathVariable int id, Model model){
        model.addAttribute("bookId",id);
        return "detailBook";
    }


    @GetMapping("/updateForm/{bookId}")
    public String updateForm(@PathVariable int bookId, Model model){
        model.addAttribute("bookId",bookId);
        return "updateBookForm";
    }

    @GetMapping("/returnForm")
    public String returnForm(){
        return "returnForm";
    }

}
