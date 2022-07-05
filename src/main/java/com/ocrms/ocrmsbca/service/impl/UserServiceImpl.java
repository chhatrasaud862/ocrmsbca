package com.ocrms.ocrmsbca.service.impl;

import com.ocrms.ocrmsbca.Enum.ERole;
import com.ocrms.ocrmsbca.dto.UserDto;
import com.ocrms.ocrmsbca.entity.role.Role;
import com.ocrms.ocrmsbca.entity.user.User;
import com.ocrms.ocrmsbca.repository.role.RoleRepository;
import com.ocrms.ocrmsbca.repository.user.UserRepository;
import com.ocrms.ocrmsbca.service.user.UserService;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
   private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto save(UserDto userDto){
        User user = new User();
        Role role=new Role();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setContact(userDto.getContact());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(user.getRoles());
        role.setId(userDto.getId());
        userRepository.save(user);

        role.setEmail(userDto.getEmail());
        role.setPassword(passwordEncoder.encode(userDto.getPassword()));
        role.setRole(ERole.ROLE_USER);
        roleRepository.save(role);
     return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto findById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
