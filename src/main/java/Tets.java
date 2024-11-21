import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Tets {
    public static void sendEmail(String toEmail, String subject, String messageContent) {
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
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String toEmail = "fishyphhhh@gmail.com";  // 收件人邮箱
        String subject = "测试邮件";
        String messageContent = "这是一封通过 QQ 邮箱发送的测试邮件。";

        sendEmail(toEmail, subject, messageContent);
    }
}
