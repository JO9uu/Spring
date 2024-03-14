package kr.co.ch07.service;

import kr.co.ch07.dto.User1DTO;
import kr.co.ch07.dto.User2DTO;
import kr.co.ch07.entity.User2;
import kr.co.ch07.repository.User2Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class User2Service {

    // 생성자 주입
    private final User2Repository repository;

    public void insertUser2(User2DTO user2DTO ){
        // DTO를 Entity로 변환
        User2 user2 = user2DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        repository.save(user2);
    }
    public User2DTO selectUser2(String uid){
        Optional<User2> result = repository.findById(uid);
        User2 user2 = result.get();

        // Entity를 DTO로 변환 후 리턴
        return user2.toDTO();

    }
    public List<User2DTO> selectUser2s(){
        // 전체 조회
        List<User2> user2s = repository.findAll();

        // Entity 리스트를 DTO 리스트로 변환
        List<User2DTO> user2DTOs = new ArrayList<>();

        for (User2 user2 : user2s){
            user2DTOs.add(user2.toDTO());
        }

        return user2DTOs;
    }
    public void updateUser2(User2DTO user2DTO){
        // DTO를 Entity로 변환
        User2 user2 = user2DTO.toEntity();

        // Entity 수정
        repository.save(user2);
    }
    public void deleteUser2(String uid){

        repository.deleteById(uid);
    }

}
