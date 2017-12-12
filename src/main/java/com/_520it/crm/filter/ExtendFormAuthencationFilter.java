package com._520it.crm.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 扩展原始默认的过滤为：FormAuthenticationCaptchaFilter.java
 * 重新加入字段captchaParam= "captcha";
 */
@Component
public class ExtendFormAuthencationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // 校验验证码
        // 从session获取正确的验证码
        HttpSession session = ((HttpServletRequest)request).getSession();
        //页面输入的验证码
        String captcha = request.getParameter("captcha");
        //从session中取出验证码
        String validateCode = (String) session.getAttribute("KEY_CAPTCHA");
        if (captcha!=null&&validateCode!=null&&!captcha.equals(validateCode)) {
            // CaptchaException表示验证码错误
            request.setAttribute("shiroLoginFailure", "CaptchaException");
            System.out.println((String) request.getAttribute("shiroLoginFailure"));
           // throw  new CaptchaException();
            //拒绝访问，不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws IOException {
        //登录成功之后处理
        //直接响应登录成功的JSON
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"success\":true,\"msg\":\"登录成功\"}");
        out.flush();
        out.close();
        //返回值为boolean,表示是否需要继续访问登录表单地址对应控制器的方法/login
        return false;
    }
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        //登录失败之后处理
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            //捕获登录的错误信息
            //String message = (String) request.getAttribute("shiroLoginFailure");
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号不存在\"}");
            } else if ("CaptchaException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"验证码错误\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号被锁定\"}");
            } else if ("AuthenticationException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"认证失败\"}");
            } else {
                out.println("{\"success\":false,\"msg\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}

