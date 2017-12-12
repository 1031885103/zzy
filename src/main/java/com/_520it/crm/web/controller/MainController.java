package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.SqlBackupAndRecover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("")
    public String main() {
        return "main";
    }

    @RequestMapping("/resetPsw")
    @ResponseBody
    public Employee resetPsw(String username) {
            Employee employee = employeeService.resetPsw(username);
        return employee;
    }
    @RequestMapping("/savePsw")
    @ResponseBody
    public AjaxResult savePsw(String username,String newpsw) {
        AjaxResult ajaxResult = null;
        try{
             employeeService.savePsw(username,newpsw);
            ajaxResult = new AjaxResult("密码修改成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }

    //数据库的备份
    @RequestMapping("/mysql_backup")
    @ResponseBody
    public void backup(HttpServletResponse response) {
        SqlBackupAndRecover.backup(response);
    }

    //数据库的还原
    @RequestMapping("/mysql_restore")
    @ResponseBody
    public AjaxResult recover() {
        try {
            SqlBackupAndRecover.restore("pet1");
            return new AjaxResult(true, "还原成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "还原失败!");
    }
}