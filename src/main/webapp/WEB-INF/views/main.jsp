<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/plugins/jquery_portal/jquery.portal.js"></script>
    <link media="all" href="/static/css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="/static/js/plugins/jquery_portal/portal.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="/static/js/views/main.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#pt').portal({
                border: false,
                fit: true
            });
            //初始化大框架后，添加portal元素
            add();
        });

        function closedIndex() {
            $.messager.confirm("温馨提示", "您确定要退出系统吗?", function (yes) {
                if (yes) {
                    $.post("/logout", function () {
                        window.location.reload();
                    });
                }
            })
        }

        function add() {
            $('#pt').portal('resize');
        }

        //动态显示时间
        var timerID = null;
        var timerRunning = false;

        function showtime() {
            var now = new Date();
            document.clock.thetime.value = now.toLocaleString() + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
            timerID = setTimeout("showtime()", 1000);
            timerRunning = true;
        }

        function backup() {
            window.location.href = '/main/mysql_backup';
        }

        function restore() {
            $.messager.confirm('温馨提示', '确认要还原数据库吗?', function (r) {
                if (r) {
                    $.post('/main/mysql_restore', function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', data.msg, 'info');
                            window.location.reload();
                        } else {
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    })
                }
            })
        }
    </script>

</head>

<body onload="showtime()">
<font color="red"></font>

<%--面板关闭--%>
<div id="mm" class="easyui-menu" style="width: 150px;">
    <div id="refresh" data-options="iconCls:'icon-arrow_refresh'">刷新</div>
    <div class="menu-sep"></div>
    <div id="close">关闭</div>
    <div id="closeall">全部关闭</div>
    <div id="closeother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="closeright">当前页右侧全部关闭</div>
    <div id="closeleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="exit">退出</div>
