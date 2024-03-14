package kr.co.ch04.dao;

import kr.co.ch04.dto.User1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User1Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser1(User1DTO user1DTO){

        String sql = "INSERT INTO `user1` values  (?, ?, ?, ?, ?)";

        Object[] params = {
                user1DTO.getUid(),
                user1DTO.getName(),
                user1DTO.getBirth(),
                user1DTO.getHp(),
                user1DTO.getAge()
        };

        jdbcTemplate.update(sql, params);

    }

    public User1DTO selectUser1(String uid){
        String sql = "SELECT * FROM `user1` WHERE `uid`";
        return jdbcTemplate.queryForObject(sql, new User1RowMapper(), uid);

    }

    public List<User1DTO> selectUser1s(){
        String sql = "SELECT * FROM `user1`";
        return jdbcTemplate.query(sql, new User1RowMapper());
    }

    public void updateUser1(User1DTO user1DTO){

    }

    public void deleteUser1(String uid){
        String sql = "DELETE FROM `user1` WHERE `uid`=? ";
        jdbcTemplate.update(sql, uid);
    }






}
