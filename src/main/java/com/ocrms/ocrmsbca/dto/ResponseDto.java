package com.ocrms.ocrmsbca.dto;

import lombok.*;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 06/07/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private boolean status;
    private String message;
}
