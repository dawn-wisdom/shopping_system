package dao;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMailDao {
    public static boolean sendEmail(String toEmail, String subject, String messageContent) {
        String host = "smtp.qq.com";  // QQ 邮箱的 SMTP 服务器
        final String username = "1466840216@qq.com";  // 发件人邮箱
        final String password = "fvbrdlihikwyihdg";      // 授权码（不是邮箱密码）

        // 设置 SMTP 配置
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");  // 使用 SSL 端口 465
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");  // 启用 SSL

        // 获取会话并认证
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件已发送成功！");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMail2Customer(String reciever) {
        String subject = "您的商品已经发货，请注意查收";
        String messageContent = "您的商品已发货，预计三天内送达，请您注意查收";

        if(sendEmail(reciever, subject, messageContent))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

