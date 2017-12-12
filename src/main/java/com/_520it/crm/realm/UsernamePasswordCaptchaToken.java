package com._520it.crm.realm;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Component;

/**
 * 扩展默认的用户认证的bean为：UsernamePasswordCaptchaToken.java
 */
@Component
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 1L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public UsernamePasswordCaptchaToken() {
        super();

    }

    public UsernamePasswordCaptchaToken(String username, char[] password,boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        //登录验证码
        this.captcha = captcha;
    }
}
