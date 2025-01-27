package com.demo.securedoc.service.impl;

import com.demo.securedoc.exception.ApiException;
import com.demo.securedoc.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import static com.demo.securedoc.utils.EmailUtils.getEmailMessage;
import static com.demo.securedoc.utils.EmailUtils.getResetPasswordEmail;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    private static final String PASSWORD_RESET_REQUEST = "Password Reset Request";
    private final JavaMailSender mailSender;
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String email, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, host, token));
            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Unable to send new email");
        }
    }




    @Override
    @Async
    public void sendResetPasswordEmail(String name, String email, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getResetPasswordEmail(name, host, token));
            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Unable to send new email");
        }
    }


}
