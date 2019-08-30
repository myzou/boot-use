package com.example.boot.mail.service;

/**
 * @Author dyzhan
 * @Description //TODO
 * @Date 17:12 2019/6/20
 * @Param
 * @return
 **/
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
