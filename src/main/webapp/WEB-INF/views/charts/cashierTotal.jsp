<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>收银的列表</title>
    <%@include file="../common.jsp" %>
    <script type="text/javascript" src="/static/js/views/cashierTotal.js"></script>
    <%--添加时间插件--%>
    <script type="text/javascript" src="/static/js/plugins/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<div id="serach_div">
    <%--会员号--%>
    <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
        会员号：
    </div>
    <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
        <input id="VIPNumber" name="VIPNumber" type="text" value="" />
    </div>
        <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
            收银时间：
        </div>
        <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
            <input id="startTime" name="startTime" type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'new Date()\'}'})"/>~
        </div>

        <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
            <input id="endTime" name="endTime" type="text" value="" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:new Date()})"/>
        </div>
    <%--查询按钮--%>
    <div style="float: left; line-height: 24px; margin-left: 24px;">
        <input type="button" value="查询" style="margin-top: 6px; background-color: lightgray" onclick="search();">
    </div>
</div>
<table id="cashierTotal_datagrid"></table>


<%--展示:订单详情页的会话框--%>
<div id="cashierTotal_dialog">
    <table id="detail_datagrid"></table>
</div>

</body>
</html>
