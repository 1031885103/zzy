$(function () {
    var productStockItrmDatagrid, editForm;
    productStockItrmDatagrid = $("#productStockItem_datagrid");
    editForm = $("#editForm");
    productStockItrmDatagrid.datagrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        url: "/productStockItem/list",
        columns: [[
            {field: 'sn', title: '商品条码', width: 10, align: 'center', formatter:productSn},
            {field: 'name', title: '商品名称', width: 25, align: 'center', formatter:productName},
            {field: 'vdate', title: '入库时间', width: 20, align: 'center'},
            {field: 'type', title: '商品类别', width: 10, align: 'center', formatter: productType},
            {field: 'specification', title: '商品规格', width: 10, align: 'center'},
            {field: 'costPrice', title: '进价(元)', width: 10, align: 'center'},
            {field: 'salePrice', title: '售价(元)', width: 15, align: 'center', formatter: productSalePrice},
            {field: 'vsalePrice', title: '会员价(元)', width: 10, align: 'center',formatter: productVsalePrice},
            {field: 'amount', title: '数量', width: 10, align: 'center'},
            {field: 'totalPrice', title: '进货总价(元)', width: 10, align: 'center'},
            {field: 'warningDate', title: '预警日期', width: 12, align: 'center'},
            {field: 'safeDate', title: '保质期', width: 12, align: 'center'},
            {field: 'employeeName', title: '操作人', width: 10, align: 'center'}
        ]]
    });
    var cmdObj = {
        //添加高级查询
        queryKey: function () {
            var productSn = $("[name=productSn]").val();
            var productName = $("[name=productName]").val();
            productStockItrmDatagrid.datagrid('load', {
                productSn: productSn,
                productName: productName
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
function productType(value, row) {
    if (row.product) {
        return row.product.categoryName;
    }
}
function productSalePrice(value, row) {
    if (row.product) {
        return row.product.salePrice;
    }
}
function productIngredient(value, row) {
    if (row.product) {
        return row.product.ingredient;
    }
}
function productVsalePrice(value, row) {
    if (row.product) {
        return row.product.vsalePrice;
    }
}

