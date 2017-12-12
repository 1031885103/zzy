<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>商品销售记录</title>
    <%@include file="../common.jsp" %>
    <script type="text/javascript" src="/static/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#saleData_datagrid").datagrid({
                fit: true,
                fitColumns: true,
                striped:true,
                striped:true,
                singleSelect: true,
                url: "/saleBill1/SaleData",
                rownumbers:true,
                //pagination:true,
                columns: [[
                    {field:'sn',align:'center',width:10,title:'商品条码', formatter:snFormatter},
                    {field:'name',align:'center',width:10,title:'商品名称', formatter:nameFormatter},
                    {field:'specification',align:'center',width:10,title:'单位', formatter:specificationFormatter},
                    {field:'originalprice',align:'center',width:10,title:'商品原价(元)'},
                    {field:'releasePrice',align:'center',width:10,title:'折扣(元)'},
                    {field:'realprice',align:'center',width:10,title:'售价(元)'},
                    {field:'number',align:'center',width:10,title:'销售数量'},
                    {field:'remark',align:'center',width:10,title:'折扣原因',formatter:remarkFormatter}
                ]]

            });
        })

        // 明细中和商品相关的格式
        function specificationFormatter(value, row, index) {
            //alert(row);
            if (row.product) {
                return row.product.specification;
            }
        }
        function snFormatter(value, row, index) {
            if (row.product) {
                return row.product.sn;
            }
        }
        function nameFormatter(value, row, index) {
            if (row.product) {
                return row.product.name;
            }
        }

        function remarkFormatter(value, row, index){
            if (row.product) {
                return row.product.remark;
            }
        }

        // 高级查询
        function search() {
            $("#saleData_datagrid").datagrid("load",{
                name: $("#name").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            })
        }

    </script>
</head>
<body>

<div id="serach_div">
    <%--会员号--%>
    <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
        商品名称：
    </div>
    <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
        <input id="name" name="name" type="text" value="" />
    </div>
    <div style="float: left; margin-top: 6px; line-height: 24px;margin-right:10px">
         销售时间：
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

<%--展示:订单详情页的会话框--%>
<table id="saleData_datagrid"></table>

</body>
</html>
