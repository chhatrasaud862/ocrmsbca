package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.Enum.ERole;
import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.role.Role;
import com.ocrms.ocrmsbca.repository.admin.AdminRepository;
import com.ocrms.ocrmsbca.repository.role.RoleRepository;
import com.ocrms.ocrmsbca.service.admin.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        adminRepository.save(admin);

        role.setId(adminDto.getId());
        role.setEmail(adminDto.getEmail());
        role.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        role.setRole(ERole.ROLE_ADMIN);
        roleRepository.save(role);
        return adminDto;
    }

    @Override
    public List<AdminDto> findAll()  {
        return null;
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
