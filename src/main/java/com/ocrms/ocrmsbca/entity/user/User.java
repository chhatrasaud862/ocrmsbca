package com.ocrms.ocrmsbca.entity.user;

import com.ocrms.ocrmsbca.entity.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
@Builder
@Table(name="tbl_user",uniqueConstraints = {
        @UniqueConstraint(name ="unique_User_email",columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name = "contact",nullable = false)
    private String contact;

    @Column(name="password",nullable = false)
    private String password;
}
