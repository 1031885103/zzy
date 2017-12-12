$(function () {
    var stockBillItemDatagrid, editForm, subbranchDialog, subbranchForm, touchDialog, touchDatagrid;
    stockBillItemDatagrid = $("#stockBillItem_datagrid");
    editForm = $("#editForm");
    subbranchDialog = $("#subbranch-dialog");
    subbranchForm = $("#subbranchForm");
    touchDialog = $("#touch-dialog");
    touchDatagrid = $("#touch-datagrid");
    stockBillItemDatagrid.datagrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#btn01",
        url: "/stockBillItem/list",
        columns: [[
            {field: 'sn', title: '商品条码', width: 10, align: 'center', formatter: productSn},
            {field: 'name', title: '商品名称', width: 35, align: 'center', formatter: productName},
            {field: 'inputTime', title: '录入时间', width: 20, align: 'center', formatter: timeFormatter},
            {field: 'product', title: '商品类别', width: 10, align: 'center', formatter: productType},
            {field: 'specification', title: '折零规格', width: 10, align: 'center'},
            {field: 'minunit', title: '最小单位', width: 10, align: 'center'},
            {field: 'maxunit', title: '最大单位', width: 10, align: 'center'},
            {field: 'costPrice', title: '进货单价', width: 10, align: 'center'},
            {field: 'amount', title: '数量', width: 10, align: 'center'},
            {field: 'totalPrice', title: '进货总价', width: 10, align: 'center'},
            {field: 'salePrice', title: '标价', width: 10, align: 'center', formatter: productSalePrice},
            {field: 'vsalePrice', title: '会员价', width: 10, align: 'center', formatter: productVSalePrice},
            {field: 'safeDate', title: '保质期', width: 12, align: 'center'},
            {field: 'stockMethod', title: '出入库方式', width: 10, align: 'center', formatter: methodFormatter}
        ]],
        onLoadSuccess: function (data) {
            var total = parseFloat(0);
            $.map(data, function (item) {
                for (var i = 0; i < item.length; i++) {
                    if (item[i].totalPrice) {
                        total = total + parseFloat(item[i].totalPrice);
                    }
                }
                return total;
            });
            $("#totalPrice").text(total);
        }
    });

    var cmdObj = {
        add: function () {
            if (!($("[name=amount]").val() && $("[name=sn]").val() && $("[name=costPrice]").val() && $("[name=stockMethod]").val())) {
                $.messager.alert("温馨提示", "请输入必填项!!!", "info");
                return;
            }
            editForm.form('submit', {
                url: "/stockBillItem/addStock",
                onSubmit: function (param) {
                    var id = $("[name=id]").val();
                    param['product.id'] = id;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            editForm.form("clear");
                            stockBillItemDatagrid.datagrid("reload", true);
                        });
                    }
                }
            });
        },
        instock: function () {
            var itemRows = stockBillItemDatagrid.datagrid("getRows");
            var ids = $.map(itemRows, function (item) {
                return item.id;
            });
            if (ids.length == 0) {
                $.messager.alert("温馨提示", "请选择要入库的产品!", "info");
                return;
            }
            $.messager.confirm("温馨提示", "你确定要入库所有商品吗？", function (yes) {
                if (yes) {
                    $.get("/stockBillItem/instock?ids=" + ids, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg, "info", function () {
                                stockBillItemDatagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                        }
                    }, "json")
                }
            });
        },
        remove: function () {
            var rowData = stockBillItemDatagrid.datagrid("getSelected");
            if (!rowData) {
                $.messager.alert("温馨提示", "请选择要删除的明细!!", "info");
                return;
            }
            $.messager.confirm("温馨提示", "您确定需要删除该临时明细吗?", function (yes) {
                if (yes) {
                    $.get("/stockBillItem/delete?id=" + rowData.id, function (data) {
                        if (data.success) {
                            stockBillItemDatagrid.datagrid("reload");
                            $.messager.alert("温馨提示", data.msg, "info");
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                        }
                    }, "json")
                }
            });
        },
        outstock: function () {
            subbranchForm.form("clear");
            subbranchDialog.dialog("setTitle", "分店选项");
            subbranchDialog.dialog("open");
        },
        sure: function () {
            var itemRows = stockBillItemDatagrid.datagrid("getRows");
            var ids = $.map(itemRows, function (item) {
                return item.id;
            });
            if (ids.length == 0) {
                $.messager.alert("温馨提示", "请选择要入库的产品!", "info");
                return;
            }
            if (!itemRows) {
                $.messager.alert("温习提示", "请选择要发货的产品!", "info");
                return;
            }
            var subId = $("[name=subId]").val();
            if (!subId) {
                $.messager.alert("温习提示", "请选择要发货的分店!", "info");
                return;
            }
            $.post("/stockBillItem/outstock?", {ids: ids, subId: subId}, function (data) {
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg, "info", function () {
                        subbranchDialog.dialog("close");
                        stockBillItemDatagrid.datagrid("reload");
                    });
                } else {
                    $.messager.alert("温馨提示", data.msg, "error");
                }
            }, "json");
        },
        cancel: function () {
            subbranchDialog.dialog("close");
        },
        printTouch: function () {
            //清除表单
            $("#touchForm").form("clear");
            touchDialog.dialog("setTitle", "打印单据");
            //获取数据
            var itemRows = stockBillItemDatagrid.datagrid("getRows");
            //数据加载
            touchDatagrid.datagrid("loadData", itemRows);
            //设置时间
            var date = new Date();
            var time = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" +
                date.getMinutes() + ":" + date.getSeconds();
            $("#time").text(time);
            //打开
            touchDialog.dialog("open", true);
        },
        cancelTouch: function () {
            touchDialog.dialog("close");
        },
        print: function () {
            window.location.href = "http://www.baidu.com";
        },
        reset: function () {
            editForm.form("clear");
        }
    }

    $("a[data-cmd]").on('click', function () {
        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    });

    subbranchDialog.dialog({
        width: 250,
        height: 250,
        buttons: "#btn02",
        closed: true
    });
    touchDialog.dialog({
        width: 650,
        height: 350,
        buttons: "#btn03",
        closed: true
    });
    touchDatagrid.datagrid({
        width: 630,
        height: 160,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'sn', title: '商品条码', width: 12, align: 'center', formatter: productSn},
            {field: 'name', title: '商品名称', width: 18, align: 'center', formatter: productName},
            {field: 'amount', title: '数量', width: 10, align: 'center'},
            {field: 'maxunit', title: '单位', width: 10, align: 'center'},
            {field: 'maxunit1', title: '规格', width: 13, align: 'center', formatter: valueFormatter}
        ]]
    });
    $(document).keydown(function (event) {
        //当键盘按下回车键登录
        if (event.keyCode == 8) {
            $("#productSn,#productName").combobox("clear");
        }
    });
});

//绑定失去焦点事件
function getProduct() {
    var productSn = $(this).val();
    $.post("/product/queryProductByProductSn", {productSn: productSn}, function (data) {
        $("#editForm").form("load", data);
    }, "json");
}
function getProductName() {
    var productName = $(this).val();
    $.post("/product/queryProductByProductName", {productName: productName}, function (data) {
        $("#editForm").form("load", data);
    }, "json");
}


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
function valueFormatter(value, row) {
    if (row.product) {
        return 1 + "" + row.maxunit;
    }
}
function productVSalePrice(value, row) {
    if (row.product) {
        return row.product.vsalePrice;
    }
}
function methodFormatter(value) {
    if (value) {
        return "<span style='color:red'>" + value + "</span>";
    }
}
//格式化时间
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
function timeFormatter(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
}