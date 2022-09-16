package com.ocrms.ocrmsbca.repository.complain;

import com.ocrms.ocrmsbca.entity.complain.Complain;
import com.ocrms.ocrmsbca.entity.user.User;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("FROM Complain as c WHERE c.user.id=:userId")
    public Page<Complain> getComplainList(@Param("userId") Long userId, Pageable pageable);

    @Query( value = "select * from tbl_complain order by id",nativeQuery = true)
    public Page<Complain> getTotalComplain(Pageable pageable);

    @Query(value = "SELECT count(uc.complain_status) from tbl_complain uc inner join tbl_user u on u.id=uc.user_id where uc.user_id=:userId",nativeQuery = true)
    Long getComplainByComplainStatus(Long userId);

    @Query(value = "SELECT count(uc.complain_status)from tbl_complain uc inner join tbl_user u on u.id=uc.user_id where uc.user_id=:userId  and uc.complain_status= 0",nativeQuery = true)
    Long getComplainByComplainStatus_Pending(Long userId);

    @Query(value = "select count(uc.complain_status)from tbl_complain uc inner join tbl_user u on u.id=uc.user_id where uc.user_id=:userId  and uc.complain_status= 1",nativeQuery = true)
    Long getComplainByComplainStatus_Approve(Long userId);

    @Query(value = "select count(uc.complain_status)from tbl_complain uc inner join tbl_user u on u.id=uc.user_id where uc.user_id=:userId and uc.complain_status= 2",nativeQuery = true)
    Long getComplainByComplainStatus_Reject(Long userId);



    @Query(value = "SELECT * FROM user_complain u WHERE u.register_id=?1 and u.complain_status=1",nativeQuery = true)
    List<Complain> getVerifiedStatus(Integer userId);

    @Query(value = "select count(uc.complain_status) from tbl_complain uc",nativeQuery = true)
    String getTotalComplain();

    @Query(value = "select count(uc.complain_status)from tbl_complain uc where uc.complain_status= 0",nativeQuery = true)
    String getPendingComplain();

    @Query(value = "select count(uc.complain_status)from tbl_complain uc where uc.complain_status=1",nativeQuery = true)
    String getApproveComplain();

    @Query(value = "select count(uc.complain_status)from tbl_complain uc where uc.complain_status=2",nativeQuery = true)
    String getRejectComplain();

    @Query(value = "select * from tbl_complain u  order by u.id",nativeQuery = true)
    List<Complain> getComplainDetails();

    @Query(value = "select name from tbl_complain tc inner join tbl_user u on u.id=tc.user_id where tc.user_id=:userId and tc.complain_status=1",nativeQuery = true)
    String getComplainByAndUserName(Long userId);
}
