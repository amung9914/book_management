package library.admin.service;

import library.admin.domain.BookVO;
import library.admin.domain.MemberVO;
import library.admin.domain.RecordVO;
import library.admin.repository.BookMapper;
import library.admin.repository.MemberMapper;
import library.admin.repository.RecordMapper;
import library.admin.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final RecordMapper recordMapper;
    private final S3Uploader s3Uploader;

    /**
     * 도서를 등록한다
     */
    public void register(BookVO book, MultipartFile file){
        book.makeAvailable();

        String url = "";
        if(file !=null) {
            url = s3Uploader.uploadFileToS3(file,"static/library");
            book.updateUrl(url);
        }
        bookMapper.register(book);
    }

    public int findBookIdByName(BookVO book){
        return bookMapper.findBookIdByName(book);
    }

    /**
     * 등록된 도서를 수정한다
     * bookId,bookName,author 필요
     */
    public void update(BookVO book, MultipartFile file){
        String url = "";
        if(file !=null) {
            url = s3Uploader.uploadFileToS3(file,"static/library");
            book.updateUrl(url);

        }
        if(bookMapper.update(book)!=1){
            throw new IllegalArgumentException("도서수정실패");
        };
    }


    /**
     * 도서 대출처리
     */
    public void borrow(int bookId, MemberVO member){
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
            recordMapper.borrow(record);
        }else{
            throw new IllegalArgumentException("이미 대출된 도서입니다");
        };

    }

    /**
     * 도서 반납처리
     */
    public void returnBook(int bookId){
        // book 상태변경
        BookVO book = BookVO.builder()
                .bookId(bookId)
                .build();
        book.makeAvailable();
        if(bookMapper.changeStatus(book)==1){
            // 대출 이력에 반납일시 기록
            RecordVO findRecord = recordMapper.findByBookId(bookId);
            recordMapper.returnRecord(findRecord);
        }else{
            throw new IllegalArgumentException("도서 반납 실패");
        };

    }


    /**
     * 도서 목록 전달
     */
    public List<BookVO> bookList(){
        return bookMapper.bookList();
    }

    /**
     * 대출중인 도서 목록 출력
     */
    public List<BookVO> borrowList(){
        return bookMapper.borrowList();
    }

    /**
     * 특정인이 대출중인 도서 목록 출력
     */
    public List<BookVO> borrowListByMember(MemberVO member){
        return bookMapper.borrowListByMember(member);
    }

    public BookVO detailBook(int bookId){
        return bookMapper.detailBook(bookId);
    }

}
