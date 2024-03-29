package library.admin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookVO {

    private int bookId;
    private String bookName;
    private String author;
    private String bookStatus;
    private String content;
    private String url;


    @Builder
    public BookVO(int bookId, String bookName, String author, String bookStatus, String content, String url) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.bookStatus = bookStatus;
        this.content = content;
        this.url = url;
    }

    /**
     * 책 상태 변경 매서드(대출)
     */
    public void borrowBook(){
        this.bookStatus = "BORROWED";
    }

    /**
     * 책 상태 변경 매서드(반납)
     */
    public void makeAvailable(){
        this.bookStatus = "AVAILABLE";
    }

    /**
     * 사진 url 추가 메서드
     */
    public void updateUrl(String url) {
        this.url = url;
    }
}
