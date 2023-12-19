package library.admin.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordMapperTest {

    @Autowired RecordMapper recordMapper;

    @Test
    public void testMethodNameHere() throws Exception {
        // given
        recordMapper.findByBookId(12);

        // when

        // then
        Assertions.assertThat( recordMapper.findByBookId(12).getRecordId()).isEqualTo(12);
    }

}