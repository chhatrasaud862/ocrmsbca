package com.ocrms.ocrmsbca.repository.complain;

import com.ocrms.ocrmsbca.entity.complain.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Repository
public interface ComplainRepository extends JpaRepository<Complain,Long> {
    @Query(value = "SELECT * FROM tbl_complain u WHERE u.user_id = ?1 order by u.id", nativeQuery = true)
    List<Complain> getComplainList(Integer userId);

    @Query(value = "SELECT * FROM user_complain u WHERE u.register_id=?1 and u.complain_status=1",nativeQuery = true)
    List<Complain> getVerifiedStatus(Integer userId);

    @Query(value = "select count(uc.complain_status) from user_complain uc",nativeQuery = true)
    int getTotalComplain();

    @Query(value = "select count(uc.complain_status)from user_complain uc where uc.complain_status= 0",nativeQuery = true)
    String getPendingComplain();

    @Query(value = "select count(uc.complain_status)from user_complain uc where uc.complain_status=1",nativeQuery = true)
    String getApproveComplain();

    @Query(value = "select * from user_complain u  order by u.id",nativeQuery = true)
    List<Complain> getComplainDetails();

    @Query(value = "select uc.register_id from user_complain uc where id=uc.id",nativeQuery = true)
    List<Complain>getRegister(Integer userId);
}
