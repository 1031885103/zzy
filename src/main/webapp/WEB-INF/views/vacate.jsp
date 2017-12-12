<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>请假记录</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/vacate.js"></script>
</head>
<body>
<table id="vacate_datagrid"></table>
<!-- 定义对话框 -->
<div id="vacate_dialog">
    <form id="vacate_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 30px;">
            <table>
                <tr>
                    <td width="75px">
                        <select name="emp.id" class="easyui-combobox" panelHeight="auto"
                                data-options="
						            width:200,
						            label:'请假员工:',
						            labelWidth:'70',
						            labelPosition:'before',
						            valueField:'id',
						            textField:'username',
						            url:'/employee/selectListForLeaveForm'
						            ">
                        </select>
                    </td>
                    <td>
                        <select name="type" id="typeSelect" class="easyui-combobox"
                                data-options="label:'请假类别:',labelWidth:'70',labelPosition:'before', width:200" panelHeight="auto">
                            <option value="-1">请选择</option>
                            <option value="1">事假</option>
                            <option value="2">病假</option>
                            <option value="3">婚假</option>
                            <option value="4">丧假</option>
                            <option value="5">公假</option>
                            <option value="6">产假</option>
                            <option value="7">工伤</option>
                            <option value="8">护理假</option>
                            <option value="9">其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="reason" class="easyui-textbox" style="width:450px;height:40px;"
                               data-options="label:'请假原因:',labelWidth:'70',labelPosition:'before', width:200" multiline:true>
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                       <input name="begin" class="easyui-datebox"
                              data-options="label:'开始时间:',labelWidth:'70',labelPosition:'before', width:200">
                    </td>
                    <td >
                        <input name="end" class="easyui-datebox"
                               data-options="label:'结束时间:',labelWidth:'70',labelPosition:'before', width:200">
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="duration" type="text"  class="easyui-textbox" data-options="label:'请假时长:',labelWidth:'70',labelPosition:'before', width:200">
                    </td>
                    <td >
                        <select name="normal" id="normalSelect" class="easyui-combobox"
                                data-options="label:'是否正常请假:',labelPosition:'before',labelWidth:'90', width:200" panelHeight="auto">
                            <option value="0">是</option>
                            <option value="1">否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="75px">
                        <input name="sn" type="text"  class="easyui-textbox" data-options="label:'工号:',labelWidth:'70',labelPosition:'before', width:200">
                    </td>
                </tr>
                <tr>
                    <td >
                        <input name="remark" type="text" style="width: 450px;height: 40px;" class="easyui-textbox"
                               data-options="label:'领导意见:',labelWidth:'70',labelPosition:'before', width:200">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="vacate_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加记录</a>
        <input id="searchBtn" type="text">
    </div>

</div>
<!-- 对话框底部按钮 -->
<div id="vacate_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>