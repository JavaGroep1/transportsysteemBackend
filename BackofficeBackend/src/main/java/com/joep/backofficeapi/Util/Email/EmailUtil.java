package com.joep.backofficeapi.Util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class EmailUtil {


    @Autowired
    private  JavaMailSender javaMailSender;

    public void sendEmail(String[] to, String subject, String body) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);

    }
}
