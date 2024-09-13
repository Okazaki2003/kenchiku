package com.seproject.buildmanager.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.seproject.buildmanager.entity.MstUser;
import java.util.List;
import java.util.Optional;

public interface MstUserRepository extends JpaRepository<MstUser, Integer> {

  @EntityGraph(value = "auth_with_all_associations", type = EntityGraph.EntityGraphType.FETCH)
  public List<MstUser> findAll();

  @Query(
      value = "SELECT u.id AS id, u.login_cd, u.password, u.mst_auth_id, GROUP_CONCAT(a.name) AS role, u.l_name, u.f_name, u.l_name_kana, u.f_name_kana, u.tel, u.email, u.status, u.created_at, u.updated_at, u.updated_mst_user_id "
          + "FROM mst_user u " + "LEFT JOIN mst_auth a ON u.mst_auth_id = a.id "
          + "WHERE u.login_cd = :loginCd "
          + "GROUP BY u.id, u.login_cd, u.password, u.l_name, u.f_name, u.l_name_kana, u.f_name_kana, u.tel, u.email, u.status, u.created_at, u.updated_at, u.updated_mst_user_id",
      nativeQuery = true)
  Optional<MstUser> findByLoginCd(@Param("loginCd") String loginCd);

}
