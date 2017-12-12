<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小码哥客户关系管理系统</title>
    <link rel="stylesheet" href="/static/css/style.css">
    <%@include file="common.jsp" %>

    <script type="text/javascript">
        $(function () {
            $(document).keydown(function (event) {
                //当键盘按下回车键登录
                if (event.keyCode == 13) {
                    loginForm();
                }
            });
            //或者点击页面登录按钮进行登录
            $("#loginbtn").click(function () {
                loginForm();
            });
        });
        function loginForm() {
            $("#loginForm").form("submit", {
                url: "/login",
                success: function (data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        console.log(data.success);
                        window.location.href = "/main";
                    } else {
                        chageCode();
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }
            });
        }
        //链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
        function chageCode() {
            $('#codeImage').attr('src', '/CodeController/verification?abc=' + Math.random());
        }
    </script>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form id="loginForm" method="post" class="easyui-form">
            <p><input type="text" name="username" value="" placeholder="账号"></p>
            <p><input type="password" name="password" value="" placeholder="密码"></p>
            <p><input id="authCode" name="captcha" type="text" placeholder=" 验证码"></p>
            <!--这里img标签的src属性的值为后台实现图片验证码方法的请求地址-->
            <label><img type="image" src="/CodeController/verification" id="codeImage" onclick="chageCode()"
                        title="图片看不清？点击重新得到验证码" style="cursor:pointer;"/></label>
            <label><a onclick="chageCode()">换一张</a></label>
            <label style="margin-left: 100px"></label>
                <input type="checkbox" name="rememberMe" /><font size="2px">自动登陆</font>
            <label></label>
            <p class="submit">
                <input id="loginbtn" type="button" value="登录">
                <input type="button" value="重置">
            </p>
            <div align="c" class="mg">
                <div class="form_reg_btn"><span>还没有帐号？</span><a href="/register">马上注册</a>&nbsp; &nbsp;&nbsp;&nbsp;<a href="/login/sendEmail">忘记密码</a></div>
            </div>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright ©2015 广州小码哥教育科技有限公司</p>
</div>
</body>
</html>
