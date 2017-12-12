package com._520it.crm.realm;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IEmployeeService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;


    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名,设置用户密码,获取类名
        String username = (String) token.getPrincipal();

        //从数据库中查询用户对象
        Employee current = userService.queryByUsername(username);
        String password = current.getPassword();
        //创建认证信息对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(current, password, getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前用户,获取用户的角色,权限,设置到授权对象中
        Employee current = (Employee) principals.getPrimaryPrincipal();
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        if (current.isAdmin()) {//若是超级管理员
            //查询出所有角色,所有权限
            List<Role> roleList = roleService.selectAll();
            for (Role role : roleList) {
                roles.add(role.getSn());
            }
            List<Permission> permissionList = permissionService.selectAll();
            for (Permission permission : permissionList) {
                permissions.add(permission.getResource());
            }

        } else {
            List<Role> roleList = roleService.queryRolesByEmpId(current.getId());
            for (Role role : roleList) {
                roles.add(role.getSn());
            }
            List<Permission> permissionList = permissionService.queryPermissionsByEmpId(current.getId());
            for (Permission permission : permissionList) {
                permissions.add(permission.getResource());
            }

        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }
}
