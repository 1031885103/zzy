<%--
  Created by IntelliJ IDEA.
  User: ruosu
  Date: 2017/11/10
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>不同收入的项目的饼图</title>
    <script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/plugins/Echarts/echarts-all.js"></script>
    <script type="text/javascript" src="/static/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

        $(function() {

            // 设置日期样式
            $("#linedate").addClass("Wdate").click(function() {
                WdatePicker({
                    maxDate:new Date()
                });
            })

            // 回显select标签
            $("#lineselect option[value=${qo.state}]").prop("selected", true);
            $("#piedateselect option[value=${qo.dateType}]").prop("selected", true);

            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('main'), 'macarons');

            var option = {
                title : {
                    text: '${groupBy}',
                    //Color:'black',
                    //subtext: '纯属虚构',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:${groupTypeList}
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: ${max}
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                series : [
                    {
                        name:'访问来源',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:${resultList}
                    }
                ]
            };

            myChart.setOption(option);
        })
        function reload() {
            $("#pie_form").submit();
        }
    </script>
</head>
<body>
<form id="pie_form" method="post" action="/saleBill1/chartByPie">
<div id="one" style="display: block">
    <div style="margin-left: 10px">
        <div style="float:left;margin:10px 10px 0 0">
            <input id="linedate"  name="opDate" onchange="reload()" value="<fmt:formatDate value='${qo.opDate }'/>"/>
        </div>
        <div style="float:left">
            <%--<select id="lineselect" onchange="LineChart()" name="timeType" style="margin-top: 10px; height: 23px;">--%>
                <%--<option value="0">周报表</option>--%>
                <%--<option value="1">月报表</option>--%>
                <%--<option value="2">年报表</option>--%>
            <%--</select>--%>
            <select id="lineselect" onchange="reload()" name="state" onselect="" style="margin-top: 10px; height: 23px;">
                <option value="0">销售额比例</option>
                <option value="1">销售数量比例</option>
            </select>
        </div>
        <div style="float:left">
           <select id="piedateselect" onchange="reload()" name="dateType" style="margin-top: 10px; height: 23px;">
                <option value="0">所有</option>
                <option value="1">日</option>
                <option value="2">月</option>
                <option value="3">年</option>
            </select>
        </div>
    </div>
</div>
    <div id="main" style="height:400px; margin-left: 10px; margin-top: 20px"></div>
</form>
</body>
</html>
