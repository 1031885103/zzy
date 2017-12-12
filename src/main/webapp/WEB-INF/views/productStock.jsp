<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>入库/发货/制单</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/productStock.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div data-options="region:'north',split:false,border:true" style="height:70px;">
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
            <div style="float: right;margin-top:0px;margin-right: 70px">
                <font size="3">
                    共有:&nbsp;<span id="type"></span>类&nbsp;&nbsp;&nbsp;&nbsp;整装:&nbsp;<span id="allNub"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                    散装:&nbsp;<span>0</span>&nbsp;&nbsp;&nbsp;&nbsp;结余总结:&nbsp;<span id="price"></span>元
                </font>
            </div>
        </form>
    </div>
    <div data-options="region:'center',split:false,border:true" style="padding:5px;">
        <table id="productStock_datagrid"></table>
    </div>
    <div id="btn01">
        <a class="easyui-linkbutton" iconCls="icon-custom-stock" plain="true" data-cmd="instock">入库/发货/制单</a>
        <a class="easyui-linkbutton" iconCls="icon-custom-info" plain="true" data-cmd="stockInfo">入库信息</a>
        <a class="easyui-linkbutton" iconCls="icon-custom-info" plain="true" data-cmd="sendInfo">发货信息</a>
    </div>
</div>
<!--打印单据-->
<div id="productStockItem_dialog">
    <form id="productStockForm" method="post">
        <div>
            <font size="2.5">
                <span style="float: right;margin-right: 30px">
                    <font size="3">总数量:<spam id="totalNumber"></spam></font>
                    <font size="3">总金额:<span id="totalPrice"></span></font>
                </span>
            </font>
        </div>
        <table id="productStockItem_datagrid">
        </table>
    </form>
</div>
</body>
</html>
