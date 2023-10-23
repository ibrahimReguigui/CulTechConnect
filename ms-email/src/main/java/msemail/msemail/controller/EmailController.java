package msemail.msemail.controller;

import jakarta.validation.Valid;
import msemail.msemail.entities.EmailDto;
import msemail.msemail.entities.EmailModel;
import msemail.msemail.service.EmailService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        String emailContent = getEmailContent(emailModel);
        emailModel.setText(emailContent);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }


    private String getEmailContent(EmailModel emailModel) {
        Context context = new Context();
        context.setVariable("emailModel", emailModel);
        return templateEngine.process("email-template", context);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}
