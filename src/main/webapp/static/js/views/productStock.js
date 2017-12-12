$(function () {
    var productStockDatagrid, editForm, productStockItemDialog, productStockItemDatagrid;
    productStockDatagrid = $("#productStock_datagrid");
    editForm = $("#editForm");
    productStockItemDialog = $("#productStockItem_dialog");
    productStockItemDatagrid = $("#productStockItem_datagrid")
    productStockDatagrid.datagrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#btn01",
        url: "/productStock1/list",
        columns: [[
            {field: 'sn', title: '商品条码', width: 10, align: 'center', formatter: productSn},
            {field: 'name', title: '商品名称', width: 35, align: 'center', formatter: productName},
            {field: 'type', title: '商品类别', width: 10, align: 'center', formatter: productType},
            {field: 'brandName', title: '品牌', width: 10, align: 'center', formatter: productBrandName},
            {field: 'ingredient', title: '主要成分', width: 10, align: 'center', formatter: productIngredient},
            {field: 'supplierName', title: '供应商', width: 15, align: 'center', formatter: productsupplierName},
            {field: 'storeNumber', title: '整装库存', width: 10, align: 'center'},
            {field: 'bulkNumber', title: '散装库存', width: 10, align: 'center'},
            {field: 'totalPrice', title: '库存结余(元)', width: 10, align: 'center'},
            {field: 'warningNumber', title: '预警数量', width: 10, align: 'center', formatter: colorFormatter}
        ]],
        onLoadSuccess: function (data) {
            var type = data.total;
            $("#type").text(type);
            var allNub = parseFloat(0);
            $.map(data, function (item) {
                for (var i = 0; i < item.length; i++) {
                    if (item[i].storeNumber) {
                        allNub = allNub + parseFloat(item[i].storeNumber);
                    }
                }
                return allNub;
            });
            var price = parseFloat(0);
            $.map(data, function (item) {
                for (var i = 0; i < item.length; i++) {
                    if (item[i].storeNumber) {
                        price = price + parseFloat(item[i].totalPrice);
                    }
                }
                return price;
            });
            $("#allNub").text(allNub);
            $("#price").text(price);
        },
        onDblClickRow: function (index, row) {
            $("#productStockForm").form("clear");
            productStockItemDialog.dialog("setTitle", "商品入库详细");
            if (row.product.sn) {
                var productSn = row.product.sn;
                productStockItemDatagrid.datagrid("options").url = "/productStockItem/queryByProductSn?productSn=" + productSn;
                productStockItemDatagrid.datagrid("load", true);
                productStockItemDialog.dialog("open");
            }
        }
    });
    //方法统一管理起来]
    var cmdObj = {
        //添加高级查询
        queryKey: function () {
            var productSn = $("[name=productSn]").val();
            var productName = $("[name=productName]").val();
            productStockDatagrid.datagrid('load', {
                productSn: productSn,
                productName: productName
            })
        },
        instock: function () {
            /*var mainTabs = window.parent.document.getElementById("main_tabs");
            console.debug(mainTabs);
            if (mainTabs.tabs("exists","入库/发货/制单")) {
                mainTabs.tabs("select","入库/发货/制单")
            } else {
                mainTabs.tabs("add", {
                    title: "入库/发货/制单",
                    closable: true,
                    //href:node.attributes.url//只能加载远程页面中的body部分的内容
                    content: '<iframe src="/stockBillItem" style="width:100%;height:100%" frameborder="0"></iframe>'
                });
            }*/
            var mainTabs = top.jQuery;

            if (mainTabs("#mtTabs").tabs('exists', "入库/发货/制单")){
                mainTabs("#mtTabs").tabs('select', "入库/发货/制单");
            } else {
                var content = '<iframe src="/stockBillItem" style="width:100%;height:100%" frameborder="0"></iframe>';
                mainTabs("#mtTabs").tabs('add',{
                    title:"入库/发货/制单",
                    content:content,
                    closable:true
                });
            }
            //window.parent.document.location.href="/stockBillItem";
        },
        stockInfo: function () {
            var mainTabs = top.jQuery;

            if (mainTabs("#mtTabs").tabs('exists', "入库信息")){
                mainTabs("#mtTabs").tabs('select', "入库信息");
            } else {
                var content = '<iframe src="/productStockItem" style="width:100%;height:100%" frameborder="0"></iframe>';
                mainTabs("#mtTabs").tabs('add',{
                    title:"入库信息",
                    content:content,
                    closable:true
                });
            }
        },
        sendInfo: function () {
            var mainTabs = top.jQuery;
            if (mainTabs("#mtTabs").tabs('exists', "发货信息")){
                mainTabs("#mtTabs").tabs('select', "发货信息");
            } else {
                var content = '<iframe src="/orderStockItem" style="width:100%;height:100%" frameborder="0"></iframe>';
                mainTabs("#mtTabs").tabs('add',{
                    title:"发货信息",
                    content:content,
                    closable:true
                });
            }
        }
    }
    //对按钮进行统一事件监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    productStockItemDatagrid.datagrid({
        width: 630,
        height: 330,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        columns: [[
            {field: 'sn', title: '商品条码', width: 80, align: 'center', formatter: productSn},
            {field: 'name', title: '商品名称', width: 120, align: 'center', formatter: productName},
            {field: 'vdate', title: '入库时间', width: 140, align: 'center'},
            {field: 'costPrice', title: '进价(元)', width: 60, align: 'center'},
            {field: 'salePrice', title: '售价(元)', width: 60, align: 'center', formatter: productSalePrice},
            {field: 'amount', title: '进货数量', width: 60, align: 'center'},
            {field: 'totalPrice', title: '进货总价', width: 60, align: 'center'}
        ]],
        onLoadSuccess: function (data) {
            var totalNumber = parseFloat(0);
            var totalPrice = parseFloat(0);
            $.map(data, function (item) {
                for (var i = 0; i < item.length; i++) {
                    if (item[i].amount) {
                        totalNumber = totalNumber + parseFloat(item[i].amount);
                    }
                }
                return totalNumber;
            });
            $.map(data, function (item) {
                for (var i = 0; i < item.length; i++) {
                    if (item[i].totalPrice) {
                        totalPrice = totalPrice + parseFloat(item[i].totalPrice);
                    }
                }
                return totalPrice;
            });
            $("#totalNumber").text(totalNumber);
            $("#totalPrice").text(totalPrice);
        }
    });
    productStockItemDialog.dialog({
        width: 650,
        height: 400,
        closed: true
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
function productBrandName(value, row) {
    if (row.product) {
        return row.product.brandName;
    }
}
function productIngredient(value, row) {
    if (row.product) {
        return row.product.ingredient;
    }
}
function productsupplierName(value, row) {
    if (row.product) {
        return row.product.supplierName;
    }
}
function productSalePrice(value, row) {
    if (row.product) {
        return row.product.salePrice;
    }
}
function colorFormatter(value) {
    if (value) {
        return "<span style='color:red'>" + value + "</span>";
    } else {
        return "0";
    }
}
