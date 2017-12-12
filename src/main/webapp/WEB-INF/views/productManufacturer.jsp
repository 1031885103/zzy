<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品生产产地</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/productManufacturer.js"></script>
</head>
<body>
<table id="productManufacturer_datagrid"></table>
<!-- 定义对话框 -->
<div id="productManufacturer_dialog">
    <form id="productManufacturer_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <input type="text" name="address" class="easyui-textbox" data-options="label:'商品生产地址',labelPosition:'top', width:150">
            </div>
            <div>
                <select name="display" id="displaySelect" class="easyui-combobox"
                        data-options="label:'是否启用:',labelPosition:'top', width:150" panelHeight="auto">
                    <option value="0">是</option>
                    <option value="1">否</option>
                </select>
            </div>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="productManufacturer_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加产地</a>
        <input id="searchBtn" type="text">
    </div>


</div>
<!-- 对话框底部按钮 -->
<div id="productManufacturer_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>