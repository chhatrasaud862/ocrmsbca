package com.ocrms.ocrmsbca.config;

import com.ocrms.ocrmsbca.entity.role.Role;
import com.ocrms.ocrmsbca.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 12/08/2022
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Role role=roleRepository.getRoleByRoleName(username);
        if(role==null){
            throw new UsernameNotFoundException("Could not found user!!");
        }
        CustomUserDetails customUserDetail=new CustomUserDetails(role);
        return customUserDetail;
    }
}
