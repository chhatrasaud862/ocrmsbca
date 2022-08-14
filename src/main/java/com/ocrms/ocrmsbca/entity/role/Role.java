package com.ocrms.ocrmsbca.entity.role;

import lombok.*;

import javax.persistence.*;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 05/07/2022
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "roles",uniqueConstraints = {
        @UniqueConstraint(name="unique_Role_email",columnNames = "email")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(length = 20)
    private String role;

}
