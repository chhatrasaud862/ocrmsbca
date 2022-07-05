package com.ocrms.ocrmsbca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class AdminDto {
    private Long id;
    private String email;
    private String password;
}
