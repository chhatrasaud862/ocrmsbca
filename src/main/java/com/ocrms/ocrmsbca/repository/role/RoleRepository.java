package com.ocrms.ocrmsbca.repository.role;

import com.ocrms.ocrmsbca.Enum.ERole;
import com.ocrms.ocrmsbca.entity.role.Role;
import com.ocrms.ocrmsbca.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 05/07/2022
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findUserByEmail(String email);
    @Query(value = "SELECT * FROM roles r WHERE r.id=?1 and r.role = 1", nativeQuery = true)
    List<User> userList(Integer userId);
}
