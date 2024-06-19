package com.example.security.service;

import com.example.security.dto.JoinDTO;
import com.example.security.entity.UserEntity;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean loginProc(JoinDTO joinDTO) {

        UserEntity user = userRepository.findByUsername(joinDTO.getUsername());
        if(user == null){
            return false;
        }

        return bCryptPasswordEncoder.encode(joinDTO.getPassword()).equals(user.getPassword());
    }

}
