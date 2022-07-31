package com.ocrms.ocrmsbca.dto;

import lombok.*;

import java.util.Set;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String contact;
    private String password;
    private Set<String> role;
}
