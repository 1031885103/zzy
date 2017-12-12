<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>白牙宠物店云管理平台注册</title>
    <link href="/static/css/registerstyle.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="/static/Images/register/index.ico"/>

    <link href="/static/css/jquery.autocomplete.css" rel="stylesheet"/>
    <link href="/static/css/jquery.bigautocomplete.css" rel="stylesheet"/>
    <link href="/static/css/newstyle.css" rel="stylesheet"/>

    <script src="/static/js/register/jquery-1.7.1.js"></script>
    <script src="/static/js/register/LG.js"></script>
    <script src="/static/js/register/jquery.autocomplete.js"></script>
    <script src="/static/js/register/jquery.bigautocomplete.js"></script>

    <script src="/static/js/register/base.js"></script>
    <script src="/static/js/register/all.js"></script>

    <script src="/static/js/register/Dsy.js"></script>
    <script src="/static/js/register/register.js"></script>
    <%@include file="common.jsp" %>
    <script type="application/javascript">
        $(function () {
            $(document).keydown(function (event) {
                //当键盘按下回车键登录
                if (event.keyCode == 13) {
                    registerForm();
                }
            });
            //或者点击页面登录按钮进行登录
            $("#btn_login").click(function () {
                registerForm();
            });
        });
        function registerForm() {
            if (checkStoreAddress() && checkStoreHead() && checkStoreName() && checkRepeatpassword() && checkUsername() && checkpassword() && checkaddress()) {
                $.post("/register/login", $("form").serialize(), function (data) {
                    if (data.success) {
                        $.messager.confirm("注册成功", "立即登录", function (yes) {
                            if (yes) {
                                window.location.href = "/login";
                            }
                                $("#Username").val("");
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error", function () {
                        });
                    }
                }, "json");
            }
        }
    </script>
</head>
<body>
<header>
    <div class="w12 header">
        <a class="db logo fl">
            <img src="/static/Images/register/logo.jpg" width="327" height="94" alt=""/></a>
        <div class="fr logofr">客服热线：<strong style="font-size: 14px;">400-993-1007</strong></div>
    </div>
</header>
<div class="head_border"></div>
<section class="w12 login"><em class="fr">已经是会员，请&nbsp;&nbsp;<a class="db logbtn fr" href="/login">前往登录</a> </em></section>
<section class="main w12">
    <div class="title"></div>
    <div class="fr tit2"></div>
    <form method="post">
        <!-- <div class="w12 step"></div>-->
        <div class="inputsec">
            <ul>
                <li>
                    <label class="fl mr2">登录名：</label>
                    <input type="text" tabindex="3" name="Username" id="Username" autocomplete="off" onfocus="if(this.value=='请输入登录名'){this.value=''}"
                           onblur="checkUsername();" class="txt-m fl " value="请输入登录名">
                    <label id="UsernameMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">登录密码：</label>
                    <input class="txt-m fl " type="password" id="PassWord" name="password" onblur="checkpassword()" img="" src="/Images/name_06.jpg"
                           required="true">
                    <label id="passwordMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">重复密码：</label>
                    <input class="txt-m fl " type="password" id="Repeatpassword" name="repassword" onblur="checkRepeatpassword()" img=""
                           src="/Images/name_06.jpg" required="true">
                    <label id="RepeatpasswordMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">年龄：</label>
                    <input id="Age" type="number" name="age" style="width:100px;decoration:none;" onblur="checkAge()"
                           required="true">
                    <label id="AgeMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">性别：</label>
                    <div id="Gender" onblur="checkGender()">
                        <input type="radio" name="gender" value="男"><span>男</span>
                        <input type="radio" name="gender" value="女"><span>女</span>
                    </div>
                    <label id="GenderMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">邮箱：</label>
                    <input type="email" tabindex="3" name="email" id="Email" autocomplete="off" onblur="checkEmail();" class="txt-m fl"
                           required="true">
                    <label id="EmailMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">联系方式：</label>
                    <input type="text" tabindex="3" name="tel" id="Tel" autocomplete="off" onblur="checkTel();" class="txt-m fl" required="true">
                    <label id="TelMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">店铺名：</label>
                    <input type="text" tabindex="3" name="storename" id="StoreName" autocomplete="off"
                           onfocus="if(this.value=='请慎重填写，确认后不可修改'){this.value=''}" onblur="checkStoreName();" class="txt-m fl "
                           value="请慎重填写，确认后不可修改" required="true">
                    <label id="StoreNameMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">店主姓名：</label>
                    <input type="text" tabindex="3" name="storeMaster" id="StoreHead" autocomplete="off"
                           onfocus="if(this.value=='请慎重填写，确认后不可修改'){this.value=''}" onblur="checkStoreHead();" class="txt-m fl "
                           value="请慎重填写，确认后不可修改" required="true">
                    <label id="StoreHeadMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li>
                    <label class="fl mr2">店铺地址：</label>
                    <select id="Province" name="address" class="txt-m fl" style="width:85px;padding-left:3px">
                        <option>省份</option>
                    </select>
                    <select id="City" name="address" class="txt-m fl" style="width:auto;padding-left:3px">
                        <option>地级市</option>
                    </select>
                    <select id="County" name="address" class="txt-m fl" style="width:auto;padding-left:3px">
                        <option>市、县级市、县</option>
                    </select>
                </li>
                <li>
                    <label class="fl mr2">详细地址：</label>
                    <input id="StoreAddress" type="text" tabindex="3" name="address" autocomplete="off"
                           onfocus="if(this.value=='请精确到门牌号'){this.value=''}" class="txt-m fl ">
                    <label id="StoreAddressMsg" style="float: left; width: 250px; text-align: left;"></label>
                </li>
                <li class="clr">
                    <label class="db fl">&nbsp;</label>
                    <input type="button" id="btn_login" style="background-color: #7cb61b" class="db fl next high" value="完成注册"/>
                </li>
            </ul>
        </div>
    </form>
</section>
<div class="foot-copy">
    <p>Copyright &copy;2013-2015 白牙宠物&nbsp;&nbsp; 渝ICP备13006802号-4</p>
</div>
</body>
</html>
