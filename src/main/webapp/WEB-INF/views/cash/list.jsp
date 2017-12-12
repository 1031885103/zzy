<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理</title>
    <%@include file="/WEB-INF/views/common.jsp" %>
    <script type="text/javascript" src="/static/js/views/cash.js"></script>
    <script type="text/javascript" src="/static/js/views/jquery.autocomplete.js"></script>
    <style type="text/css">
        html, body {
            overflow: hidden;
            font-size: 12px;
        }

        tbody {
            height: 50px;
            font-size: 12px;
        }

        tr {
            height: 32px;
        }

        table {
            overflow-y: auto;
            overflow-x: hidden;
            font-size: 12px;
        }

        .border-color-error {
            border: 1px solid red;
        }

        .border-color-success {
            border: 1px solid #D0D0D0;
        }

        input, select {
            border: 1px solid #D0D0D0;
            /*border-radius: 5px;*/
        }

        .pic {
            width: 200px;
            height: 220px;
            border: 1px solid #999;
            z-index: 9999;
            position: absolute;
            top: 39px;
            left: -204px;
            display: none;
            border-radius: 10px;
        }

        .StoreName {
            text-align: center;
            font-size: 14px;
            font-weight: bold;
        }

        tr {
            height: 20px;
        }

        .ui-autocomplete {
            overflow-y: auto;
            max-height: 350px;
            overflow-x: hidden;
        }
    </style>

    <script type="text/css">
        .autocomplete-suggestions {
            border: 1px solid #999;
            background: #FFF;
            overflow: auto;
        }

        .autocomplete-suggestion {
            padding: 2px 5px;
            white-space: nowrap;
            overflow: hidden;
        }

        .autocomplete-selected {
            background: #F0F0F0;
        }

        .autocomplete-suggestions strong {
            font-weight: normal;
            color: #3399FF;
        }
    </script>

</head>
<body>
<table id="employee_datagrid"></table>
<!-- 定义对话框 -->
<%--收银记录对话框--%>
<div id="cash_dialog">
    <table id="recorddatagrid"></table>
</div>


<div id="employee_dialog">
    <form id="employee_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <input type="text" name="product.name" class="easyui-textbox" data-options="label:'商品名称:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="storeNumber" class="easyui-textbox" data-options="label:'库存数量:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="product.salePrice" class="easyui-textbox" data-options="label:'单价:',labelPosition:'top', width:150">
            </div>
            <div>
                <input id="number" type="text" name="number" class="easyui-textbox" data-options="label:'所购数量:',labelPosition:'top', width:150">
            </div>
            <div align="left">
                <input type="hidden" name="product.sn" class="easyui-textbox">
                <input type="hidden" name="product.vsalePrice" class="easyui-textbox">
                <input type="hidden" name="specification" class="easyui-textbox">
                <input type="hidden" name="state" class="easyui-textbox">
                <h1>小计:<span id="amount"></span></h1>
            </div>

        </div>
    </form>
</div>


