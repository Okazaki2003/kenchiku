package com.seproject.buildmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "mst_user")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "auth_with_all_associations", includeAllAttributes = true)
public class MstUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "login_cd", nullable = false)
  private String loginCd;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "mst_auth_id", nullable = false)
  private Integer mstAuthId;

  @Column(name = "l_name")
  private String lName;

  @Column(name = "f_name")
  private String fName;

  @Column(name = "l_name_kana")
  private String lNameKana;

  @Column(name = "f_name_kana")
  private String fNameKana;

  @Column(name = "tel")
  private String tel;

  @Column(name = "email")
  private String email;

  @Column(name = "status")
  private Integer status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "updated_mst_user_id")
  private Integer updatedMstUserId;

  // MstAuthのnameが入る
  @Column(name = "role")
  private String role;

}
