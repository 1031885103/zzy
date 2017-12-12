package com._520it.crm.web.controller;

import com._520it.crm.domain.Cities;
import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.service.IAreasService;
import com._520it.crm.service.ICityService;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IAreasService areasService;
    @RequestMapping("")
    public String register(Employee employee) {
        if (employee.getUsername() != null) {
            employeeService.insert(employee);
        }
        return "register";
    }
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(Employee employee) {
        AjaxResult ajaxResult = null;
        try{
            employeeService.insert(employee);
            ajaxResult = new AjaxResult("注册成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult = new AjaxResult(false,e.getMessage());
        }
        return ajaxResult;
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public String checkUsername(String username) {
        Employee user = employeeService.checkUsername(username);
        if (user != null) {
            return "false";
        }
        return "true";
    }
    @RequestMapping("/getCity")
    @ResponseBody
    public List<Cities> getCity(Long id) {
        List<Cities> citys = cityService.listByProvinceId(id);
        return citys;
    }
    @RequestMapping("/getArea")
    @ResponseBody
    public List<String> getArea(Long id) {
        List<String> Countys = areasService.listByCityId(id);
        return Countys;
    }

}
