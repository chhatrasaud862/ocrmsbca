package com.ocrms.ocrmsbca.entity.admin;

import lombok.*;

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
@Builder
@Table(name="tbl_admin" ,uniqueConstraints = {
        @UniqueConstraint(name = "unique_Admin_email",columnNames = "email")
})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;
}
