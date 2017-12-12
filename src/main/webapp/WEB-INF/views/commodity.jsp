<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品信息</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/commodity.js"></script>
</head>
<body>
<table id="commodity_datagrid"></table>
<!-- 定义对话框 -->
<div id="commodity_dialog">
    <form id="commodity_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 30px;">
            <table>
                <tr>
                    <td>
                        <input type="text" name="sn" class="easyui-textbox" data-options="label:'商品编码:',labelPosition:'before', width:250">*
                        <input type="text" name="name" class="easyui-textbox" data-options="label:'商品名称:',labelPosition:'before', width:250">*
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <select name="category.id" class="easyui-combobox" panelHeight="auto"
                                data-options="
						            width:250,
						            label:'商品类别:',
						            labelPosition:'before',
						            valueField:'id',
						            textField:'category',
						            url:'/productType/selectListForProductForm'
						            ">
                        </select>
                        <select name="allergen" id="allergenSelect" class="easyui-combobox"
                                data-options="label:'易过敏物:',labelPosition:'before', width:250" panelHeight="auto">
                            <option value="0">无</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <select name="origin.id" class="easyui-combobox" panelHeight="auto"
                                data-options="
						            width:250,
						            label:'生产厂商:',
						            labelPosition:'before',
						            valueField:'id',
						            textField:'address',
						            url:'/productManufacturer/selectListForProductForm'
						            ">
                        </select>
                        <select name="brand.id" class="easyui-combobox" panelHeight="auto"
                                data-options="
						            width:250,
						            label:'品牌:',
						            labelPosition:'before',
						            valueField:'id',
						            textField:'name',
						            url:'/productBrand/selectListForProductForm'
						            ">
                        </select>
                    </td>

                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input name="ingredient" type="text"  class="easyui-textbox" data-options="label:'主要成分:',labelPosition:'before', width:250">
                        <input name="remark" type="text"  class="easyui-textbox" data-options="label:'备注:',labelPosition:'before', width:250">
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="advantage" class="easyui-textbox" data-options="label:'展示照片:',labelPosition:'before', width:250">
                        <input type="text" name="salePrice" class="easyui-textbox" data-options="label:'售价:',labelPosition:'before', width:250">
                    </td>
                </tr>
                <tr>
                    <td>
                        <select name="display" id="displaySelect" class="easyui-combobox"
                                data-options="label:'是否启用:',labelPosition:'before', width:250" panelHeight="auto">
                            <option value="0">是</option>
                            <option value="1">否</option>
                        </select>
                    </td>
                </tr>o
            </table>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="commodity_datagrid_tb">
    <div>

        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加商品</a>

        <input  id="proname" type="text" name="name" class="easyui-textbox" data-options="label:'商品名称:',labelPosition:'before', width:200">*
        <select id="categorySelect" name="category.id" class="easyui-combobox" panelHeight="auto"
                data-options="
				width:200,
				label:'商品类别:',
				labelPosition:'before',
				valueField:'id',
				textField:'category',
				url:'/productType/selectListForProductForm'
				">
        </select>
        <select id="brandSelect" name="brand.id" class="easyui-combobox" panelHeight="auto"
                data-options="
				    width:200,
				    label:'品牌:',
					labelPosition:'before',
					valueField:'id',
					textField:'name',
					url:'/productBrand/selectListForProductForm'
					">
        </select>
        <input id="proele" type="text" name="ingredient" class="easyui-textbox" data-options="label:'主要成分:',labelPosition:'before', width:200">*
        <a id="searchBtn" class="easyui-linkbutton">查询</a>
    </div>

</div>
<!-- 对话框底部按钮 -->
<div id="commodity_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>