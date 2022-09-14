package com.ocrms.ocrmsbca.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Enter the Name")
    @NotEmpty(message = "Name can not be empty !")
    private String name;

    @NotEmpty
    @Email
    private String email;

    private String contact;
    private String password;
    private Set<String> role;
}
