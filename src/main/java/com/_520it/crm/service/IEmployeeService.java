package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
	int deleteByPrimaryKey(Long id);
    int insert(Employee record);
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee record);
	PageResult queryPage(EmployeeQueryObject qo);
	void quit(Long id);

    Employee queryByUsername(String username);

    //---------------------------------------
    Employee checkUsername(String username);
    void savePsw(String username,String newpsw);

    Employee resetPsw(String username);
}
