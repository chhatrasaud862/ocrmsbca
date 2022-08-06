package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.Enum.EComplainStatus;
import com.ocrms.ocrmsbca.components.AuthorizeUser;
import com.ocrms.ocrmsbca.components.FileStorageComponent;
import com.ocrms.ocrmsbca.dto.ComplainDto;
import com.ocrms.ocrmsbca.dto.ResponseDto;
import com.ocrms.ocrmsbca.entity.complain.Complain;
import com.ocrms.ocrmsbca.repository.complain.ComplainRepository;
import com.ocrms.ocrmsbca.service.complain.ComplainService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Service
public class ComplainServiceImpl implements ComplainService {
    private final ComplainRepository complainRepository;
    private final FileStorageComponent fileStorageComponent;

    public ComplainServiceImpl(ComplainRepository complainRepository, FileStorageComponent fileStorageComponent) {
        this.complainRepository = complainRepository;
        this.fileStorageComponent = fileStorageComponent;
    }

    @Override
    public ComplainDto save(ComplainDto complainDto) throws ParseException, IOException {
        ResponseDto responseDto = fileStorageComponent.storeFile(complainDto.getMultipartFile());
        Complain complain=new Complain();
        complain.setId(complainDto.getId());
        complain.setAddress(complainDto.getAddress());
        complain.setCrime(complainDto.getCrime());
        complain.setCrimeDate(new SimpleDateFormat("yyyy-dd-mm").parse(complainDto.getCrimeDate()));
        complain.setComplainDate(new Date());
        complain.setUser(AuthorizeUser.getUser());
        if(complain.getId() == null){
            complain.setComplainStatus(EComplainStatus.PENDING);
        }else {
            complain.setComplainStatus(complainDto.getComplainStatus());
            complain.setUser(complainDto.getUser());
        }
        complain.setDescription(complainDto.getDescription());
        complain.setPhoto(responseDto.getMessage());
        complainRepository.save(complain);

        return complainDto;
    }

    @Override
    public List<ComplainDto> findAll() throws IOException {
        List<ComplainDto> complainList = new ArrayList<>();
        List<Complain> complainList1 = complainRepository.getComplainList(Math.toIntExact(AuthorizeUser.getUser().getId()));
        for (Complain complain : complainList1){
            complainList.add(ComplainDto.builder()
                    .id(complain.getId())
                    .address(complain.getAddress())
                    .crime(complain.getCrime())
                    .crimeDate(new SimpleDateFormat("yyyy-MM-dd").format(complain.getCrimeDate()))
                    .complainStatus(complain.getComplainStatus())
                    .complainDate(complain.getComplainDate())
                    .description(complain.getDescription())
                    .photo(fileStorageComponent.base64Encoded(complain.getPhoto()))
                    .user(complain.getUser())
                    .build());
        }
        return complainList;
    }

    @Override
    public ComplainDto findById(Long aLong) throws IOException {
        Complain complain;
        Optional<Complain> optionalComplain=complainRepository.findById(aLong);
        if (optionalComplain.isPresent())
        {
            complain=optionalComplain.get();
            return   ComplainDto.builder()
                    .id(complain.getId())
                    .address(complain.getAddress())
                    .crime(complain.getCrime())
                    .crimeDate(new SimpleDateFormat("yyyy-MM-dd").format(complain.getCrimeDate()))
                    .complainStatus(complain.getComplainStatus())
                    .complainDate(complain.getComplainDate())
                    .description(complain.getDescription())
                    .photo(fileStorageComponent.base64Encoded(complain.getPhoto()))
                    .user(complain.getUser())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        complainRepository.deleteById(aLong);

    }
    public List<Complain> getComplain()
    {
        return complainRepository.getComplain();
    }
}
