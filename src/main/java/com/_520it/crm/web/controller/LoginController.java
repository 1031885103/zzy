package com._520it.crm.web.controller;

import com._520it.crm.page.AjaxResult;
import com._520it.crm.util.CaptchaException;
import com._520it.crm.util.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException
    { //如果登录失败从request中获取认证异常信息，shrioLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        //根据shrio返回的异常路径判断，抛出指定异常信息
        if (exceptionClassName!=null){
            if(CaptchaException.class.getSimpleName().equals(exceptionClassName)) {
                //抛出异常
                response.setCharacterEncoding("utf-8");
                response.getWriter().println("{\"success\":false,\"msg\":\"验证码错误\"}");
                return null;
            }
        }
        return "login";
    }

    @RequestMapping("/main")
    @ResponseBody
    public AjaxResult main() {
        AjaxResult ajaxResult=null;
        try{
            //登录校验

            ajaxResult= new AjaxResult("登录成功");
        }catch (Exception e){
            ajaxResult=new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout() {
        //清空数据
        UserContext.setCurrentUser(null);
        return "redirect:/login";
    }
    //发送邮箱
    @RequestMapping("/sendEmail")
    public String sendEmail(){
        return "sendEmail";
    }
}
