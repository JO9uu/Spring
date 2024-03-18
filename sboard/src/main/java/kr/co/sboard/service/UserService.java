package kr.co.sboard.service;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public TermsDTO selectTerms() {
        return userMapper.selectTerms();
    }

    public int selectCountUser(String uid){
        return userMapper.selectCountUser(uid);
    }

    public void insertUser(UserDTO userDTO){

        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);

        userMapper.insertUser(userDTO);
    }

}
