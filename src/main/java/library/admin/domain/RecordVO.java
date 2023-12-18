package library.admin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 도서 대출 이력
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecordVO {

    private int recordId;
    private int bookId;
    private int memberId;
    private LocalDateTime borrowTime; // 대출일시
    private LocalDateTime returnTime; // 반납일시

    @Builder
    public RecordVO(int bookId, int memberId, LocalDateTime borrowTime, LocalDateTime returnTime) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }

}
