<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>职位设置</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/work.js"></script>
</head>
<body>
<table id="work_datagrid"></table>
<!-- 定义对话框 -->
<div id="work_dialog">
    <form id="work_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <input type="text" name="position" class="easyui-textbox" data-options="label:'职位名称',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="job" class="easyui-textbox" data-options="label:'岗位津贴',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="traffic" class="easyui-textbox" data-options="label:'交通补贴',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="tel" class="easyui-textbox" data-options="label:'话费补贴',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="meal" class="easyui-textbox" data-options="label:'餐补',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="every" class="easyui-textbox" data-options="label:'全勤奖励',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="proportion" class="easyui-textbox" data-options="label:'提成比例',labelPosition:'top', width:150">
            </div>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="work_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加职位</a>

    </div>


</div>
<!-- 对话框底部按钮 -->
<div id="work_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>