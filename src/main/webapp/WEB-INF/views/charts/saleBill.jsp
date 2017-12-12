
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售订单</title>

    <script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script>
    <script>

        $(document).ready(function () {
            $(".main_box div").click(function () {
                $(".main_box div").removeClass("now")
                $(this).addClass("now")
            })
        })
        function show(obj) {
            $("#index").attr("src", obj)
        }
    </script>
    <style>
        .l-text-wrapper {
            position: relative;
            float: left;
            margin: 10px;
        }

        .main_box {
            height: 90px;
            min-width: 1100px;
            overflow: hidden;
            width: 100%;
            margin: 5px auto 0 10px;
            padding-top: 5px;
        }

        .now {
            border: 2px solid #ccc;
            box-shadow: none;
        }

        .imageButton {
            background: #fafbfb none repeat scroll 0 0;
            box-shadow: 1px 2px 5px 0 rgba(111, 111, 111, 0.7);
            float: left;
            height: 70px;
            margin-right: 27px;
            padding-top: 10px;
            width: 90px;
        }
    </style>

</head>
<body>

<div class="main_box">
    <div class="imageButton now" onclick="show('/saleBill1/chartByBar')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/Images/right/Line.png" /><p style="text-align: center; margin-top: 7px;">数据报表</p>
    </div>
    <div class="imageButton" onclick="show('/saleBill1/chartByPie')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/Images/right/Pie.png" /><p style="font-size:15px;text-align: center; margin-top: 7px;">分类销售比例</p>
    </div>

    <div class="imageButton" onclick="show('/saleBill1/cashierTotal')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/Images/right/sales.png" /><p style="font-size:15px;text-align: center; margin-top: 7px;">收银记录</p>
    </div>
    <div class="imageButton" onclick="show('/saleBill1/goodsSales')">
        <img style="margin-left: 30px; width: 35px; height: 35px" src="/static/Images/right/sale.png" /><p style="font-size:15px;text-align: center; margin-top: 7px;">商品销售记录</p>
    </div>

</div>

<div>
    <iframe id="index" style="width:100%;height:81%" frameborder="0" name="showmessage" src="/saleBill1/chartByBar"></iframe>
</div>

</body>
</html>