<div id="saleLayout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:5px;background:white">


        <div>
            <form action="#" method="post" id="vipForm">
                <table style="width: 888px; height: 50px; margin-left: 60px">
                    <tr>
                        <td style="text-align: left; width: 100px;">会员卡号：
                        </td>
                        <td style="text-align: left; width: 160px">
                            <input id="telphone" class="easyui-textbox" data-options="" style="width:200px">
                        </td>
                        <td style="text-align: right; width: 80px">会员姓名：
                        </td>
                        <td style="text-align: left; width: 80px">

                            <span id="MemberName" style="color: black; text-align: left"></span></td>
                        <td style="text-align: right; width: 80px">现有金额：
                        </td>
                        <td style="text-align: left; width: 80px">
                            <span id="MemberPrice" style="color: black; text-align: left"></span></td>
                        <td style="text-align: right; width: 80px">会员等级：
                        </td>
                        <td style="text-align: left; width: 80px">
                            <span id="MemberGrade" style="color: black; text-align: left"></span></td>
                        <td style="text-align: right; width: 80px">商品折扣：
                        </td>
                        <td style="text-align: left; width: 80px">
                            <span id="MemberProportion" style="color: black; text-align: left"></span>%
                        </td>
                        <td style="text-align: right; width: 80px">服务折扣：
                        </td>
                        <td style="text-align: left; width: 80px">
                            <span id="ServerProption" style="color: black; text-align: left"></span>%
                            <input id="vId" class="easyui-textbox" type="hidden">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div style="width: 100%; text-align: left; margin-left: 60px;">
            <div style="display: none">
                <span>优惠券:</span>
                <input id="CouponsNumber" name="CouponsNumber" onblur="CheckCoupons()" style="height:30px;width:150px;font-size:20px;text-align:left"
                       type="text" value=""/>
                <span style="margin-left: 20px">类型：</span><span id="CouponsType"></span>
                <span style="margin-left: 20px">起始金额：</span><span id="CouponsLines" style="width: 100px"></span>
                <span style="margin-left: 20px">优惠金额：</span><span id="CouponsPrice" style="width: 100px"></span>
            </div>
            <span style="width: 80px">应收金额：</span>
            <input id="Price" name="Price" style="height:30px;width:150px;font-size:20px" type="text" value="0"/>
            <span>元</span>
            <span id="nowPrice" style="margin-left: 15px">实收金额：</span>
            <input id="Sprice" data-val="true" data-val-number="字段 DiscountPrice 必须是一个数字。" data-val-required="DiscountPrice 字段是必需的。"
                   id="DiscountPrice"
                   name="DiscountPrice" style="height:30px;width:150px;font-size:20px" type="text" value=""/>
            <span>元</span>
            <span id="fangshi" style="margin-left:20px;">收款方式：</span>
            <select id="Display" class="input_check" style="width: 80px">
                <option value="1">现金</option>
                <option value="2">微信</option>
                <option value="3">支付宝</option>
            </select>
            <a id="saleProduct" class="easyui-linkbutton"
               style="background-color:orange;
           /*float: right;margin-right: 15px;*/width:150px;" data-cmd="inputCash">现金结账</a>
        </div>
        <br/>
        <div class="botbtbx pdb0" style="width: 100%; text-align: left; margin-left: 60px">
            <input id="MemberPayMoney" type="button" class="comBtn orange" value="会员结账" style="float: left; display: none;">
            <input id="PayMoney" type="button" class="comBtn orange" value="会员结账" style="float: left;">
            <input id="Waiting" type="button" class="comBtn gray" value="挂单" style="float: left; margin-left: 20px">
            <input id="GetWaiting" type="button" class="comBtn gray" value="取单" style="float: left; margin-left: 20px">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 15px" id="totalPrice">总计:</span>
        </div>
    </div>
</div>


<%--定义收银记录表格的顶部按钮--%>
<div id="cashrecord_datagrid_tb">
    <div>
        <input id="vipId" type="text" name="vipCard" class="easyui-textbox" data-options="label:'会员号:',labelWidth:50, width:200">

        <select id="storeId" name="storeId" class="easyui-combobox"
                data-options="
						 width:200,
						 label:'收银店铺:',

						 valueField:'id',
						 textField:'name',
						 url:'/saleBill/selectListForRecordForm'
						">
        </select>
        <input id="beginTime" type="text" name="vipCard" class="easyui-datetimebox" data-options="label:'收银时间:',labelWidth:70, width:200">
        <input id="endTime" type="text" name="vipCard" class="easyui-datetimebox" data-options="label:'~',labelWidth:20, width:200">
        <a id="searchRecord" class="easyui-linkbutton" data-options="width:80" data-cmd="searchRecord">查询</a>


    </div>
</div>


<!-- 定义顶部按钮 -->
<div id="employee_datagrid_tb">
    <div>
        <input id="searchBtn" <%--class="easyui-textbox"--%>
        <%--data-options="label:'商品条码:',width:220,labelWidth:60,iconCls:'icon-search'--%>
        >
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        商品名称:<input id="autocomplete" type="text" name="country">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a id="searchProductName" class="easyui-linkbutton" data-options="width:80" data-cmd="searchName">查询</a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a id="cashrecord" class="easyui-linkbutton" style="background-color:green;float: right;margin-right: 15px;width:150px;">收银记录</a>
    </div>

</div>


<!-- 对话框底部按钮 -->
<div id="employee_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">结账</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>


<%--取单dialog--%>

<div id="take_dialog">
    <table id="takedatagrid"></table>
</div>

<div id="tbt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="makeTrue">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

</body>
</html>