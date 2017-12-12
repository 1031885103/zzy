<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录日志</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/log.js"></script>
</head>
<body>
<table id="log_datagrid"></table>
<div id="log_datagrid_tb">
    <div>
        <input id="logUser" type="text" name="opUser.username" class="easyui-textbox" data-options="label:'登录名称:',labelPosition:'before', width:200">
        <input id="beginTime" type="text" name="beginTime" class="easyui-datebox" data-options="label:'登录时间:',labelWidth:'70',labelPosition:'before', width:170">
        <input id="endTime" type="text" name="endTime" class="easyui-datebox" data-options="label:'~',labelWidth:'10',labelPosition:'before', width:115">

        <a id="searchBtn" class="easyui-linkbutton">查询</a>
    </div>
</div>
</body>
</html>