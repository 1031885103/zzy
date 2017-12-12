package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemMenu;
import com._520it.crm.mapper.SystemMenuMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public int deleteByPrimaryKey(Long id) {
        return systemMenuMapper.deleteByPrimaryKey(id);
    }

    public int insert(SystemMenu record) {
        return systemMenuMapper.insert(record);
    }

    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }

    public int updateByPrimaryKey(SystemMenu record) {
        return systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = systemMenuMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<SystemMenu> result = systemMenuMapper.queryPageDataResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public List<SystemMenu> queryTree() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<SystemMenu> queryForRole() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<Long> queryMenuIdsListForRole(Long roleId) {
        return systemMenuMapper.systemMenuMapper(roleId);
    }

    @Override
    public List<SystemMenu> indexMenu() {
        //获取所有的菜单userTree
        List<SystemMenu> queryTree = systemMenuMapper.queryTree();
        //获取用户对象,判断是否是超级管理员
        Employee current = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (!current.isAdmin()) {
            //根据用户id查询该用户的菜单id集合
            List<Long> ids = systemMenuMapper.queryMenuIdListByEmployeeId(current.getId());
            //对用户菜单做筛选
            filterMenu(queryTree, ids);//在迭代方法中删除菜单节点,这里的菜单集合也会改变,引用关系
        }
        return queryTree;
    }

    private void filterMenu(List<SystemMenu> queryTree, List<Long> ids) {
        //迭代所有菜单节点
        SystemMenu systemMenu;
        for (int i = queryTree.size() - 1; i >= 0; i--) {
            systemMenu = queryTree.get(i);
            if (!ids.contains(systemMenu.getId())) {//若系统菜单不再用户菜单id节点集合
                queryTree.remove(i);

            } else {
                if (systemMenu.getChildren() != null && systemMenu.getChildren().size() > 0) {
                    filterMenu(systemMenu.getChildren(), ids);
                }
            }
        }
    }
}
