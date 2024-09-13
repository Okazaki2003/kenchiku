package com.seproject.buildmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import com.seproject.buildmanager.service.EmailService;
import com.seproject.buildmanager.service.MstUserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/")
@Controller
public class IndexController {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);


  @Autowired
  private EmailService emailService;

  @GetMapping({"/"})
  private String index(@RequestParam(value = "name", defaultValue = "World") String name) {

    logger.info("--- IndexController.index START ---");

    logger.info("--- IndexController.index END ---");
    return "index";
  }

  @GetMapping("/sendHtmlEmail")
  public String sendHtmlEmail() {

    try {
      emailService.sendHtmlEmail("takano@se-project.co.jp", "BuildManagerタイトルsendHtmlEmail",
          "BODYsendHtmlEmail", null);
    } catch (MessagingException e) {
      e.printStackTrace();
      logger.info(e.getMessage());
    }
    return "index";
  }

  @GetMapping("/sendEmail")
  public String sendEmail() {

    emailService.sendSimpleEmail("takano@se-project.co.jp", "BuildManagerタイトルsendEmail",
        "BODYsendEmail");

    return "index";

  }


  @Value("${file.upload-dir}")
  private String uploadDir;

  @PostMapping("/fileupload")
  public String fileupload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:/";
    }

    try {
      // Get the file and save it to the upload directory
      byte[] bytes = file.getBytes();
      Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
      Files.write(path, bytes);

      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + file.getOriginalFilename() + "'");

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/";

  }

  @GetMapping("/download")
  public String downloadFile(HttpServletResponse response) {

    String originFilePath = "/app/uploads/favicon.png";
    String outputFileName = "favicon.png";

    String CONTENT_DISPOSITION_FORMAT = "attachment; filename=\"%s\"; filename*=UTF-8''%s";
    outputFileName = String.format(CONTENT_DISPOSITION_FORMAT, outputFileName,
        UriUtils.encode(outputFileName, StandardCharsets.UTF_8.name()));
    try (OutputStream os = response.getOutputStream();) {
      Path filePath = Path.of(originFilePath);
      byte[] fb = Files.readAllBytes(filePath);
      response.setContentType("application/octet-stream");
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION, outputFileName);
      response.setContentLength(fb.length);
      os.write(fb);
      os.flush();
      // Files.delete(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/";


  }


}
