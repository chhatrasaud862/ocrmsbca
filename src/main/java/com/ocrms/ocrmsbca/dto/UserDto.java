package com.ocrms.ocrmsbca.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
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
@Valid
public class UserDto {
    private Long id;

    @NotEmpty(message = "Name can not be empty !")
    private String name;

    @Email(message = "Enter valid e-mail" )
    private String email;

    @NotBlank(message = "mobileNumber is required")
    @Size(min = 10, max = 10)
    @Length(max = 10)
    private String contact;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
            message = "Enter valid password")
    private String password;

    private Set<String> role;
}
