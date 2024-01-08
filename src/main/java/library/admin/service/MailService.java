package library.admin.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "gtd8983@gmail.com";
    private static String number;

    // 인증코드 생성
    public static void createNumber(){
        number = UUID.randomUUID().toString().substring(0,8);
    }

    public String sendMail(String mail){
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);
        return number;
    }

    public MimeMessage createMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO,mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8","html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }



}
