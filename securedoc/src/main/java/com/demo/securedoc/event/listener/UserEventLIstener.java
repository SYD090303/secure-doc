package com.demo.securedoc.event.listener;


import com.demo.securedoc.event.UserEvent;
import com.demo.securedoc.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class UserEventLIstener {
    private EmailService emailService;


    @EventListener
    public void onUserEvent(UserEvent event) {
        switch (event.getEventType()) {
            case REGISTRATION ->
                    emailService.sendNewAccountEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String) event.getData().get("key"));
            case RESETPASSWORD -> emailService.sendResetPasswordEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String) event.getData().get("key"));
            default -> System.out.println("Invalid event type");
        }
    }
}
