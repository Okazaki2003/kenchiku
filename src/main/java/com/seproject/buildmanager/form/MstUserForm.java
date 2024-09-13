package com.seproject.buildmanager.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MstUserForm {

  private Integer id;

  @NotBlank(message = "Login Code is required")
  @Size(max = 15, message = "Login Code must be 15 characters or less")
  private String loginCd;

  @NotBlank(message = "Password is required")
  private String password;

  private String roles;

  @Size(max = 20, message = "Last Name must be 20 characters or less")
  private String lName;

  @Size(max = 20, message = "First Name must be 20 characters or less")
  private String fName;

  @Size(max = 20, message = "Last Name Kana must be 20 characters or less")
  private String lNameKana;

  @Size(max = 20, message = "First Name Kana must be 20 characters or less")
  private String fNameKana;

  @Size(max = 14, message = "Tel must be 14 characters or less")
  private String tel;

  @Email(message = "Email should be valid")
  @Size(max = 100, message = "Email must be 100 characters or less")
  private String email;

  private String status;

  private String transactionToken;

}
