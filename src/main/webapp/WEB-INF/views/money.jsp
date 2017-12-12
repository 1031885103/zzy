<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>薪资计算</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/money.js"></script>
</head>
<body>
<table id="money_datagrid"></table>
<!-- 定义对话框 -->
<div id="money_dialog">
    <form id="money_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 30px;">
            <table>
                <tr>
                    <td width="75px">
                        <input name="sn" class="easyui-textbox" id="snId"
                               data-options="label:'工号:',labelWidth:'70',labelPosition:'top', width:200">

                    </td>
                    <td>
                        <input name="name" class="easyui-textbox" id="nameId"
                               data-options="label:'姓名:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="salary" class="easyui-textbox" id="salaryId"
                               data-options="label:'基本工资:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="job" class="easyui-textbox" id="jobId"
                               data-options="label:'岗位津贴:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                    <td >
                        <input name="traffic" class="easyui-textbox" id="trafficId"
                               data-options="label:'交通补贴:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="tel" class="easyui-textbox" id="telId"
                               data-options="label:'话费补贴:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                    <td >
                        <input name="meal" class="easyui-textbox" id="mealId"
                               data-options="label:'餐补:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="every" class="easyui-textbox" id="everyId"
                               data-options="label:'全勤:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
                <tr>
                    <td >
                        <input name="proportion" class="easyui-textbox" id="proportionId"
                               data-options="label:'提成:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
                <tr>
                    <td >
                        <input name="holiday" class="easyui-textbox" id="holidayId"
                               data-options="label:'请假:',labelWidth:'70',labelPosition:'top', width:200">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="money_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加职工</a>
        <input id="searchBtn" type="text">
    </div>

</div>
<!-- 对话框底部按钮 -->
<div id="money_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>