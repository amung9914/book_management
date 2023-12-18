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
    private String status;

    @Builder
    public BookVO(String bookName, String author, String status) {
        this.bookName = bookName;
        this.author = author;
        this.status = status;
    }

    /**
     * 책 상태 변경 매서드(대출)
     */
    public void borrowBook(){
        this.status = "BORROWED";
    }

    /**
     * 책 상태 변경 매서드(반납)
     */
    public void returnBook(){
        this.status = "AVAILABLE";
    }

}
