package com.seproject.buildmanager.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class menuController {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

  @GetMapping({"/menu"})
  private String menu() {

    logger.info("--- menuController.menu START ---");

    logger.info("--- menuController.menu END ---");
    return "menu";
  }
}
