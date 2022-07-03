package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.service.complain.ComplainService;
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
public class ComplainServiceImpl implements ComplainService {
    @Override
    public ComplainDto save(ComplainDto complainDto) throws ParseException, IOException {
        return null;
    }

    @Override
    public List<ComplainDto> findAll() throws IOException {
        return null;
    }

    @Override
    public ComplainDto findById(Long aLong) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
