package com.ocrms.ocrmsbca.entity.role;

import com.ocrms.ocrmsbca.Enum.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 05/07/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;
}
