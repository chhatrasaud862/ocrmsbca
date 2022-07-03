package com.ocrms.ocrmsbca.repository.complain;

import com.ocrms.ocrmsbca.entity.complain.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Repository
public interface ComplainRepository extends JpaRepository<Complain,Long> {
}
