package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Log;
import com._520it.crm.service.ILogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class LogAspect {
    @Autowired
    private ILogService logService;

    public void writeLog(JoinPoint joinPoint) {
        //记录方法调用信息,保存到日志
        //获取用户对象,设置日志时间,操作者
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        Log log = new Log();
        log.setOpDate(new Date());
        log.setOpUser(employee);
        //String function = joinPoint.getTarget().getClass().getName() + ":" + joinPoint.getSignature().getName();
        log.setOpMac("0");
        log.setDisplay(0);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr());
        log.setOpIp(request.getRemoteAddr());
        logService.save(log);


    }
}
