package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.dto.UserRegisterDto;
import com.ocrms.ocrmsbca.service.user.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserRegisterDto save(UserRegisterDto userRegisterDto) throws ParseException, IOException {
        return null;
    }

    @Override
    public List<UserRegisterDto> findAll() throws IOException {
        return null;
    }

    @Override
    public UserRegisterDto findById(Long aLong) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
