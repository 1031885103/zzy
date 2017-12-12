<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>入库临时明细页面</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/stockBillItem.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div data-options="region:'north',split:true,border:true,height:140">
        <form id="editForm" method="POST">
            <input type="hidden" class="easyui-textbox" name="id">
            <table>
                <tr>
                    <td>
                        <div class="easyui-layout" style="width:1050px;height:125px;">
                            <div data-options="region:'west',split:true" style="width:620px;height: 90px;background: #f3f3f3">

                                <div id="changSnAndName" style="margin-top: 5px;margin-left: 10px">
                                    <%-- <input id="productSn" type="text" name="sn"
                                            class="easyui-textbox"
                                            data-options="label:'商品条码:',required:true, width:170,labelWidth:60,events:{blur:getProduct}">
                                     <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-shop',width:25"></a>--%>
                                    <%--<input type="text" name="name"
                                           class="easyui-textbox" data-options="label:'商品名称:', width:390,labelWidth:60,events:{blur:getProductName}">
                                        --%>
                                    <input id="productSn" class="easyui-combobox" name="sn"
                                           data-options="
                                                width:170,
                                                labelWidth:60,
                                                panelHeight:'auto',
                                                url:'/product/queryProduct',
                                                valueField: 'sn',
                                                textField: 'sn',
                                                label: '商品条码:',
                                                required:true,
                                                onChange:getProduct
                                                ">

                                    <input id="productName" class="easyui-combobox" name="name" data-options="
                                                width:420,
                                                labelWidth:60,
                                                panelHeight:'auto',
                                                url:'/product/queryProduct',
                                                valueField: 'name',
                                                textField: 'name',
                                                label: '商品名称:',
                                                required:true,
                                                onChange:getProductName
                                                ">
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">

                                    <input type="text" name="categoryName" class="easyui-textbox"
                                           data-options="label:'商品类别:',prompt:'商品类别',
                                           width:170,labelWidth:60,labelAlign:'left'">
                                    <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25"></a>

                                    <input type="text" name="originName" class="easyui-textbox"
                                           data-options="label:'生产场地:',prompt:'生产场地',
                                           width:165,labelWidth:60,labelAlign:'right'">
                                    <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25"></a>
                                    <input type="text" name="brandName" class="easyui-textbox"
                                           data-options="label:'商品品牌:',prompt:'商品品牌',
                                           width:165,labelWidth:60,labelAlign:'right'">
                                    <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25"></a>
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input type="text" name="supplierName" class="easyui-textbox"
                                           data-options="label:'供   应    商:',prompt:'供应商',
                                           width:170,labelWidth:60,labelAlign:'left'">
                                    <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25"></a>
                                    <input type="text" name="ingredient" class="easyui-textbox"
                                           data-options="label:'主要成分:',prompt:'主要成分',
                                           width:165,labelWidth:60,labelAlign:'right'">
                                    <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25"></a>
                                    <select class="easyui-combobox" required="required" name="stockMethod" data-options="
                                         width:165,
                                         labelWidth:60,
                                         panelHeight:'auto',
                                         label:'出/入   库:',
                                         prompt:'必填项',
                                         limitToList:true,
                                        ">
                                        <option></option>
                                        <option value="入库">入库</option>
                                        <option value="出库">出库</option>
                                    </select>
                                    <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-edit',width:25"></a>
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input type="text" name="amount" class="easyui-textbox"
                                           data-options="label:'数量:',prompt:'大于零的整数', width:140,labelWidth:35,required:true">
                                    <input type="text" name="costPrice" class="easyui-textbox"
                                           data-options="label:'进/发价:',prompt:'商品进价', width:150,labelWidth:55,labelAlign:'right',required:true">
                                    <input type="text" name="salePrice" class="easyui-textbox"
                                           data-options="label:'商品标价:',prompt:'商品标价', width:143,labelWidth:60,labelAlign:'right'">
                                    <input type="text" name="vsalePrice" class="easyui-textbox"
                                           data-options="label:'会员价格:',prompt:'会员价格', width:150,labelWidth:60,labelAlign:'right'">
                                </div>
                            </div>
                            <div data-options="region:'center'" style="padding:5px;height: 90px;background: #f3f3f3">
                                <div style="margin-top: 5px;margin-left: 10px">折零参数设置</div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input type="text" class="easyui-textbox" name="specification"
                                           data-options="label:'折零规格:',prompt:'单位换算比例', width:160,labelWidth:60">
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <select class="easyui-combobox" name="minunit" data-options="
                                         width:160,
                                         labelWidth:60,
                                         panelHeight:'auto',
                                         label:'最小单位:',
                                        ">
                                        <option value="包">包</option>
                                        <option value="把">把</option>
                                        <option value="次">次</option>
                                        <option value="个">个</option>
                                        <option value="根">根</option>
                                        <option value="罐">罐</option>
                                        <option value="盒">盒</option>
                                        <option value="件">件</option>
                                        <option value="粒">粒</option>
                                        <option value="瓶">瓶</option>
                                        <option value="套">套</option>
                                        <option value="针">针</option>
                                        <option value="支">支</option>
                                        <option value="只">只</option>
                                        <option value="卷">卷</option>
                                        <option value="袋">袋</option>
                                        <option value="桶">桶</option>
                                        <option value="斤">斤</option>
                                        <option value="天">天</option>
                                        <option value="加仑">加仑</option>
                                        <option value="对">对</option>
                                        <option value="条">条</option>
                                        <option value="双">双</option>
                                        <option value="g">g</option>
                                        <option value="ml">ml</option>
                                        <option value="块">块</option>
                                        <option value="颗">颗</option>
                                        <option value="板">板</option>
                                        <option value="顶">顶</option>
                                        <option value="台">台</option>
                                        <option value="镑">镑</option>
                                        <option value="kg">kg</option>
                                        <option value="m">m</option>
                                        <option value="升">升</option>
                                        <option value="lbs">lbs</option>
                                        <option value="瓶">瓶</option>
                                        <option value="箱">箱</option>
                                    </select>
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <select class="easyui-combobox" name="maxunit" style="width:160px;"
                                            data-options="
                                            width:160,
                                            label:'最大单位:'
                                            ,labelWidth:60,
                                            panelHeight:'auto'">
                                        <option value="包">包</option>
                                        <option value="把">把</option>
                                        <option value="次">次</option>
                                        <option value="个">个</option>
                                        <option value="根">根</option>
                                        <option value="罐">罐</option>
                                        <option value="盒">盒</option>
                                        <option value="件">件</option>
                                        <option value="粒">粒</option>
                                        <option value="瓶">瓶</option>
                                        <option value="套">套</option>
                                        <option value="针">针</option>
                                        <option value="支">支</option>
                                        <option value="只">只</option>
                                        <option value="卷">卷</option>
                                        <option value="袋">袋</option>
                                        <option value="桶">桶</option>
                                        <option value="斤">斤</option>
                                        <option value="天">天</option>
                                        <option value="加仑">加仑</option>
                                        <option value="对">对</option>
                                        <option value="条">条</option>
                                        <option value="双">双</option>
                                        <option value="g">g</option>
                                        <option value="ml">ml</option>
                                        <option value="块">块</option>
                                        <option value="颗">颗</option>
                                        <option value="板">板</option>
                                        <option value="顶">顶</option>
                                        <option value="台">台</option>
                                        <option value="镑">镑</option>
                                        <option value="kg">kg</option>
                                        <option value="m">m</option>
                                        <option value="升">升</option>
                                        <option value="lbs">lbs</option>
                                        <option value="瓶">瓶</option>
                                        <option value="箱">箱</option>
                                    </select>
                                </div>
                            </div>
                            <div data-options="region:'east',split:true" style="width:210px;height: 90px;background: #f3f3f3">
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input class="easyui-datebox" name="bornDate"
                                           data-options="label:'生产日期:',prompt:'XXXX-XX-XX', width:180,labelWidth:60"> </input>
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input class="easyui-datebox" name="safeDate"
                                           data-options="label:'保质期:',prompt:'XXXX-XX-XX', width:180,labelWidth:60"> </input>
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input class="easyui-datebox" name="warningDate"
                                           data-options="label:'预警日期:',prompt:'XXXX-XX-XX', width:180,labelWidth:60"> </input>
                                </div>
                                <div style="margin-top: 5px;margin-left: 10px">
                                    <input type="text" name="warningNumber" class="easyui-textbox"
                                           data-options="label:'预警数量:',prompt:'输入大于零的整数', width:180,labelWidth:60">
                                </div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div style="margin-top: 35px;margin-left: 5px">
                            <a class="easyui-linkbutton" data-options="width:75,height:25" style="background: #f3f3f3" data-cmd="add">提交</a>
                        </div>
                        <div style="margin-top: 10px;margin-left: 5px">
                            <a class="easyui-linkbutton" data-options="width:75,height:25" style="background: #f3f3f3" data-cmd="reset">重置</a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',split:true,border:true,height:60">
        <div style="float: right;margin-top: 10px;margin-right: 30px">
            入库总价:<span id="totalPrice" style="margin-top: 50px"></span>元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a class="easyui-linkbutton" data-options="width:100,height:30" data-cmd="outstock">发货给分店</a>
            <a class="easyui-linkbutton" data-options="width:100,height:30" data-cmd="instock">全部入库</a>
            <a class="easyui-linkbutton" data-options="width:100,height:30" data-cmd="printTouch">打印制单</a>
        </div>
    </div>
    <div data-options="region:'center',split:true,border:true">
        <table id="stockBillItem_datagrid"></table>
    </div>
    <div id="btn01">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-custom-remove'" data-cmd="remove">删除</a>
    </div>
