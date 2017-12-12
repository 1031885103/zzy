<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会员管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/pet.js"></script>
</head>
<body>
<table id="pet_datagrid"></table>



<%--//======================================    添加会员     ======================================================--%>

<!-- 定义对话框 -->
<div id="pet_dialog">
    <form id="pet_form" method="post">
        <br/>
        <div style="color: blue">&nbsp;&nbsp;&nbsp;主人信息 :</div>

        <input type="hidden" name="vipId">
        <div style="margin-top: 10px;">
            <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="vipTel" style="color: red">手机号码:&nbsp;&nbsp;<input type="text" name="vipTel" class="easyui-textbox"
                                                                 data-options="labelPosition:'top',required:'true', width:110">&nbsp;&nbsp;&nbsp;</span>
                <span id="vipName"  style="color: red">会员姓名:&nbsp;&nbsp;<input type="text" name="vipName" class="easyui-textbox"
                                                                 data-options="labelPosition:'top',required:'true', width:110">&nbsp;&nbsp;&nbsp;</span>
                <span style="color: red">首冲金额:&nbsp;&nbsp;<input type="text" name="cardBalance" class="easyui-textbox"
                                                                 data-options="labelPosition:'top',required:'true', width:110">&nbsp;&nbsp;&nbsp;</span>
                会员等级:&nbsp;&nbsp;
                <select id="vipGender" name="vipLevels" class="easyui-combobox"
                        data-options="
                        panelHeight:'auto',
						 width:120,
						 valueField:'vleId',
						 textField:'vleName',
						 url:'/vipLevel/selectListVipLevel'
						">
                </select>
                &nbsp;&nbsp;
                <span id="productCount" style="color: red"></span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="serveCount" style="color: red"></span>&nbsp;&nbsp;&nbsp;&nbsp;

            </div>
            <br/>

            <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <font size="2">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:
                </font> &nbsp;&nbsp;&nbsp; 男&nbsp;<input type="radio" name="vipGender" value="1">&nbsp;&nbsp;
                女&nbsp;<input type="radio" name="vipGender" value="2">&nbsp;&nbsp;
                不详&nbsp;<input type="radio" name="vipGender" value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


                <font size="2">生&nbsp;&nbsp;&nbsp;&nbsp;日:&nbsp;&nbsp;</font>
                <input type="text" name="vipBirthday" class="easyui-datebox" data-options="labelPosition:'top', width:110">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


                <font size="2">备&nbsp;&nbsp;&nbsp;&nbsp;注:&nbsp;&nbsp;<input type="text" name="vipRemark" class="easyui-textbox"></font>

            </div>
            <br/>

            <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <font size="2">住&nbsp;&nbsp;&nbsp;&nbsp;址:&nbsp;&nbsp;<input type="text" name="vipAddress" class="easyui-textbox"
                                                                             data-options="labelPosition:'top', width:400">&nbsp;&nbsp;&nbsp;</font>
            </div>


            <br/>

            <div style="color: blue">&nbsp;&nbsp;&nbsp;宠物信息 :</div>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

          <div>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <font size="2"><span style="color: red">宠物名:&nbsp;&nbsp;<input type="text" name="petName" class="easyui-textbox"
                                                                           data-options="labelPosition:'top',required:'true', width:120">&nbsp;&nbsp;&nbsp;</span></font>

            <font size="2">宠物生日:&nbsp;&nbsp;</font>
            <input type="text" name="petBirthday" class="easyui-datebox" data-options="labelPosition:'top', width:110">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <font size="2">颜&nbsp;&nbsp;&nbsp;色:&nbsp;&nbsp;<input type="text" name="petColour" class="easyui-textbox"
                                                                   data-options="labelPosition:'top', width:120">&nbsp;&nbsp;&nbsp;</font>

            <font size="2">特&nbsp;&nbsp;&nbsp;征:&nbsp;&nbsp;<input type="text" name="petTrait" class="easyui-textbox"
                                                                   data-options="labelPosition:'top', width:200">&nbsp;&nbsp;&nbsp;</font>

        </div>

        <br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color: red">宠物类型:&nbsp;&nbsp;
                <select name="parentId" class="easyui-combobox"
                        data-options="
                        required:'true',
                        panelHeight:'auto',
						 width:120,
						 valueField:'id',
						 textField:'name',
						 url:'/petCategory/selectPetType'
						">
                </select></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <span style="color: red">宠物品种:&nbsp;&nbsp;
                <select name="id" class="easyui-combobox"
                        data-options="
                        required:'true',
                        panelHeight:'auto',
						 width:120,
						 valueField:'id',
						 textField:'name',
						 url:'/petCategory/selectPetKind',
						">
                </select></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            过敏药:&nbsp;&nbsp;:&nbsp;&nbsp;
            <select  class="easyui-combobox" name="petMaterial" data-options="
                        width:50,
                        panelHeight:'auto',
                       " ><option value="0">无</option>
                          <option value="1">有</option>
            </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            血&nbsp;&nbsp;统&nbsp;&nbsp;:&nbsp;&nbsp;
             <select  class="easyui-combobox" name="petAncestry" data-options="
                        width:50,
                        panelHeight:'auto',
                       " ><option value="0">无</option>
                          <option value="1">有</option>
             </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        </div>
        <br/>


        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <font size="2">宠物性别:
            </font> &nbsp;&nbsp;&nbsp; 雄性&nbsp;<input type="radio" name="petGender" value="1">&nbsp;&nbsp;
            雌性&nbsp;<input type="radio" name="petGender" value="2">&nbsp;&nbsp;
            who能辨我是雄雌&nbsp;<input type="radio" name="petGender" value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


            <font size="2">宠物状态:
            </font> &nbsp;&nbsp;&nbsp;
            健康&nbsp;<input type="radio" name="petState" value="1">&nbsp;&nbsp;
            生病&nbsp;<input type="radio" name="petState" value="2">&nbsp;&nbsp;
            绝育&nbsp;<input type="radio" name="petState" value="3">&nbsp;&nbsp;
            已逝&nbsp;<input type="radio" name="petState" value="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div><br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">血统登记号:&nbsp;&nbsp;<input type="text" name="petRegisterNumber" class="easyui-textbox"
                                                                   data-options="labelPosition:'top', width:100">&nbsp;&nbsp;&nbsp;</font>

            <font size="2">血统登记处:&nbsp;&nbsp;<input type="text" name="petRegistryAddress" class="easyui-textbox"
                                                                   data-options="labelPosition:'top', width:230">&nbsp;&nbsp;&nbsp;</font>
        </div>
    </form>
