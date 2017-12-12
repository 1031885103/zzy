<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>供应商管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/supplier.js"></script>
</head>
<body>
<table id="supplier_datagrid"></table>
<!-- 定义对话框 -->
<div id="supplier_dialog">
    <form id="supplier_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 30px;">
            <table>
                <tr>
                    <td>
                        <input type="text" name="sn" class="easyui-textbox" data-options="label:'供应商编号:',labelPosition:'before', width:250">*
                        <input type="text" name="name" class="easyui-textbox" data-options="label:'供应商名称:',labelPosition:'before', width:250">*
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="contacts" class="easyui-textbox" data-options="label:'联系人:',labelPosition:'before', width:250">
                        <input type="text" name="phone" class="easyui-textbox" data-options="label:'联系电话:',labelPosition:'before', width:250">
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="product" class="easyui-textbox" data-options="label:'主供商品:',labelPosition:'before', width:250">
                        <input id="targetDate" type="text"  class="easyui-datebox" data-options="label:'首次合作时间:',labelPosition:'before', width:250">
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="advantage" class="easyui-textbox" data-options="label:'优势:',labelPosition:'before', width:250">
                        <select name="display" id="displaySelect" class="easyui-combobox"
                                data-options="label:'是否启用:',labelPosition:'before', width:150" panelHeight="auto">
                            <option value="0">是</option>
                            <option value="1">否</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="supplier_datagrid_tb">
    <div>

        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加供应商</a>

        <input id="searchBtn" type="text">
    </div>

</div>
<!-- 对话框底部按钮 -->
<div id="supplier_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>