package library.admin.dto;

import lombok.Data;

@Data
public class BorrowRequestDto {
    private String memberName;
    private int bookId;

}
