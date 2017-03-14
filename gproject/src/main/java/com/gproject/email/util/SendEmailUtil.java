package com.gproject.email.util;

import freemarker.template.TemplateException;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * modify by hezishan
 *
 * @Description:TODO 邮件发送工具类
 * @time:2016年10月8日 下午4:57:43
 */
public class SendEmailUtil {

    /**
     * 邮件发送的方法
     *
     * @param to         收件人
     * @param title      标题
     * @param subject    主题
     * @param content    内容
     * @param smtp       协议
     * @param host       发送服务器服务器
     * @param sendName   邮件发送人
     * @param sendPort   邮件发送人端口
     * @param userName   邮件发送人名
     * @param userPwd    邮件发送人密码
     * @param attachment 附件路径
     * @return 成功或失败
     */
    public static boolean sendWithAttachment(String to, String title, String subject, String content, String smtp, String host,
                                             String sendName, String sendPort, String userName, String userPwd, String attachment) {

        // 第一步：创建Session
        Properties props = new Properties();
        // 指定邮件的传输协议，smtp(Simple Mail Transfer Protocol 简单的邮件传输协议)
        props.put("mail.transport.protocol", smtp);
        // 指定邮件发送服务器服务器 "smtp.qq.com"
        props.put("mail.host", host);
        // 指定邮件的发送人(您用来发送邮件的服务器，比如您的163\sina等邮箱)
        props.put("mail.from", sendName);
        if (true) {
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.port", sendPort);
        }
        Authentication authentication = new Authentication(userName, userPwd);
        Session session = Session.getDefaultInstance(props, authentication);

        // 开启调试模式
        session.setDebug(true);
        try {
            // 第二步：获取邮件发送对象
            Transport transport = session.getTransport();
            // 连接邮件服务器，链接您的163、sina邮箱，用户名（不带@163.com，登录邮箱的邮箱账号，不是邮箱地址）、密码
            transport.connect(userName, userPwd);
            Address toAddress = new InternetAddress(to);

            // 第三步：创建邮件消息体
            MimeMessage message = new MimeMessage(session);
            //设置自定义发件人昵称
            String nick = "";
            try {
                nick = MimeUtility.encodeText(title);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setFrom(new InternetAddress("BuyShop" + " <" + sendName + ">"));
            //设置发信人
            //message.setFrom(new InternetAddress(sendName));
            // 邮件的主题
            message.setSubject(subject);
            //收件人
            message.addRecipient(Message.RecipientType.TO, toAddress);

            Multipart mainPart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            //设置HTML内容  
            messageBodyPart.setContent(content, "text/html; charset=utf-8");
            mainPart.addBodyPart(messageBodyPart);
            message.setContent(mainPart);
            // 添加附件的内容
            if ( null!= attachment &&  ""!= attachment) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                URL url = new URL(attachment);
                URLDataSource uds = new URLDataSource(url);
                attachmentBodyPart.setDataHandler(new DataHandler(uds));
               // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord("attachment.jpg"));
                mainPart.addBodyPart(attachmentBodyPart);
            }
            // 邮件发送时间
            message.setSentDate(new Date());

            // 第四步：发送邮件
            // 第一个参数：邮件的消息体
            // 第二个参数：邮件的接收人，多个接收人用逗号隔开（test1@163.com,test2@sina.com）
            transport.sendMessage(message, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        Map<String, String> root = new HashMap<String, String>();
        root.put("vercode", "124345");
        // type=1 ---注册  2----忘记密码

         String  content = TemplateConfig.getTemplate("textRegister", root);

        SendEmailUtil.sendWithAttachment("1546477188@qq.com", "GMC Verification Code", "GMC Verification Code", content, "smtp", "smtp.163.com", "yu18320304743@163.com", "465", "yu18320304743", "yuhuang0119", "attachment.jpg");
    }
}

class Authentication extends Authenticator {
    String username = null;
    String password = null;

    public Authentication() {
    }

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        PasswordAuthentication pa = new PasswordAuthentication(username, password);
        return pa;
    }
}