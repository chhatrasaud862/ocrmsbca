package com.ocrms.ocrmsbca.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
@Valid
public class AdminDto {
    private Long id;

    @NotEmpty(message = "Name can not be empty !")
    private String name;

    @Email(message = "Enter valid e-mail" )
    private String email;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
            message = "Enter valid password")
    private String password;
}
