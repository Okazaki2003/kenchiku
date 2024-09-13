// package com.seproject.buildmanager.service;
//
// import java.time.LocalDateTime;
// import java.util.List;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import com.seproject.buildmanager.entity.MstUser;
// import com.seproject.buildmanager.form.MstUserForm;
// import com.seproject.buildmanager.repository.MstUserRepository;
//
// public class MstOwnerService {
//
// private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);
//
// @Autowired
// private MstUserRepository mstUserRepository;
//
// @Autowired
// private PasswordEncoder passwordEncoder;
//
//
// public List<MstUser> getAllUsers() {
//
// logger.info("--- MstUserService.getAllUsers START ---");
//
// List<MstUser> users = mstUserRepository.findAll();
//
// logger.info("--- MstUserService.getAllUsers END ---");
//
// return users;
//
// }
//
// public MstUserForm showUserForm() {
//
// logger.info("--- MstUserService.showUserForm START ---");
//
// MstUserForm tmp = new MstUserForm();
//
// logger.info("--- MstUserService.showUserForm END ---");
//
// return tmp;
//
// }
//
// public MstUser saveUser(MstUserForm mstUserForm) {
//
// logger.info("--- MstUserService.saveUser START ---");
//
// MstUser tmp = new MstUser();
// tmp.setLoginCd(mstUserForm.getLoginCd());
// tmp.setPassword(passwordEncoder.encode(mstUserForm.getPassword()));
// // tmp.setRoles("ROLE_ADMIN"); //TODO:権限は未対応
// tmp.setLName(mstUserForm.getLName());
// tmp.setFName(mstUserForm.getFName());
// tmp.setLNameKana(mstUserForm.getLNameKana());
// tmp.setFNameKana(mstUserForm.getFNameKana());
// tmp.setTel(mstUserForm.getTel());
// tmp.setEmail(mstUserForm.getEmail());
// try {
// tmp.setStatus(Integer.valueOf(mstUserForm.getStatus()));
// } catch (NumberFormatException e) {
// tmp.setStatus(1);
// }
// tmp.setCreatedAt(LocalDateTime.now());
// // TODO:登録ユーザは未対応
// MstUser result = mstUserRepository.save(tmp);
//
// logger.info("--- MstUserService.saveUser END ---");
// return result;
//
// }
//
// }
