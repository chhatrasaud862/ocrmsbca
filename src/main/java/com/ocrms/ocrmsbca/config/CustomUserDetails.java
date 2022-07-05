package com.ocrms.ocrmsbca.config;


import com.ocrms.ocrmsbca.components.AuthorizeUser;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.role.Role;
import com.ocrms.ocrmsbca.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final Role role;

    public CustomUserDetails(Role role) {
        this.role = role;
        if (role.getRole().ordinal() == 0) {
            User user = new User();
            user.setEmail(role.getEmail());
            AuthorizeUser.setUser(user);

        } else {
            Admin admin = new Admin();
            admin.setEmail(role.getEmail());
            AuthorizeUser.setAdmin(admin);
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    //get the user password
    @Override
    public String getPassword() {
        return role.getPassword();
    }

    //get teh email
    @Override
    public String getUsername() {
        return role.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
