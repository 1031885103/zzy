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
    <div class="imageButton" onclick="clickMenu('/employee')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/Line.png">
        <p style="text-align: center; margin-top: 7px;">基本信息 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/work')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/Pie.png" width="30">
        <p style="text-align: center; margin-top: 7px;">职位设置 </p>
    </div>
    <span class="imageButton" onclick="clickMenu('/vacate')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/report.png" width="30">
        <p style="text-align: center; margin-top: 7px;">请假记录 </p>
    </span>
    <div class="imageButton" onclick="clickMenu('/money')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/report.png" width="30">
        <p style="text-align: center; margin-top: 7px;">薪资计算 </p>
    </div>
    <div class="imageButton" onclick="clickMenu('/role')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/image/report.png" width="30">
        <p style="text-align: center; margin-top: 7px;">角色管理 </p>
    </div>
</div>
<div class="Box_image">
    <iframe style="width:100%;height:500px;" id="showgrid" name="showgrid" frameborder="1" src="/employee"></iframe>
</div>
</body>
</html>
