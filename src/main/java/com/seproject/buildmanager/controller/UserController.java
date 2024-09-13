package com.seproject.buildmanager.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.seproject.buildmanager.common.Constants;
import com.seproject.buildmanager.config.TransactionTokenCheck;
import com.seproject.buildmanager.form.MstUserForm;
import com.seproject.buildmanager.service.MstUserService;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("user")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

  @Autowired
  private MstUserService mstUserService;

  @GetMapping("")
  public String getAllUsers(Model model) {

    logger.info("--- UserController.getAllUsers START ---");

    model.addAttribute("users", mstUserService.getAllUsers());
    model.addAttribute("statusTrue", Constants.STATUS_TRUE);

    logger.info("--- UserController.getAllUsers END ---");
    return "user/list";
  }

  @GetMapping("register")
  public String showUserForm(HttpSession session, Model model) {

    logger.info("--- UserController.showUserForm START ---");

    String transactionToken = UUID.randomUUID().toString();
    session.setAttribute("transactionToken", transactionToken);
    model.addAttribute("transactionToken", transactionToken);

    model.addAttribute("mstUser", mstUserService.showUserForm());

    logger.info("--- UserController.showUserForm END ---");

    return "user/register";
  }


  @PostMapping("register")
  public String processRegistration(MstUserForm mstUserForm, Model model) {

    logger.info("--- UserController.processRegistration START ---");

    model.addAttribute("mstUserForm", mstUserForm);

    logger.info("--- UserController.processRegistration END ---");
    return "user/register_confirm";
  }

  @PostMapping("save")
  @TransactionTokenCheck("save")
  public String saveUser(@ModelAttribute("mstUserForm") MstUserForm mstUserForm) {

    logger.info("--- UserController.saveUser START ---");

    mstUserService.saveUser(mstUserForm);

    logger.info("--- UserController.saveUser END ---");
    return "redirect:/user/save";
  }

  @GetMapping("save")
  public String createComplete() {
    return "user/register_end";
  }
}
