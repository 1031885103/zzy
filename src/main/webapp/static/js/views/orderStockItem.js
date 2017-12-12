$(function () {
    var orderStockItrmDatagrid, editForm;
    orderStockItrmDatagrid = $("#orderStockItem_datagrid");
    editForm = $("#editForm");
    orderStockItrmDatagrid.datagrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        url: "/orderStockItem/list",
        columns: [[
            {field: 'sn', title: '商品条码', width: 10, align: 'center', formatter: productSn},
            {field: 'name', title: '商品名称', width: 25, align: 'center', formatter: productName},
            {field: 'vdate', title: '发货时间', width: 15, align: 'center'},
            {field: 'subbranchName', title: '进货分店', width: 15, align: 'center'},
            {field: 'state', title: '状态', width: 10, align: 'center', formatter: stateFormatter},
            {field: 'employeeName', title: '发货人', width: 10, align: 'center'},
            {field: 'remark', title: '备注', width: 15, align: 'center', formatter: remarkFormatter}
        ]]
    });
    var cmdObj = {
        //添加高级查询
        queryKey: function () {
            var productSn = $("[name=productSn]").val();
            var productName = $("[name=productName]").val();
            var subbranchName = $("[name=subbranchName]").val();
            orderStockItrmDatagrid.datagrid('load', {
                productSn: productSn,
                productName: productName,
                subbranchName: subbranchName
            })
        }
    }
    //对按钮进行统一事件监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
});
function productSn(value, row) {
    if (row.product) {
        return row.product.sn;
    }
}
function productName(value, row) {
    if (row.product) {
        return row.product.name;
    }
}
function stateFormatter(value) {
    if (value == 1) {
        return "<span style='color:green'>已发货</span>";
    } else {
        return "<span style='color:red'>异常</span>";
    }
}
function remarkFormatter(value) {
    if (value) {
        return value;
    } else {
        return "无";
    }
}
