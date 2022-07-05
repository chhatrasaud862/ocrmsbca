package com.ocrms.ocrmsbca.entity.complain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_complain")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
