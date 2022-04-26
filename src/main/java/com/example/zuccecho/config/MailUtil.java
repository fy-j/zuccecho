package com.example.zuccecho.config;


import com.example.zuccecho.form.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class MailUtil {

    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(MessageDto messageDto){
        try{
            MimeMessage mimeMessage  = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(new InternetAddress(MAIL_SENDER,"zuccecho","UTF-8"));
            String[] rec = messageDto.getRecipient().toArray(new String[messageDto.getRecipient().size()]);
            helper.setTo(rec);
            helper.setSubject(messageDto.getSubject());
            helper.setText(messageDto.getContent(),true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }

    }


}
