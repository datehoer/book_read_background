package com.datehoer.bookapi.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.extra.mail.MailUtil;
import com.datehoer.bookapi.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    public void checkMail(String to, String subject, String content){
        Assert.notNull(to, "邮件收件人不能为空");
        Assert.notNull(subject, "邮件主题不能为空");
        Assert.notNull(content, "邮件收件人不能为空");
    }

    @Override
    public boolean sendSimpleMail(String to, String subject, String content) {
        try{
            String[] toSplit = to.split(",");
            List<String> toList = Arrays.asList(toSplit);
            MailUtil.send(toList, subject, content, false);
            return true;
        }catch (Exception e){
            logger.error("发送邮件失败", e);
            return false;
        }
    }

}
