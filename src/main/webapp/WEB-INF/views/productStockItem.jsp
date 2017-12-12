<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>入库明细页面</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/productStockItem.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div data-options="region:'north',split:false,border:true" style="height:60px;">
        <form id="editForm">
            <div style="margin-top: 20px;margin-left: 10px">
                <input type="text" name="productSn" class="easyui-textbox"
                       data-options="label:'商品条码:',prompt:'输入条码信息', width:200,labelWidth:60">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25,height:23"></a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" name="productName" class="easyui-textbox"
                       data-options="label:'商品名称:',prompt:'输入名称信息', width:200,labelWidth:60">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="easyui-linkbutton"
                   data-options="width:70,height:25" style="background: #e2e2e2" data-cmd="queryKey">查&nbsp;&nbsp;&nbsp;询</a>
            </div>
        </form>
    </div>
    <div data-options="region:'center',split:false,border:true" style="padding:5px;">
        <table id="productStockItem_datagrid"></table>
    </div>
</div>
</body>
</html>
