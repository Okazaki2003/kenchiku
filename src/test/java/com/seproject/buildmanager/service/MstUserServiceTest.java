package com.seproject.buildmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.seproject.buildmanager.entity.MstUser;
import com.seproject.buildmanager.form.MstUserForm;
import com.seproject.buildmanager.repository.MstUserRepository;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MstUserServiceTest {


  @Mock
  private MstUserRepository mstUserRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private MstUserService mstUserService;

  private MstUserForm mstUserForm;
  private MstUser mstUser;

  @BeforeEach
  public void setUp() {
    mstUserForm = new MstUserForm();
    mstUserForm.setLoginCd("testLogin");
    mstUserForm.setPassword("password");
    mstUserForm.setLName("Test");
    mstUserForm.setFName("User");
    mstUserForm.setLNameKana("テスト");
    mstUserForm.setFNameKana("ユーザー");
    mstUserForm.setTel("1234567890");
    mstUserForm.setEmail("test@example.com");
    mstUserForm.setStatus("1");

    mstUser = new MstUser();
    mstUser.setId(1);
    mstUser.setLoginCd("testLogin");
    mstUser.setPassword("encodedPassword");
    mstUser.setLName("Test");
    mstUser.setFName("User");
    mstUser.setLNameKana("テスト");
    mstUser.setFNameKana("ユーザー");
    mstUser.setTel("1234567890");
    mstUser.setEmail("test@example.com");
    mstUser.setStatus(1);
    mstUser.setCreatedAt(LocalDateTime.now());
  }

  @Test
  public void testGetAllUsers() {
    List<MstUser> userList = Arrays.asList(mstUser);
    when(mstUserRepository.findAll()).thenReturn(userList);

    List<MstUser> result = mstUserService.getAllUsers();
    assertEquals(1, result.size());
    verify(mstUserRepository, times(1)).findAll();
  }

  @Test
  public void testShowUserForm() {
    MstUserForm result = mstUserService.showUserForm();
    assertEquals(new MstUserForm(), result);
  }

  @Test
  public void testSaveUser() {
    when(passwordEncoder.encode(mstUserForm.getPassword())).thenReturn("encodedPassword");
    when(mstUserRepository.save(any(MstUser.class))).thenReturn(mstUser);

    MstUser result = mstUserService.saveUser(mstUserForm);

    assertEquals(mstUser.getLoginCd(), result.getLoginCd());
    assertEquals(mstUser.getPassword(), result.getPassword());
    assertEquals(mstUser.getLName(), result.getLName());
    assertEquals(mstUser.getFName(), result.getFName());
    assertEquals(mstUser.getLNameKana(), result.getLNameKana());
    assertEquals(mstUser.getFNameKana(), result.getFNameKana());
    assertEquals(mstUser.getTel(), result.getTel());
    assertEquals(mstUser.getEmail(), result.getEmail());
    assertEquals(mstUser.getStatus(), result.getStatus());

    verify(passwordEncoder, times(1)).encode(mstUserForm.getPassword());
    verify(mstUserRepository, times(1)).save(any(MstUser.class));
  }

}
