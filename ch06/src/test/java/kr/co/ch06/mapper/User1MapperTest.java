package kr.co.ch06.mapper;

import kr.co.ch06.dto.User1DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class User1MapperTest {

    @Autowired
    private User1Mapper mapper;

    @Test
    @DisplayName("insertUser1...")
    void insertUser1() {
        log.info("insertUser1...");
        // 테스트 코드 작성: given - when -then 패턴

        // given : 테스트를 실행할 준비 단계
        User1DTO user1DTO = User1DTO.builder()
                .uid("j101")
                .name("홍길동")
                .birth("1990-03-11")
                .hp("010-1234-1111")
                .age(22)
                .build();

        //when : 테스트를 진행하는 단계
        mapper.insertUser1(user1DTO);

        //then : 테스트 결과를 검증하는 단계
        User1DTO resultUser1 = mapper.selectUser1(user1DTO.getUid());
        Assertions.assertEquals(user1DTO.getUid(), resultUser1.getUid());

    }

    @Test
    @DisplayName("user1 조회...")
    void selectUser1() {
        log.info("selectUser1...");
    }

    @Test
    @DisplayName("user1 목록...")
    void selectUser1s() {
        log.info("selectUser1s...");
    }

    @Test
    @DisplayName("user1 수정...")
    void updateUser1() {
        log.info("updateUser1...");
    }

    @Test
    @DisplayName("user1 삭제...")
    void deleteUser1() {
        log.info("deleteUser1...");
    }
}