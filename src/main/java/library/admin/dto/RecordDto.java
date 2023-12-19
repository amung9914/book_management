package library.admin.dto;

import library.admin.domain.MemberVO;
import library.admin.domain.RecordVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordDto {
    private String memberName; // 회원아이디
    private LocalDateTime borrowTime; // 대출일시
    private LocalDateTime returnTime; // 반납일시

    public RecordDto(MemberVO member, RecordVO record) {
        this.memberName = member.getMemberName();
        this.borrowTime = record.getBorrowTime();
        this.returnTime = record.getReturnTime();
    }
}
