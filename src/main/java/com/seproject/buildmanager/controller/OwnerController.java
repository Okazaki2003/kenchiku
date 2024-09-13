// package com.seproject.buildmanager.controller;
//
// import java.util.UUID;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import com.seproject.buildmanager.common.Constants;
// import com.seproject.buildmanager.config.TransactionTokenCheck;
// import com.seproject.buildmanager.form.MstUserForm;
// import com.seproject.buildmanager.service.MstOwnerService;
// import jakarta.servlet.http.HttpSession;
//
// public class OwnerController {
// private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);
//
// @Autowired
// private MstOwnerService mstOwnerService;
//
// @GetMapping("")
// public String getAllUsers(Model model) {
//
// logger.info("--- UserController.getAllUsers START ---");
//
// model.addAttribute("users", mstOwnerService.getAllUsers());
// model.addAttribute("statusTrue", Constants.STATUS_TRUE);
//
// logger.info("--- UserController.getAllUsers END ---");
// return "user/list";
// }
//
// @GetMapping("register")
// public String showUserForm(HttpSession session, Model model) {
//
// logger.info("--- UserController.showUserForm START ---");
//
// String transactionToken = UUID.randomUUID().toString();
// session.setAttribute("transactionToken", transactionToken);
// model.addAttribute("transactionToken", transactionToken);
//
// model.addAttribute("mstUser", mstOwnerService.showUserForm());
//
// logger.info("--- UserController.showUserForm END ---");
//
// return "user/register";
// }
//
//
// @PostMapping("register")
// public String processRegistration(MstUserForm mstUserForm, Model model) {
//
// logger.info("--- UserController.processRegistration START ---");
//
// model.addAttribute("mstUserForm", mstUserForm);
//
// logger.info("--- UserController.processRegistration END ---");
// return "user/register_confirm";
// }
//
// @PostMapping("save")
// @TransactionTokenCheck("save")
// public String saveUser(@ModelAttribute("mstUserForm") MstUserForm mstUserForm) {
//
// logger.info("--- UserController.saveUser START ---");
//
// mstOwnerService.saveUser(mstUserForm);
//
// logger.info("--- UserController.saveUser END ---");
// return "redirect:/user/save";
// }
//
// @GetMapping("save")
// public String createComplete() {
// return "user/register_end";
// }
// }
