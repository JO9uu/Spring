package kr.co.ch07.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch07.dto.User1DTO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="user1")        // 매핑 테이블 설정
public class User1 {

    @Id                     // PK 컬럼 설정
    private String uid;

    @Column(name="name")    // 매핑 컬럼 설정
    private String name;

    @Column(name="birth")
    private String birth;

    @Column (name="hp")
    private String hp;

    @Column(name="age")
    private int age;

    // DTO 변환 메소드
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
