package com.ocrms.ocrmsbca.dto;

import lombok.*;

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
public class AdminDto {
    private Long id;
    private String name;
    private String email;
    private String password;
}