</div>



<%--==============================================================  添加宠物表单  ===========================================================================--%>

<%--<table id="petDog_datagrid"></table>--%>
<!-- 定义对话框 -->
<div id="petDog_dialog">
    <form id="petDog_form" method="post">

        <br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span id="vipNumber" style="color: red">会员编号:&nbsp;&nbsp;<input type="text" name="vips.vipTel" class="easyui-textbox"
                                                         data-options="labelPosition:'top',required:'true', width:110"/>&nbsp;&nbsp;&nbsp;</span>
            <span style="color: red">宠物名:&nbsp;&nbsp;<input type="text" name="petName" class="easyui-textbox"
                                                         data-options="labelPosition:'top',required:'true', width:110"/>&nbsp;&nbsp;&nbsp;</span>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color: red">宠物类型:&nbsp;&nbsp;
                <select class="easyui-combobox", name="parentId"
                        data-options="
                        required:'true',
						 width:106  ,
						  valueField:'id',
						  textField:'name',
						  panelHeight:'auto',
						 url:'/petCategory/selectPetType'
						">
                </select></span>&nbsp;&nbsp;
            <span style="color: red">宠物品种:&nbsp;&nbsp;
                <select name="id" class="easyui-combobox"
                        data-options="
                        required:'true',
						 width:106  ,
						 valueField:'id',
						 textField:'name',
						 panelHeight:'auto',
						  url:'/petCategory/selectPetKind',
						">
                </select></span>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">生&nbsp;&nbsp;&nbsp;&nbsp;日:&nbsp;&nbsp;</font>
            <input type="text" name="petBirthday" class="easyui-datebox" data-options="labelPosition:'top', width:110">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            颜&nbsp;&nbsp;色:<input type="text" name="petColour" class="easyui-textbox"
                                                            data-options="labelPosition:'top', width:110">&nbsp;&nbsp;&nbsp;
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">宠物性别:
            </font> &nbsp;&nbsp;&nbsp; 雄性&nbsp;<input type="radio" name="petGender" value="1">&nbsp;&nbsp;
            雌性&nbsp;<input type="radio" name="petGender" value="2">&nbsp;&nbsp;
            雌雄同体&nbsp;<input type="radio" name="petGender" value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">宠物状态:
            </font> &nbsp;&nbsp;&nbsp; 健康&nbsp;<input type="radio" name="petState" value="1">&nbsp;&nbsp;
            生病&nbsp;<input type="radio" name="petState" value="2">&nbsp;&nbsp;
            绝育&nbsp;<input type="radio" name="petState" value="3">&nbsp;&nbsp;
            已逝&nbsp;<input type="radio" name="petState" value="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            过敏物:&nbsp;&nbsp;<select  class="easyui-combobox" name="petAncestry" data-options="
                        width:50,
                        panelHeight:'auto',
                       " ><option value="0">无</option>
                <option value="1">有</option>
            </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">特&nbsp;&nbsp;&nbsp;征:&nbsp;&nbsp;<input type="text" name="petTrait" class="easyui-textbox"
                data-options="labelPosition:'top', width:200">&nbsp;&nbsp;&nbsp;</font>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">血统登记号:&nbsp;&nbsp;<input type="text" name="petRegisterNumber" class="easyui-textbox"
                                                    data-options="labelPosition:'top', width:100">&nbsp;&nbsp;&nbsp;</font>

            <font size="2">血统登记处:&nbsp;&nbsp;<input type="text" name="petRegistryAddress" class="easyui-textbox"
                                                    data-options="labelPosition:'top', width:230">&nbsp;&nbsp;&nbsp;</font>
        </div>
    </form>
