package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.Enum.ERole;
import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.role.Role;
import com.ocrms.ocrmsbca.repository.admin.AdminRepository;
import com.ocrms.ocrmsbca.repository.role.RoleRepository;
import com.ocrms.ocrmsbca.service.admin.AdminService;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminDto save(AdminDto adminDto) {
        Admin admin=new Admin();
        Role role=new Role();
        admin.setId(adminDto.getId());
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        adminRepository.save(admin);

        role.setId(adminDto.getId());
        role.setName(adminDto.getName());
        role.setEmail(adminDto.getEmail());
        role.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        role.setRole(ERole.ROLE_ADMIN);
        roleRepository.save(role);
        return adminDto;
    }

    @Override
    public List<AdminDto> findAll()  {
        List<AdminDto> adminDtoList=new ArrayList<>();
        List<Admin> adminList=adminRepository.findAll();
        for (Admin admin:adminList)
        {
            adminDtoList.add(AdminDto.builder()
                            .id(admin.getId())
                            .name(admin.getName())
                            .email(admin.getEmail())
                    .build());
        }
        return adminDtoList;
    }

    @Override
    public AdminDto findById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
    public Admin findAdminByEmail(String email){
        return adminRepository.findAdminRegisterByEmail(email);
    }
}
