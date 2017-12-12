document.createElement("section");
document.createElement("article");
document.createElement("footer");
document.createElement("header");
document.createElement("hgroup");
document.createElement("nav");
document.createElement("menu");
//jquery验证登录名
function checkUsername() {
    if ($("#Username").val() == "") {
        $("#Username").attr("class", "txt-ms fl");
        $("#UsernameMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;登录用户名不能为空</font>");

        return false;
    }
    if (!$("#Username").val().match(/^[\u4E00-\u9FA5A-Za-z0-9]+$/)) {
        $("#Username").attr("class", "txt-ms fl");
        $("#UsernameMsg").html("<font color='red' ><img src='/static/Images/register/vili-error.png' />&nbsp;登录名格式不正确！请重新输入！</font>");
        return false;
    }
    if (true) {
        //向后台发送处理数据
        $.ajax({
            type: 'POST',
            url: "/register/checkUsername",
            data: {
                username: $("#Username").val()
            },
            success: function (data1) {
                if (data1 == 'false') {
                    $("#Username").attr("class", "txt-ms fl");
                    $("#UsernameMsg").html("<font color='red' ><img src='/static/Images/register/vili-error.png' />&nbsp;该用户名已被注册！</font>");
                    $("#Username").val("");
                    return false;
                }
            },
        });
    }
    $("#Username").attr("class", "txt-m fl");
    $("#UsernameMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}

//jquery验证密码
function checkpassword() {
    if ($("#PassWord").val() == "") {
        $("#PassWord").attr("class", "txt-ms fl");
        $("#passwordMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;登录密码不能为空</font>");
        return false;
    }
    if (!$("#PassWord").val().match(/^[\A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/)) {
        $("#PassWord").attr("class", "txt-ms fl");
        $("#passwordMsg").html("<font color='red' ><img src='/static/Images/register/vili-error.png' />&nbsp;密码格式不正确！请重新输入！</font>");

        return false;
    }
    $("#PassWord").attr("class", "txt-m fl");
    $("#passwordMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}


//jquery验证重复密码
function checkRepeatpassword() {
    if ($("#Repeatpassword").val() != $("#PassWord").val()) {
        $("#Repeatpassword").attr("class", "txt-ms fl");
        $("#RepeatpasswordMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;两次密码不一致！请重新输入</font>");

        return false;
    }
    $("#Repeatpassword").attr("class", "txt-m fl");
    $("#RepeatpasswordMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}
//验证年龄
function checkAge() {
    if ($("#Age").val()=="" || $("#Age").val()<18 || $("#Age").val()>55) {
        $("#Age").attr("class", "txt-ms fl");
        $("#AgeMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;年龄必须在[18-55]区间</font>");
        $("#Age").val("");
        return false;
    }
    $("#Age").attr("class", "txt-m fl");
    $("#AgeMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}
//验证性别
/*function checkGender() {
    if ($("#Gender").val()=="") {
        $("#Gender").attr("class", "txt-ms fl");
        $("#GenderMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;该项必填</font>");
        $("#Gender").val("");
        return false;
    }
    $("#Age").attr("class", "txt-m fl");
    $("#AgeMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}*/
//验证邮箱
function checkEmail() {
    if (!$("#Email").val().match(/^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(\.[0-9A-Za-z]+)+$/)) {
        $("#Email").attr("class", "txt-ms fl");
        $("#EmailMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;邮箱格式不正确</font>");
        return false;
    }
    $("#Email").attr("class", "txt-m fl");
    $("#EmailMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}
//验证联系方式
function checkTel() {
    if (!$("#Tel").val().match(/^[1][3,4,5,7,8][0-9]{9}$/)) {
        $("#Tel").attr("class", "txt-ms fl");
        $("#TelMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;手机号码不存在</font>");
        return false;
    }
    $("#Tel").attr("class", "txt-m fl");
    $("#TelMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}
//jquery验证店铺名称
function checkStoreName() {
    if ($("#StoreName").val() == "") {
        $("#StoreName").attr("class", "txt-ms fl");
        $("#StoreNameMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;店铺名称不能为空</font>");

        return false;
    }
    if (!$("#StoreName").val().match(/^[0-9A-Za-z\u4e00-\u9fa5]{2,18}$/)) {
        $("#StoreName").attr("class", "txt-ms fl");
        $("#StoreNameMsg").html("<font color='red' ><img src='/static/Images/register/vili-error.png' />&nbsp;店铺名称不正确！请重新输入！</font>");

        return false;
    }
    $("#StoreName").attr("class", "txt-m fl");
    $("#StoreNameMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}


//jquery验证店主名称
function checkStoreHead() {
    if ($("#StoreHead").val() == "") {
        $("#StoreHead").attr("class", "txt-ms fl");
        $("#StoreHeadMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;店主姓名不能为空</font>");

        return false;
    }
    if (!$("#StoreHead").val().match(/^[0-9A-Za-z\u4e00-\u9fa5]{2,18}$/)) {
        $("#StoreHead").attr("class", "txt-ms fl");
        $("#StoreHeadMsg").html("<font color='red' ><img src='/static/Images/register/vili-error.png' />&nbsp;店主姓名不正确！请重新输入！</font>");

        return false;
    }
    $("#StoreHead").attr("class", "txt-m fl");
    $("#StoreHeadMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}
//jquery验证店铺地址
function checkStoreAddress() {
    if ($("#StoreAddress").val() == "") {
        $("#StoreAddress").attr("class", "txt-ms fl");
        $("#StoreAddressMsg").html("<font color='red'><img src='/static/Images/register/vili-error.png' />&nbsp;店铺地址不能为空</font>");

        return false;
    }
    $("#StoreAddress").attr("value", "txt-m fl");
    $("#StoreAddressMsg").html("<font color='red' ><img style='padding-top:15px;' src='/static/Images/register/vili-valid.png' /></font>");
    return true;
}

//地址验证
function checkaddress() {
    if ($("#Province").val() == "省份") {
        $("#Province").attr("class", "txt-ms fl");
        return false;
    }
    if ($("#City").val() == "地级市") {
        $("#City").attr("class", "txt-ms fl");
        return false;
    }
    if ($("#County").val() == "市、县级市、县") {
        $("#County").attr("class", "txt-ms fl");
        return false;
    }
    $("#Province").attr("class", "txt-m fl");
    $("#City").attr("class", "txt-m fl");
    $("#County").attr("class", "txt-m fl");
    return true;
}

//地址
var StoreAddress = "请精确到门牌号";
function address() {
    if (StoreAddress != "请输入店铺详细地址") {
        $("#Province").val(StoreAddress.split(",")[0]);
        City();
        console.log($("#City"))
        County();
        console.log($("#County"))
        $("#City").val(StoreAddress.split(",")[1]);
        County();
        $("#County").val(StoreAddress.split(",")[2]);
        $("#StoreAddress").val(StoreAddress.split(",")[3]);
    }
}