</div>


<%--=========================================================  会员充值提交  =======================================================================--%>

<div id="vipMoney_dialog">
    <form id="vipMoney_form" method="post">
        <br/>

         <div>
             &nbsp;&nbsp;&nbsp;&nbsp;充值单编号:&nbsp;<span id="orderId" style="color: red"></span>
         </div><br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">会员卡号&nbsp;:&nbsp;<input type="text" name="vips.vipTel" class="easyui-textbox"
                                                                   data-options="labelPosition:'top', width:100">&nbsp;&nbsp;&nbsp;</font>

            <font size="2">卡内余额&nbsp;:&nbsp;<span name="" id="cardBalance" style="background-color: black;color: white"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>


            <font size="2">历史消费总额&nbsp;:&nbsp;<span id="cardTotalMoney" style="background-color: black;color: white"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>

        </div><br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">充值金额&nbsp;:&nbsp;<input type="text" name="cardMoney" class="easyui-textbox"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
        </div><br/>

        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <font size="2">
                会员等级:&nbsp;&nbsp;
                <select id="cardBVipLevel" name="vipLevel.vleName" class='easyui-combobox'></select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>

            <span id="productCount2" style="color: red">会员折扣</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span id="serveCount2" style="color: red"></span>&nbsp;&nbsp;&nbsp;&nbsp;

        </div><br/>


    </form>
</div>





<!-- 定义顶部按钮 -->
<div id="pet_datagrid_tb">
    <div>

        <a class="easyui-linkbutton" iconCls="icon-chongzhi" plain="true" data-cmd="money">一键充值</a>
        <a class="easyui-linkbutton" iconCls="icon-vip" plain="true" data-cmd="addVip">添加会员</a>
        <a class="easyui-linkbutton" iconCls="icon-dog2" plain="true" data-cmd="addPet">添加宠物</a>
        <a id="pet_editBtn" class="easyui-linkbutton" iconCls="icon-newedit" plain="true" data-cmd="edit">编辑</a>
        <a id="pet_delBtn" class="easyui-linkbutton" iconCls="icon-newdel" plain="true" data-cmd="del">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-newreload" plain="true" data-cmd="reload">刷新</a>
    </div>&nbsp;

    <div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        宠物名称&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" class="textbox" name="selectPetName">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        手机号码&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" class="textbox" name="selectVipTel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        备&nbsp;&nbsp;注:&nbsp;&nbsp;<input type="text" class="textbox" name="selectVipRemark">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <a id="selectKeyword" class="easyui-linkbutton" iconCls="icon-sousuo">搜&nbsp;&nbsp;索</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a id="pay"  class="easyui-linkbutton" iconCls="icon-jilu"  data-cmd="#">充值记录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div><br/>


</div>
<!-- 对话框底部按钮 -->
<div id="pet_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-saveVip" plain="true" data-cmd="saveVip">添加会员</a>
    <a class="easyui-linkbutton" iconCls="icon-quxiao" plain="true" data-cmd="cancelVip">取消</a>
</div>
<div id="petDog_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-newdog" plain="true" data-cmd="savePet">添加宠物</a>
    <a class="easyui-linkbutton" iconCls="icon-quxiao" plain="true" data-cmd="cancelPet">取消</a>
</div>
<div id="vipMoney_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-chongzhi" plain="true" data-cmd="vipMoney">一键充值</a>
    <a class="easyui-linkbutton" iconCls="icon-quxiao" plain="true" data-cmd="cancelMoney">取消</a>
</div>

</body>
</html>