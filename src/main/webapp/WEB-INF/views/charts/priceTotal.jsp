<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据报表</title>
    <script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/static/js/plugins/Echarts/echarts-all.js"></script>
    <script type="text/javascript">

        $(function() {

            // 设置时间控件的样式
            $("#linedate").addClass("Wdate").click(function() {
                WdatePicker({
                    maxDate: new Date()
                });
            })
            // 回显报表类型
            $("#lineselect option[value=${qo.chartType}]").prop("selected", true);

            var myChart = echarts.init(document.getElementById("main"), "macarons");
            var option = {
                title : {
                    text: '${groupBy}',
                    subtext: '含销售额,成本,利润',
                    textStyle: {
                        fontWeight: 'bold',              //标题颜色
                        color: '#000000'
                    }
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['销售额','成本', '利润']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ${groupTypeList}
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'销售额',
                        type:'line',
                        data:${totalSaleAmountList},
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name:'成本',
                        type:'line',
                        data:${totalCostAmountList},
                        markPoint : {
                            data : [
                                {name : '年最高', type: 'max'},
                                {name : '年最低', type: 'min'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'利润',
                        type:'line',
                        data:${profits},
                        markPoint : {
                            data : [
                                {name : '年最高', type: 'max'},
                                {name : '年最低', type: 'min'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    }
                ]
            };

            myChart.setOption(option);

        })

        function reload() {
            $("#bar_form").submit();
        }
    </script>
</head>
<body>
<form id="bar_form" method="post" action="/saleBill1/chartByBar">
<div id="one" style="display: block">
    <div>
        <div style="float:left;margin:10px 10px 0 0">
            <input id="linedate" name="opDate" onchange="reload()" value="<fmt:formatDate value='${qo.opDate }'/>">
        </div>
        <div style="float:left">
            <select id="lineselect" onchange="reload()" name="chartType" style="margin-top: 10px; height: 23px;">
                <option value="0">周报表</option>
                <option value="1">月报表</option>
                <option value="2">年报表</option>
            </select>
            <span>本报表是以选择日期为准分别显示周报表（选择日起前7天每日数据）、月报表（选择日期当月每日数据）、年报表（选择日期当年每月数据）</span>
        </div>
    </div>
    <div id="main" style="height:400px; margin-left: 10px; margin-top: 20px"></div>
</div>
</form>

</body>
</html>
