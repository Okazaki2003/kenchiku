package com.seproject.buildmanager.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mst_auth")
@Data
@NoArgsConstructor
public class MstAuth {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "role_name")
  private String roleName;

  @Column(name = "status")
  private Integer status;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedAt;

  @Column(name = "updated_mst_user_id")
  private Integer updatedMstUserId;
}
