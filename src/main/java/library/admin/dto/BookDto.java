package library.admin.dto;

import lombok.Data;

@Data
public class BookDto {

    private int bookId;
    private String bookName;
    private String author;
    private String status;
}
