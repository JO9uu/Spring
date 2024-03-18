package kr.co.sboard.mapper;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public TermsDTO selectTerms();
    public void insertUser(UserDTO userDTO);
    public int selectCountUser(String uid);
    public void selectUsers();
    public void updateUser();
    public void deleteUser();


}