$(function () {
    $("#main").datagrid({
        fit: true,
        fitColumns: true,//防止水平滚动
        singleSelect: true,//只允许选择一行
        url: "/productStock1/queryForStockWarning",
        pagination: true,//页码
        rownumbers: true,//行号
        pageSize: 3,
        pageList: [3, 5, 10, 20, 30, 40, 50],
        closable: true,
        toolbar: '#tb',
        columns: [[//[thead[tr]]
            {field: 'sn', title: '产品编号', width: 120, align: "center",formatter: productSnFormotter},
            {field: 'product', title: '产品名称', width: 200, align: "center", formatter: productFormatter},
            {field: 'storeNumber', title: '产品库存', width: 150, align: "center",formatter:stockFormatter},
            {field: 'warningNumber', title: '库存预警值', width: 120, align: "center"}
        ]]
    });


    $("a[data-cmd]").on("click", function () {
        var methods = $(this).data("cmd");
        if (methods) {
            cmdObj[methods]();
        }
    });
    var cmdObj = {

        petMethod: function () {
            document.getElementById("indexManager").src = '/petService'
        },
        addMember: function () {
            $('#win').window("open");
            $('#win').window('refresh', '/productStock');
        }
    };

    $("#tabpage2 li").click(function () {
        $.each($("#TabPage2 li"), function (index, item) {
            $(item).removeClass("selected");
        });
        $(this).addClass("selected");
        var title = $(this.children).data("title");
        var url = $(this.children).data("url");
        if ($("#mtTabs").tabs("exists", title)) {
            $("#mtTabs").tabs("select", title);
        } else {
            //往选项卡里添加面板
            $("#mtTabs").tabs("add", {
                title: title,
                closable: true,
                content: '<iframe src="' + url + '" style="width:100%;height:100%" frameborder="0"></iframe>'
            });
        }
    });
    //设置dialog的属性
    $("#resetPsw_dialog").dialog({
        title: "编辑",
        width: 300,
        height: 300,
        buttons: "#bt",
        closed: true
    })
});
//修改密码
function updatePsw() {
    $("#resetPsw_dialog").dialog("setTitle", "修改个人信息");
    $("#editbox").form("clear");

    $.ajax({
        async: false,
        type: "get",
        url: "/main/resetPsw?username=" + $("#currentName").text(),
        success: function (user) {
            var recode = user;
            $("#editbox").form("load", recode);
            //打开表单
            $("#resetPsw_dialog").dialog("open", true);
        }
    });
}
//保存
function save() {
    var username = $("input[name='username']").val();
    var newpsw = $("input[name='password']").val();
    var renewpsw = $("input[name='repassword']").val();
    if(newpsw.length<6){
        return $.messager.alert("错误","密码长度不能少于6个字符","error");
    }
    if(newpsw!=renewpsw){
        return $.messager.alert("错误","两次密码不一致,请重新输入","error");
    }
    if (newpsw) {
        var url = "/main/savePsw?username="+username+"&newpsw="+newpsw;
        $.messager.confirm('温馨提示', '确定修改密码吗?', function (r) {
            if (r) {
                $.post(url, function (data) {
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info');
                        //关闭对话框
                        $("#resetPsw_dialog").dialog("close", true);
                        window.location.reload();
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                })
            }
        })
    }
}
//取消
function cancel() {
    $("#resetPsw_dialog").dialog("close");
}

function jumpCashJsp() {
    if ($("#mtTabs").tabs("exists", '收银管理')) {
        $("#mtTabs").tabs("select", '收银管理');
    } else {
        //往选项卡里添加面板
        $("#mtTabs").tabs("add", {
            title: '收银管理',
            closable: true,
            content: '<iframe src="' + '/cash' + '" style="width:100%;height:100%" frameborder="0"></iframe>'
        });
    }
}

function stock(data) {
    console.log(data);
    if ($("#mtTabs").tabs("exists", $(data).data("title"))) {
        $("#mtTabs").tabs("select", $(data).data("title"));
    } else {
        //往选项卡里添加面板
        $("#mtTabs").tabs("add", {
            title: $(data).data("title"),
            closable: true,
            content: '<iframe src="' + $(data).data("url") + '" style="width:100%;height:100%" frameborder="0"></iframe>'
        });
    }
}

function cashRecord() {
    if ($("#mtTabs").tabs("exists", '收银记录管理')) {
        $("#mtTabs").tabs("select", '收银记录管理');
    } else {
        //往选项卡里添加面板
        $("#mtTabs").tabs("add", {
            title: '收银记录管理',
            closable: true,
            content: '<iframe src="' + '/cashRecord' + '" style="width:100%;height:100%" frameborder="0"></iframe>'
        });
    }
}

function productSnFormotter(value, row, index) {
    if(row.product.sn){
        return row.product.sn;
    }
}

function productFormatter(value, row, index) {
    return value ? row.product.name : value;
}
function stockFormatter(value, row, index) {
    return "<span style='color:red'>"+value+"</span>"
}



//*************************************************************************************
/*
 $(function () {

 tabClose();
 tabCloseEven();

 /!*!//给面板绑定点击事件
 $(".tabs-inner").bind('contextmenu', function (e) {
 e.preventDefault();
 $('#mm').menu('show', {
 left: e.pageX,
 top: e.pageY
 });
 });*!/

 });



 //===========选项卡右击事件==============

 function tabClose()
 {
 /!*双击关闭TAB选项卡*!/
 $(".tabs-inner").dblclick(function(){
 var subtitle = $(this).children(".tabs-closable").text();
 $('#mtTabs').tabs('close',subtitle);
 })
 /!*为选项卡绑定右键*!/
 $(".tabs-inner").bind('contextmenu',function(e){
 $('#mm').menu('show', {
 left: e.pageX,
 top: e.pageY
 });

 var subtitle =$(this).children(".tabs-closable").text();

 $('#mm').data("currtab",subtitle);
 $('#mtTabs').tabs('select',subtitle);
 return false;
 });
 }*/
