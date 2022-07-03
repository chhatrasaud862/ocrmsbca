package com.ocrms.ocrmsbca.repository.user;

import com.ocrms.ocrmsbca.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
