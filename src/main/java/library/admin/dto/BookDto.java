package library.admin.dto;

import library.admin.domain.BookVO;
import lombok.Data;

@Data
public class BookDto {

    private String bookName;
    private String author;
    private String status;

    public BookDto(BookVO book) {
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.status = book.getStatus();
    }
}
