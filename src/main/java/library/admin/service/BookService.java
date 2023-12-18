package library.admin.service;

import library.admin.domain.BookVO;
import library.admin.domain.MemberVO;
import library.admin.domain.RecordVO;
import library.admin.repository.BookMapper;
import library.admin.repository.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final RecordMapper recordMapper;

    /**
     * 도서를 등록한다
     */
    public String register(BookVO book){
        book.makeAvailable();
        return getResult(bookMapper.register(book));
    }

    /**
     * 등록된 도서를 수정한다
     * bookId,bookName,author 필요
     */
    public String update(BookVO book){
        return getResult(bookMapper.update(book));
    }


    /**
     * 도서 대출처리
     */
    public String borrow(int bookId, MemberVO member){
        // book 상태변경
        BookVO book = BookVO.builder()
                        .bookId(bookId)
                        .build();
        book.borrowBook();
        if(bookMapper.changeStatus(book)==1){
            // 대출 날짜기록
            RecordVO record = RecordVO.builder()
                                        .bookId(bookId)
                                        .memberId(member.getMemberId())
                                        .build();
            return getResult(recordMapper.borrow(record));
        };
        return "FAILED";
    }

    /**
     * 도서 반납처리
     */
    public String returnBook(int bookId){
        // book 상태변경
        BookVO book = BookVO.builder()
                .bookId(bookId)
                .build();
        book.makeAvailable();
        if(bookMapper.changeStatus(book)==1){
            // 대출 이력에 반납일시 기록
            RecordVO findRecord = recordMapper.findByBookId(bookId);
            return getResult(recordMapper.returnRecord(findRecord));
        };
        return "FAILED";
    }


    /**
     * 도서 목록 전달
     */
    public List<BookVO> bookList(){
        return bookMapper.bookList();
    }

    private String getResult(int result){
        return result==1? "SUCCESS" : "FAILED";
    }
}
