package com.movify.service.impl;

import com.movify.service.CacheService;
import com.movify.service.EmailService;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.List;

public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Inject
    SimpleEmail simpleEmail;

    @Inject
    CacheService cacheService;

    public void sendEmail(String to, String toName, String subject, String content) {
        new Thread(() -> {
            try {
                List<InternetAddress> emails = Arrays.asList(InternetAddress.parse(to));
                this.simpleEmail.setMsg("Hello " + toName + ",\n\n" + content)
                    .setFrom("60bitts@gmail.com", "Movify")
                    .setSubject(subject)
                    .setTo(emails)
                    .send();
            } catch (Exception ex) {
                LOGGER.info(String.format("Unable to send email to %s, no internet", to));
            }
        }).start();
    }

}


