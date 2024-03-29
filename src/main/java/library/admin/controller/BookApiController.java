package library.admin.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import library.admin.domain.BookVO;
import library.admin.domain.MemberVO;
import library.admin.domain.RecordVO;
import library.admin.dto.BookDto;
import library.admin.dto.BorrowRequestDto;
import library.admin.dto.RecordDto;
import library.admin.service.BookService;
import library.admin.service.MemberService;
import library.admin.service.RecordService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final MemberService memberService;
    private final BookService bookService;
    private final RecordService recordService;

    /**
     * 도서를 등록하는 메서드
     */
    @PostMapping("/book")
    public Result<String> createBook(@ModelAttribute @Valid CreateBookRequest request,
                                     @RequestParam MultipartFile img){
        // 작성하기
        BookVO newBook = BookVO.builder()
                .bookName(request.getBookName())
                .author(request.getAuthor())
                .content(request.getContent())
                .build();
        bookService.register(newBook,img);
        int bookId = bookService.findBookIdByName(newBook);
        return new Result(newBook.getBookName());
    }


    /**
     * 도서 수정 기능
     */
    @PutMapping("/book")
    public Result<String> updateBook(@ModelAttribute @Valid UpdateBookRequest request,
                                     @RequestParam MultipartFile img){

        bookService.update(BookVO.builder().bookId(request.getBookId())
                .bookName(request.getBookName())
                .author(request.getAuthor())
                .content(request.getContent())
                .build(),img);
        return new Result(request.getBookName());
    }

    /**
     * 도서 목록을 전달합니다
     * return Result{"count":도서의 수,"data":[{"bookId":14,"bookName":"마흔에 읽는 쇼펜하우어","author":"강용수"},...]}
     */
    @GetMapping("/bookList")
    public ListResult<List<BookListDto>> getBookList(){
        List<BookVO> bookVOList = bookService.bookList();
        List<BookListDto> bookList = bookVOList.stream()
                .map(o -> new BookListDto(o))
                .toList();
        return new ListResult(null,bookList.size(),bookList);
    }

    /**
     * 회원의 대출중인 도서 목록을 출력
     */
    @GetMapping("/borrowList")
    public ListResult<List<BookListDto>> getBorrowList(Principal principal){
        MemberVO findMember = memberService.findMemberbyName(principal.getName());
        List<BookVO> bookList = bookService.borrowListByMember(findMember);
        if(bookList.isEmpty()){
            throw new IllegalArgumentException("현재 대출중인 도서가 없습니다");
        }
        List<BookListDto> borrowList = bookList.stream().map(o -> new BookListDto(o))
                .toList();
        return new ListResult(principal.getName(), borrowList.size(),borrowList);
    }



    /**
     * 도서 세부 정보 조회
     */
    @GetMapping("/detailView/{bookId}")
    public Result<BookDto> getDetailBook(@PathVariable int bookId){
        BookVO bookVO = bookService.detailBook(bookId);
        BookDto bookDto = new BookDto(bookVO);
        return new Result(bookDto);
    }


    /**
     * 도서 대출 이력 조회
     */
    @GetMapping("/record/{bookId}")
    public Result<List<RecordDto>> getRecord(@PathVariable int bookId){
        List<RecordVO> recordList = recordService.recordList(bookId);
        if(recordList.isEmpty()){
            throw new IllegalArgumentException("대출 이력이 존재하지 않습니다.");
        }
        List<RecordDto> resultList = new ArrayList<>();
        for (RecordVO record : recordList) {
            MemberVO member = memberService.findMemberbyId(record.getMemberId());
            resultList.add(new RecordDto(member,record));
        }
       return new Result(resultList);
    }

    /**
     * 도서 대출 처리
     */
    @PostMapping("/borrow")
    public Result<String> borrow(@RequestBody BorrowRequestDto request, Principal principal){

        MemberVO findMember = memberService.findMemberbyName(principal.getName());
        bookService.borrow(request.getBookId(),findMember);
        return new Result(principal.getName());
    }

    /**
     * 도서 반납 처리
     */
    @PutMapping("/borrow")
    public Result<String> returnBook(@RequestBody ReturnRequestDto request){
        bookService.returnBook(request.getBookId());
        return new Result(request.getBookId());
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ListResult<T>{
        private String name;
        private int count;
        private T data;
    }

    @Data
    static private class CreateBookRequest {
        @NotEmpty
        private String bookName;
        @NotEmpty
        private String author;
        private String content;
    }

    @Data
    static private class BookListDto {
        private int bookId;
        private String bookName;
        private String author;

        public BookListDto(BookVO book){
            this.bookId = book.getBookId();
            this.bookName = book.getBookName();
            this.author = book.getAuthor();
        }

    }
    @Data
    static private class UpdateBookRequest {

        private int bookId;
        @NotEmpty
        private String bookName;
        @NotEmpty
        private String author;
        private String content;
    }

    @Data
    static private class ReturnRequestDto {
        private int bookId;
    }
}
