package kr.co.sboard.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConfigDTO {

    private String cate;
    private String boardName;
    private String admin;

    @ColumnDefault("0")
    private int total;

    @CreationTimestamp
    private LocalDateTime createDate;
}
