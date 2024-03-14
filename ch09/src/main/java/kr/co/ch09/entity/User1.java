package kr.co.ch09.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch09.dto.User1DTO;
import lombok.*;
import org.springframework.stereotype.Repository;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Repository
@Table(name="user1")
public class User1 {

    @Id
    private String uid;

    private String name;
    private String birth;
    private String hp;
    private int age;

    public User1DTO toDTO(){

        return User1DTO.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .age(age)
                .build();
    }
}
