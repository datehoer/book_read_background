package com.datehoer.bookapi.controller.common;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.datehoer.bookapi.common.PublicResponse;
import com.datehoer.bookapi.service.MailService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping()
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private RedissonClient redissonClient;
    public static final String CAPTCHA_CODE_KEY = "mail_code:";
    private static String generateVerificationCode(int length) {
        String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            code.append(charPool.charAt(index));
        }

        return code.toString();
    }
    @RequestMapping("/sendMailCode")
    public PublicResponse<JSONObject> sendMail(@RequestParam("to") String to)
    {
        String code = generateVerificationCode(6);
        String uuid = IdUtil.simpleUUID();
        String verifyKey = CAPTCHA_CODE_KEY + uuid;
        String subject = "验证码";
        String content = "您的验证码为："+ code;
        mailService.sendSimpleMail(to, subject, content);
        JSONObject jsonObject = new JSONObject();
        redissonClient.getBucket(verifyKey).set(code);
        jsonObject.set("uuid", uuid);
        return PublicResponse.success(jsonObject);
    }
}
