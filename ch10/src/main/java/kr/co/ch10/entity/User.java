package kr.co.ch10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch10.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "user")
public class User {

    @Id
    private String uid;
    private String pass;
    private String name;
    private String birth;
    private int age ;
    private String hp;
    private String role;

    @CreationTimestamp
    private LocalDateTime regDate;

    public UserDTO toDTO(){
        return UserDTO.builder()
                .uid(uid)
                .pass(pass)
                .name(name)
                .age(age)
                .birth(birth)
                .hp(hp)
                .role(role)
                .regDate(regDate)
                .build();
    }

}