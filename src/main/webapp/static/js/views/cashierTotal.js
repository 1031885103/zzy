$(function() {

    // 初始化列表
    $("#cashierTotal_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        // 设置隔行换色
        striped:true,
        singleSelect: true,
        url: "/saleBill1/saleBillList",
        rownumbers:true,
        //pagination:true,
        toolbar: "#serach_div",
        columns: [[
            {field:'sn',align:'center',width:10,title:'订单编号'},
            {field:'member',align:'center',width:10,title:'会员卡号', formatter: memberFormatter},
            {field:'store',align:'center',width:10,title:'店铺名称', formatter:storeFormatter},
            {field:'opTime',align:'center',width:10,title:'订单时间'},
            {field:'totalNumber',align:'center',width:10,title:'商品总数'},
            {field:'realAmount',align:'center',width:10,title:'消费金额(元)'},
            {field:'saleman',align:'center',width:10,formatter:salemanFormatter,title:'操作人'},
            {field:'detail',align:'center',width:10,title:'查看',formatter:detailFormatter}
        ]]

    });

    // 初始化详情会话框
    $("#cashierTotal_dialog").dialog({
        title: "本单详情",
        width: 750,
        height: 380,
        closed: true

    });

    // 初始化详情页
    $("#detail_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped:true,
        singleSelect: true,
        url: "/saleBill1/saleBillItemList",
        rownumbers:true,
        pagination:true,
        columns: [[
            {field:'sn',align:'center',width:10,title:'商品条码', formatter:snFormatter},
            {field:'name',align:'center',width:10,title:'商品名称', formatter:nameFormatter},
            {field:'specification',align:'center',width:10,title:'单位', formatter:specificationFormatter},
            {field:'originalprice',align:'center',width:10,title:'商品原价(元)'},
            {field:'releasePrice',align:'center',width:10,title:'折扣(元)'},
            {field:'realprice',align:'center',width:10,title:'售价(元)'},
            {field:'number',align:'center',width:10,title:'销售数量'},
            {field:'remark',align:'center',width:10,title:'折扣原因',formatter:remarkFormatter},
            {field:'salemanName',align:'center',width:10,title:'服务人员'}
        ]]

    });

})

// 高级查询
function search() {
    $("#cashierTotal_datagrid").datagrid("load",{
        VIPNumber: $("#VIPNumber").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val()
    })
}

// 查看订单的详情
function seeDetail(id) {
    // 展示详情
    // 获取当前订单的服务员
    var row = $("#cashierTotal_datagrid").datagrid("getSelected");
    $("#detail_datagrid").datagrid("load",{
        saleBillId: id,
        salemanName: row.saleman.realname
    });
    // 打开会话框
    $("#cashierTotal_dialog").dialog("open");
}



// 明细中和商品相关的格式
function specificationFormatter(value, row, index) {
    if (row.product) {
        return row.product.specification;
    }
}
function snFormatter(value, row, index) {
    if (row.product) {
        return row.product.sn;
    }
}
function nameFormatter(value, row, index) {
    if (row.product) {
        return row.product.name;
    }
}

function remarkFormatter(value, row, index){
    if (row.product) {
        return row.product.remark;
    }
}


// 列表页面的格式
function detailFormatter(value, row, index) {
    //console.debug(row);
    var btn = "<a href='javascript:seeDetail(" + row.id + ")'><img src='/static/Images/detail.png' /></a> ";
    return btn;
}

function storeFormatter(){
    return "白牙宠物";
}

function salemanFormatter(value) {
    if (value) {
        return value.realname;
    }
}

function memberFormatter(value) {
    if (value) {
        return value.vipnumber;
    }
}