</div>
<%--对话框关闭--%>
<div id="cc" class="easyui-layout" fit="true">
    <div data-options="region:'north',split:true,border:false" align="center"
         style="height: 100px; width: 20%;
         background:url('/static/Images/index/baiIndex3.png'); background-repeat:no-repeat; ">
        <!-- 界面上方当前用户,时间,退出系统 -->
        <div id="top">
            <div id="top_links" style="float: right;margin-right: 70px;margin-top: 20px">
                <div id="top_op" style="width: 260px;height: 50px;">
                    <img alt="当前用户" src="/static/Images/common/user.png" onclick="updatePsw()"
                         style="width: 35px; height: 35px;margin-top: 8px; float: left;"/>
                    <a style="float: left; margin-top:13px; font-size: 20px;">
                        &nbsp;&nbsp;<font color="green"><span id="currentName"><shiro:principal property="username"/></span></font>
                    </a>
                    <img alt="退出系统" title="退出系统" onclick="closedIndex()"
                         style="width: 35px; height: 35px;margin-top: 8px; margin-right:80px; float: right;"
                         src="/static/Images/common/out.png"/>
                    <span id="user_span"></span>
                    <span style="font-size: 20px">
                        <form name=clock><input name="thetime"
                                                style="background-color:transparent;font-size: 10pt;color:#000000;border:0;margin-right: 66px;margin-top: 7px"
                                                size=29></form>
                    </span>

                </div>
                <div>
                    <marquee
                            style="margin-top: 3px; width: 280px; height: 20px; position: absolute; right: 120px; top: 2px; opacity: 0.5; z-index: 99999;"
                            onclick="top.f_addTab('home');" direction="left">
                        <img src="/static/Images/meun/r1.png">
                        <a target="t_blank" style="color:blue" href="http://www.byiaa.com/News/70.html">给宠物最至尊的享受,是我们的荣耀!!</a>
                    </marquee>
                </div>
            </div>
        </div>
    </div>
    <div class="l-layout" data-options="region:'west',title:'菜单',split:true,border:false"
         style="width:125px;background-color: #1aceff">
        <div>
            <ul id="TabPage2" style="height:200px; background-color: #1aceff;height: 90%; margin-left: 3px;margin-top: 30px">
                <li style="margin-top: 15px;"></li>
                <li id="member_manager" style="height: 14%">
                    <div class="" data-rootmenu="member_manager" data-title="会员管理" data-url="/pet">
                        <lable style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="会员管理" title="会员管理"
                                                                                                          src="/static/Images/index/cwgl.png">会员管理
                        </lable>
                    </div>
                <li>
                <li id="collectmoney_manager" style="height: 14%">
                    <div class="active" data-rootmenu="collectmoney_manager" data-title="收银管理" data-url="/cash">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="收银管理" title="收银管理"
                                                                                                          src="/static/Images/index/sygl.png">收银管理</label>
                    </div>
                </li>
                <li id="pet_manager" style="height: 14%">
                    <div class="" data-rootmenu="pet_manager" data-title="宠物服务" data-url="/petService">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="宠物服务" title="宠物服务"
                                                                                                          src="/static/Images/index/fw.png">宠物服务</label>
                    </div>
                </li>
                <li id="repertory_manager" style="height: 14%">
                    <div class="" data-rootmenu="repertory_manager" data-title="库存管理" data-url="/productStock1">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="库存管理" title="库存管理"
                                                                                                          src="/static/Images/index/kcgl.png">库存管理</label>
                    </div>
                </li>
                <li id="statement_analyze" style="height: 14%">
                    <div class="" data-rootmenu="statement_analyze" data-title="报表分析" data-url="/saleBill1">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="报表分析" title="报表分析"
                                                                                                          src="/static/Images/index/bbgl.png">报表分析</label>
                    </div>
                </li>
                <li id="statement_employee" style="height: 14%">
                    <div class="" data-rootmenu="statement_employee" data-title="员工管理" data-url="/employee/list">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="员工管理" title="员工管理"
                                                                                                          src="/static/Images/index/yggl.png">员工管理</label>
                    </div>
                </li>
                <li id="system_manager" style="height: 14%">
                    <div class="" data-rootmenu="system_manager" data-title="系统设置" data-url="/systemSetting">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="系统设置" title="系统设置"
                                                                                                          src="/static/Images/index/xtsz.png">系统设置</label>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <%--  <div id="win" class="easyui-window" title="快速操作"
           style="width: 600px; height: 400px"
           data-options="iconCls:'icon-save',modal:true,closed:true">
          Window Content
      </div>--%>
    <div data-options="region:'center'" style="padding: 5px;">
        <div id="mtTabs" class="easyui-tabs" fit="true">
            <div title="欢迎页" closable="true" id="pt" style="background: url('/static/Images/meun/background.jpg')no-repeat;background-size: cover">
                <!--修改密码页面-->
                <div id="resetPsw_dialog">
                    <form id="editbox" class="easyui-form">
                        <input type="hidden" name="id">

                        <div style="margin-top: 40px" align="center">
                            <input name="username" disabled="true" class="easyui-textbox"
                                   data-options="label:'用户民',labelWidth:70,width:200,required:true">
                        </div>
                        <div style="margin-top: 40px" align="center">
                            <input name="password" type="password" class="easyui-textbox"
                                   data-options="label:'密码',labelWidth:70,width:200,required:true,validType:'minLength[4]'">
                        </div>
                        <div style="margin-top: 40px" align="center">
                            <input name="repassword" type="password" class="easyui-textbox"
                                   data-options="label:'重新输入',labelWidth:70,width:200,required:true,validType:'minLength[4]'">
                        </div>
                    </form>
                </div>
                <!--底部保存/取消按钮-->
                <div id="bt" data-options="closed:true">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="cancel()">取消</a>
                </div>
                <%-----------------------------------------------------%>
                <div id="tb">
                    <div>
                        <span style="color: red;margin-top: 20px">数据库备份还原</span>
                        <a class="easyui-linkbutton" data-options="width:40,height:20,plain:true" style="background-color: #e3f1f1;margin-left: 5px"
                           onclick="backup()">备份</a>
                        <a class="easyui-linkbutton" data-options="width:40,height:20,plain:true" style="background-color: #e3f1f1;margin-left: 10px"
                           onclick="restore()">还原</a>
                    </div>
                    <span style="margin-left: 260px;color: red"><font size="3">仓库预警</font></span>
                </div>

                <div class="easyui-panel" style="width: 650px;height: 300px;float: left;margin-left: 10px"
                     data-options="title:'仓库预警',closable:true,collapsible:true,minimizable:true,maximizable:true">
                    <table id="main" class="easyui-datagrid" style="width: 250px; height: auto"></table>
                    <div style="margin-left: 10px;margin-top: 20px;clear:right;margin-bottom: 20px;float:left;
                            width:233px;height:105px;;border:1px solid #CCC;padding:2px">
                        <div align="center" style="margin-top: 25px;"></div>
                    </div>
                </div>
                <%--日历--%>
                <div style="margin-left: 680px;margin-top: 0px">
                    <iframe style="margin-right: 10px" width="420" scrolling="no" height="60" frameborder="0"
                            allowtransparency="true"
                            src="http://i.tianqi.com/index.php?c=code&id=12&color=%23000000&icon=1&num=3&site=12">
                    </iframe>
                </div>
            </div>
        </div>
    </div>
    <div data-options="region:'east',split:true,border:false" align="center" style="width: 80px; background: #1aceff;">

        <div class="easyui-calendar" style="width:150px;height:150px;margin-bottom: 0px;margin-right: 0px"></div>
    </div>
    <div data-options="region:'south',split:true,border:false" align="center" style="height: 60px; background: #1aceff;">
        <div style="margin-bottom: 0px">
            <marquee behavior="scroll" direction="left" scrolldelay=2>
                <img src=/static/Images/dogs/2.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/4.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/22.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/5.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/7.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/8.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/9.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/10.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/11.png hspace=1 width=40 height=40>
                <img src=/static/Images/dogs/12.png hspace=1 width=40 height=40>
            </marquee>
        </div>
        <div style="margin-top: 0px" align="center"> 版权信息: | 白牙宠物 , 伴你一路</div>
    </div>
</div>
</div>
</body>
</html>
