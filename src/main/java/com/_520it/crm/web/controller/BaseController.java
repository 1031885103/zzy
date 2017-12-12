package com._520it.crm.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//在页面访问的时候如果没有权限会抛出异常.需要对异常进行统一处理.(ajax请求,页面请求.)
@ControllerAdvice
public class                                                            BaseController {
    @ExceptionHandler(UnauthorizedException.class)
    public void handlerException(HandlerMethod handlerMethod, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        if (handlerMethod.getMethodAnnotation(ResponseBody.class) == null) {
            //返回noPermission.jsp页面
            response.sendRedirect("/nopermission.jsp");
        } else {//ajax请求
            //返回json数据
            String msg = "{\"success\":false,\"msg\":\"您没有权限操作\",rows:[]}";
            response.getWriter().write(msg);
        }
    }
}
