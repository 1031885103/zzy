<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>无标题文档</title>
    <script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".main_box div").click(function () {
                $(".main_box div").removeClass("now")
                $(this).addClass("now")
            })
        })
        function clickMenu(url) {
            if (url != null) {
                $("#showgrid").attr("src", url);
            }
        }
    </script>
    <script type="text/javascript"></script>
    <style>
        .l-text-wrapper
        {
            position: relative;
            float: left;
            margin: 10px;
        }.main_box{
            height: 90px;
            min-width:1100px;
            overflow: hidden;
            width: 100%;
            margin: 5px auto 0 10px;
            padding-top:5px;
        }.now{
            border: 2px solid #ccc;
            box-shadow:none;
        }.imageButton
        {
            background: #fafbfb none repeat scroll 0 0;
            box-shadow:1px 2px 5px 0 rgba(111, 111, 111, 0.7);
            float: left;
            height: 70px;
            margin-right:27px;
            padding-top:10px;
            width: 90px;
        }
    </style>
</head>
<body>
<div class="main_box">
    <div class="imageButton" onclick="clickMenu('/specification')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/guige.png">
        <p style="text-align: center; margin-top: 7px;">商品规格 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/log')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/guige.png" width="30">
        <p style="text-align: center; margin-top: 7px;">登录日志 </p>
    </div>
    <span class="imageButton" onclick="clickMenu('/productType')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/leimu.png" width="30">
        <p style="text-align: center; margin-top: 7px;">商品类目 </p>
    </span>
    <div class="imageButton" onclick="clickMenu('/productManufacturer')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/changshang.png" width="30">
        <p style="text-align: center; margin-top: 7px;">生产厂商 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/productBrand')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/pinpai.png" width="30">
        <p style="text-align: center; margin-top: 7px;">商品品牌 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/productElement')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/chengfen.png" width="30">
        <p style="text-align: center; margin-top: 7px;">商品成分 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/commodity')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/xinxi.png" width="30">
        <p style="text-align: center; margin-top: 7px;">商品信息 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/rank')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/dengji.png" width="30">
        <p style="text-align: center; margin-top: 7px;">会员等级 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/petBreed')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/pinzhong.png" width="30">
        <p style="text-align: center; margin-top: 7px;">宠物品种 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/supplier')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/gongying.png" width="30">
        <p style="text-align: center; margin-top: 7px;">供应商管理 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/subbranch')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/churuku.png" width="30">
        <p style="text-align: center; margin-top: 7px;">分店管理 </p>
    </div>

</div>
<div class="Box_image">
    <iframe style="width:100%;height:500px;" id="showgrid" name="showgrid" frameborder="1" src="/commodity"></iframe>
</div>
</body>
</html>
