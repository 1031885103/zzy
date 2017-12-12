package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class UserContext {
	public static final String USER_IN_SESSION = "user_in_session";
	public static final String EXPRESSION_IN_SESSION = "expression_in_session";
	
	private static HttpSession getSession(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}
	public static void setCurrentUser(Employee employee) {
		if(employee != null){
			getSession().setAttribute(USER_IN_SESSION, employee);
		}else{
			getSession().invalidate();
		}
	}
	public static Employee getCurrentUser() {
		return (Employee)getSession().getAttribute(USER_IN_SESSION);
	}
	
	public static void setPermissionExpressions(List<String> expressions) {
		getSession().setAttribute(EXPRESSION_IN_SESSION,expressions);
	}
	@SuppressWarnings("unchecked")
	public static List<String> getPermissionExpressions() {
		return (List<String>)getSession().getAttribute(EXPRESSION_IN_SESSION);
	}
}
