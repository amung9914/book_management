package library.admin.service;

import library.admin.domain.RecordVO;
import library.admin.repository.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordMapper recordMapper;

    public List<RecordVO> recordList(int bookId){
        return recordMapper.recordList(bookId);
    }
}
