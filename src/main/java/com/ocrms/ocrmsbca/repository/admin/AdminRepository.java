package com.ocrms.ocrmsbca.repository.admin;

import com.ocrms.ocrmsbca.entity.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    public Admin findAdminRegisterByEmail(String email);
}
