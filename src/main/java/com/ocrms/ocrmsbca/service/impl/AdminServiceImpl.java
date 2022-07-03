package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.dto.AddAdminDto;
import com.ocrms.ocrmsbca.service.admin.AdminService;
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
public class AdminServiceImpl implements AdminService {
    @Override
    public AddAdminDto save(AddAdminDto addAdminDto) throws ParseException, IOException {
        return null;
    }

    @Override
    public List<AddAdminDto> findAll() throws IOException {
        return null;
    }

    @Override
    public AddAdminDto findById(Long aLong) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