</div>

<!--分店选择-->
<div id="subbranch-dialog">
    <form id="subbranchForm" method="post">
        <div style="margin-left: 10px;margin-top: 10px">
            <font size="3">请选择分店店铺:</font></div>
        <div style="margin-left: 10px;margin-top: 10px">
            <select class="easyui-combobox" name="subId" data-options="
	    	    width:150,
	    	    labelPosition:'top',
	        	panelHeight:'auto',
	        	valueField:'id',
	        	textField:'name',
		        url:'/subbranch/selectListForSubbranchForm'
		        ">
            </select>
        </div>
    </form>
</div>
<div id="btn02">
    <a class="easyui-linkbutton" data-options="width:35,height:26" data-cmd="sure">确定</a>
    <a class="easyui-linkbutton" data-options="width:35,height:26" data-cmd="cancel">取消</a>
</div>
<!--打印单据-->
<div id="touch-dialog">
    <form id="touchForm" method="post">
        <div>
            <center><h1>进货单</h1></center>
        </div>
        <div><font size="2.5">供货单位:</font></div>
        <div>
            <font size="2.5">
                <spam>进货单位:xxxx</spam>
                <span style="float: right;margin-right: 20px">制单时间:<span id="time"></span></span>
            </font>
        </div>
        <table id="touch-datagrid"></table>
        <div style="float:right;margin-top:10px;margin-right: 15px">
            <a class="easyui-linkbutton" data-options="width:60,height:30" data-cmd="print">打&nbsp;&nbsp;&nbsp;印</a>
            <a class="easyui-linkbutton" data-options="width:60,height:30" data-cmd="cancelTouch">取&nbsp;&nbsp;&nbsp;消</a>
        </div>
    </form>
</div>
</body>
</html>
