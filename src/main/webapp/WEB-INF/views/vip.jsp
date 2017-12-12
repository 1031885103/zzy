<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会员管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/vip.js"></script>
</head>
<body>
<table id="vip_datagrid"></table>
<!-- 定义对话框 -->
<div id="vip_dialog">
    <form id="vip_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <input type="text" name="username" class="easyui-textbox" data-options="label:'用户名:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="realname" class="easyui-textbox" data-options="label:'真实姓名:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="tel" class="easyui-textbox" data-options="label:'联系方式:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="email" class="easyui-textbox" data-options="label:'邮箱:',labelPosition:'top', width:150">

            </div>
           <%-- <div>
                <select name="dept.id" class="easyui-combobox"
                        data-options="
						 width:150,
						 label:'所属部门:',
						 labelPosition:'top',
						 valueField:'id',
						 textField:'name',
						 url:'/department/selectListForEmployeeForm'
						">
                </select>
            </div>
            <div>
                <select id="roleId" class="easyui-combobox"
                        data-options="
						 width:150,
						 label:'角色:',
						 labelPosition:'top',
						 multiple:true,
						 valueField:'id',
						 textField:'name',
						 url:'/role/selectListForEmployeeForm'
						">
                </select>
            </div>--%>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="vip_datagrid_tb">
    <div>

        <%--<shiro:hasPermission name="vip:save">--%>
            <a class="easyui-linkbutton" iconCls="icon-chongzhi" plain="true" data-cmd="money">充值</a>
            <a class="easyui-linkbutton" iconCls="icon-vip" plain="true" data-cmd="addVip">添加会员</a>
            <a class="easyui-linkbutton" iconCls="icon-dog2" plain="true" data-cmd="addPet">添加宠物</a>
        <%--</shiro:hasPermission>--%>
        <%--<shiro:hasPermission name="vip:update">--%>

            <a id="vip_editBtn" class="easyui-linkbutton" iconCls="icon-newedit" plain="true" data-cmd="edit">编辑</a>
            <a id="vip_editBtn" class="easyui-linkbutton" iconCls="icon-newdel" plain="true" data-cmd="edit">删除</a>
        <%--</shiro:hasPermission>--%>
        <%--<shiro:hasPermission name="vip:quit">--%>
            <%--<a id="vip_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">离职</a>--%>
        <%--</shiro:hasPermission>--%>
        <a class="easyui-linkbutton" iconCls="icon-newreload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        <input id="searchBtn" type="text">
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="vip_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>