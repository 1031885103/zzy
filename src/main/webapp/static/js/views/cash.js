$(function () {
    var takedialog = $("#take_dialog");
    var takedatagrid = $("#takedatagrid");

    //定义取单组件,取单对话框takedialog
    takedialog.dialog({
        title: "挂单列表",
        width: 330,
        height: 470,
        buttons: '#employee_dialog_bt',
        closed: true,
        buttons: "#tbt"
    });

    //打开取单对话框
    $("#GetWaiting").click(function () {
        //alert(0);
        takedialog.dialog("open");
    });
    //定义取单数据表格
    takedatagrid.datagrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/saleBill/queryStoreBill',
        columns: [
            [
                {field: 'optime', align: 'center', width: 130, title: '挂单时间'}
            ]
        ]

    });


    $('#autocomplete').autocomplete({
        lookup: function (query, done) {
            // Do Ajax call or lookup locally, when done,
            // call the callback and pass your results:
            var result = {
                suggestions: [
                    {"value": "猫粮", "data": "猫"},
                    {"value": "狗粮", "data": "狗"},
                    {"value": "营养素", "data": "营养"}
                ]
            };

            done(result);
        },
        onSelect: function (suggestion) {

        }
    });

    //定义收银对话框

    //对话框
    $("#cash_dialog").dialog({
        title: "收银记录",
        width: 800,
        height: 400,
        buttons: '#employee_dialog_bt',
        closed: true
    });

    //定义收银记录数据表格
    $("#recorddatagrid").datagrid(
        {
            height: 330,
            rownumbers: true,
            singleSelect: true,
            pagination: true,
            url: '/saleBill/list',
            toolbar: '#cashrecord_datagrid_tb',
            columns: [
                [
                    {field: 'id', align: 'center', width: 130, title: '订单编号'},
                    {field: 'vipCard', align: 'center', width: 130, title: '会员卡号', formatter: idFormatter},
                    {field: 'storename', align: 'center', width: 130, title: '店铺名称', formatter: storeFormatter},
                    {field: 'optime', align: 'center', width: 130, title: '订单时间'},
                    {field: 'totalNumber', align: 'center', width: 130, title: '商品总数'},
                    {field: 'totalAmount', align: 'center', width: 130, title: '消费金额'},
                    {field: 'saleman', align: 'center', width: 130, title: '操作人', formatter: manFormat}

                ]
            ]
        }
    );


    $("#cashrecord").click(function () {
        //alert(0);

        $("#cash_dialog").dialog("open");

        /* $("#main_tabs").tabs("add", {
         title: "收银记录",
         closable: true
         //href:node.attributes.url//只能加载远程页面中的body部分的内容
         /!*
         content: '<iframe src="' + node.attributes.url + '" style="width:100%;height:100%" frameborder="0"></iframe>'
         *!/
         })*/

    });
    //对页面中的元素进行抽取.
    //方法太凌乱,希望统一管理
    //按钮在JS统一进行监听
    var employeeDatagrid, employeeEditBtnAndQuitBtn, employeeDialog, employeeForm, employeeSearchBtn;
    employeeDatagrid = $("#employee_datagrid");
    employeeEditBtnAndQuitBtn = $("#employee_editBtn,#employee_quitBtn");
    employeeDialog = $("#employee_dialog");
    employeeForm = $("#employee_form");
    employeeSearchBtn = $("#searchBtn");
    //数据表格
    employeeDatagrid.datagrid({
        /*fit: true,*/
        onClickCell: onClickCell,
        height: 300,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/productStock/list',
        toolbar: '#employee_datagrid_tb',
        columns: [
            [
                {field: 'product', align: 'center', width: 130, title: '商品条码', rowspan: 2, formatter: snFormatter},
                {field: 'product1', align: 'center', width: 130, title: '商品名称', rowspan: 2, formatter: nameFormatter},
                {field: 'p', align: 'center', width: 130, title: '单价', colspan: 2},
                {
                    field: 'storeNumber', align: 'center', width: 130, title: '数量', editor: 'text', rowspan: 2

                },
                {field: 'specification', align: 'center', width: 130, title: '单位', rowspan: 2},
                {field: 'state', align: 'center', width: 130, title: '拆零', rowspan: 2, formatter: zeroFormat},
                {field: 'totalPric', align: 'center', width: 130, title: '总价', colspan: 2, class: 'total'},
                {
                    field: 'delete', align: 'center', width: 130, title: '操作', rowspan: 2, formatter: function (value, rec) {
                    var btn = '<a class="editcls" onclick="editRow(\'' + rec.projectname + '\',\'' + rec.projectpackage + '\')" href="javascript:void(0)"></a>';
                    return btn;
                }
                }
            ],
            [

                {field: 'product2', align: 'center', width: 130, title: '原价', formatter: pFormatter},
                {field: 'product3', align: 'center', width: 130, title: '会员价', formatter: vpFormatter},
                {field: 'admin', align: 'center', width: 130, title: '原价', formatter: totalpFormatter},
                {field: 'dept', align: 'center', width: 130, title: '会员价', formatter: totalvpFormatter}
            ]
        ],
        onLoadSuccess: function (data) {
            $('.editcls').linkbutton({
                plain: true, iconCls: 'icon-delete'
                /* , onClick: function () {
                 var currentTr = $(this).closest("tr");
                 currentTr.remove();
                 }
                 */
            });


        }


    });


    //对话框
    employeeDialog.dialog({
        width: 250,
        height: 350,
        buttons: '#employee_dialog_bt',
        closed: true
    });
    employeeSearchBtn.textbox({
        width: 300,
        label: "商品条码:",
        labelWidth: 70,
        prompt: "请输入商品条码",
        /* buttonText: '搜索',*/
        buttonIcon: 'icon-smq',
        plain: true,
        onClickButton: function () {
            var keyword = $(this).val();
            employeeDatagrid.datagrid("load", {
                keyword: keyword
            });
        }
    });


    //对按钮进行统一事件监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    //输入会员卡号
    $("#telphone").textbox(
        {
            onChange: function (newValue, oldValue) {
                //设置名称,折扣信息
                var tel = $(this).val();
                $.post("/saleBill/queryVip", {tel: tel}, function (data) {
                    $("#vid").val(data.id);
                    console.debug(data.id);
                    $("#MemberName").text(data.name);
                    $("#MemberPrice").text(data.vipCard.balance);
                    $("#MemberGrade").text(data.vipLevel.name);
                    $("#MemberProportion").text(data.vipLevel.productDiscount);
                    $("#ServerProption").text(data.vipLevel.serveDiscount);
                    $("#Price").val($("#totalPrice").text() * 0.01 * data.vipLevel.productDiscount);
                }, "json");


                return newValue;
            }
        }
    );

    //会员结账按钮
    $("#PayMoney").click(function () {

        //提交表单去保存
        var rowData = employeeDatagrid.datagrid("getSelected");
        if (rowData) {
            if (rowData.product) {
                rowData["product.name"] = rowData.product.name;
                rowData["product.sn"] = rowData.product.sn;
                rowData["product.salePrice"] = rowData.product.salePrice;
                rowData["product.vsalePrice"] = rowData.product.vsalePrice;
                rowData["number"] = rowData.storeNumber;

                //4.回显数据
                employeeForm.form("load", rowData);//基于同名匹配规则

                //设置小计
                var price = rowData.product.salePrice;

                //找零,获取实收金额的值,应收金额的值
                var nowPrice = parseInt($("#Sprice").val());
                var yPrice = parseInt($("#Price").val());
                console.debug(nowPrice);
                console.debug(yPrice);
                if (nowPrice < yPrice) {
                    $.messager.alert("温馨提示", "金额不足", "warning");
                }
                else {
                    $.messager.confirm("温馨提示", "需找零:" + (nowPrice - yPrice) + "元", function (r) {
                        if (r) {
                            //提交会员表单
                            employeeForm.form("submit", {
                                url: "/saleBill/save",
                                onSubmit: function (param) {
                                    param["vipCard"] = $("#telphone").val();
                                },
                                success: function (data) {
                                    data = $.parseJSON(data);
                                    if (data.success) {
                                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                                        $.messager.alert("温馨提示", "结账成功!", "info", function () {
                                            employeeDialog.dialog("close");
                                            employeeDatagrid.datagrid("reload");
                                            window.location.href = "/cash";
                                        });
                                    } else {
                                        $.messager.alert("温馨提示", data.msg, "error");
                                    }
                                }
                            });
                        }
                    })

                }


            }
        }

    });

    //挂单
    $("#Waiting").click(function () {
        //获取当前库存信息

        //提交表单去保存
        var rowData = employeeDatagrid.datagrid("getSelected");
        if (rowData) {
            if (rowData.product) {
                rowData["product.name"] = rowData.product.name;
                rowData["product.sn"] = rowData.product.sn;
                rowData["product.salePrice"] = rowData.product.salePrice;
                rowData["product.vsalePrice"] = rowData.product.vsalePrice;
                rowData["number"] = rowData.storeNumber;

                //4.回显数据
                employeeForm.form("load", rowData);//基于同名匹配规则

                //设置小计
                var price = rowData.product.salePrice;

                //提交会员表单
                employeeForm.form("submit", {
                    url: "/saleBill/storeBill",
                    onSubmit: function (param) {
                        param["vipCard"] = $("#telphone").val();
                    },
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                            $.messager.alert("温馨提示", "挂单成功", "info", function () {
                                employeeDialog.dialog("close");
                                employeeDatagrid.datagrid('loadData', {total: 0, rows: []})
                                employeeSearchBtn.textbox("clear");

                                $('#autocomplete').val("");
                                //清空会员结账表单
                                $("#MemberName").text("");
                                $("#MemberPrice").text("");
                                $("#MemberGrade").text("");
                                $("#MemberProportion").text("");
                                $("#ServerProption").text("");
                                $("#Price").val("");
                                $("#totalPrice").text("");
                                $("#nowPrice").val("");
                                $("#telphone").val("");
                                $("#telphone").textbox("clear");
                                $("#Sprice").val("");
                                $("#saleLayout").layout("clear");
                                $("#saleLayout").layout("refresh");

                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg, "error");
                        }
                    }
                });


            }
        }

    });


    //编辑单元格
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field) {
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex = undefined;

    function endEditing() {
        if (editIndex == undefined) {
            return true
        }
        if ($("#employee_datagrid").datagrid('validateRow', editIndex)) {
            $("#employee_datagrid").datagrid('endEdit', editIndex);
            editIndex = undefined;

            //商品数量
            var number = $("#employee_datagrid").datagrid("getSelected").storeNumber;
            var price = $("#employee_datagrid").datagrid("getSelected").product.salePrice;
            $("#totalPrice").text(number * price);
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing()) {
            $("#employee_datagrid").datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
        if (field == "delete") {
            employeeDatagrid.datagrid("deleteRow", index);
        }
    }


    $("#beginTime").datetimebox({
        onSelect: function (date) {
            var beginTime = (date.getFullYear() + "-" +
            (date.getMonth() + 1) + "-" + date.getDate() +
            " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
            $("#recorddatagrid").datagrid("load", {
                beginTime: beginTime

            });
        }

    });
    $("#endTime").datetimebox({
        onSelect: function (date) {
            var endTime = (date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
                    +
                    " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()
                )
            ;
            $("#recorddatagrid").datagrid("load", {
                endTime: endTime

            });
        }

    });


    //方法统一管理起来]
    var cmdObj = {
        makeTrue: function () {
            //取出挂单数据到页面
            var rowData = takedatagrid.datagrid("getSelected");
            console.debug(rowData);

            if (rowData.items) {
                var productStockId = rowData.items[0].productStockId;

                //发送ajax请求去查询库存信息
                $.post("/saleBill/QueryProductStock", {productStockId: productStockId}, function (data) {
                    var stockData = data;
                    //console.debug(data);//里面有product的sn
                    if (data.product) {
                        var keyword = data.product.sn;
                        employeeDatagrid.datagrid("load", {//根据产品编码去查询
                            keyword: keyword,
                        });
                        //console.debug(data.tsNumber);//挂单的商品数量1
                        // console.debug(data.storeNumber);
                        /* employeeDatagrid.datagrid({
                         onLoad:function (data) {
                         console.log(data.rows[0].storeNumber);

                         data.rows[0].storeNumber=1;
                         }
                         })
                         data.storeNumber=1;*/
                        var rows = employeeDatagrid.datagrid("getData");
                        //拿到会员号
                        //console.debug(rowData.vipCard);110
                        var tel = rowData.vipCard;
                        $.post("/saleBill/queryVip", {tel: tel}, function (data) {
                            $("#vid").val(data.id);
                            console.debug(data.id);
                            $("#telphone").textbox("setValue", tel);
                            $("#telphone").val(tel);
                            $("#MemberName").text(data.name);
                            $("#MemberPrice").text(data.vipCard.balance);
                            $("#MemberGrade").text(data.vipLevel.name);
                            $("#totalPrice").text(stockData.salePrice * stockData.tsNumber);
                            $("#MemberProportion").text(data.vipLevel.productDiscount);
                            $("#ServerProption").text(data.vipLevel.serveDiscount);
                            $("#Price").val($("#totalPrice").text() * 0.01 * data.vipLevel.productDiscount);
                        }, "json");

                    }
                }, "json");
                //将
                takedialog.dialog("close");

            }

        },
        inputCash: function () {
            var rowData = employeeDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                employeeForm.form("clear");
                //2.设置对话框的标题
                employeeDialog.dialog("setTitle", "现金结账");
                //3.打开对话框
                employeeDialog.dialog("open");
                //特殊数据的处理
                /*  if(rowData.dept)
                 rowData["dept.id"] = rowData.dept.id;*/

                if (rowData.product) {
                    rowData["product.name"] = rowData.product.name;
                    rowData["product.sn"] = rowData.product.sn;
                    rowData["product.salePrice"] = rowData.product.salePrice;
                    rowData["product.vsalePrice"] = rowData.product.vsalePrice;

                    //4.回显数据
                    employeeForm.form("load", rowData);//基于同名匹配规则
                    //设置小计
                    var price = rowData.product.salePrice;
                    $("#number").textbox({
                        onChange: function (newValue, oldValue) {
                            /* console.log($("#number").val() * price);*/
                            $("#amount").text($("#number").val() * price);
                            return newValue;
                        }
                    });

                } else {
                    $.messager.alert("温馨提示", "您查找的商品不存在", "info");
                }


            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }
        },
        searchName: function () {
            var productName = $("#autocomplete").val();

            employeeDatagrid.datagrid("load", {
                productName: productName
            });
        },
        vipAccount: function () {
            var rowData = employeeDatagrid.datagrid("getSelected");
        },
        searchRecord: function () {
            //订单高级查询
            var vipId = $("#vipId").val();
            var storeId = $("#storeId").combobox("getValue");

            console.debug(storeId);
            console.log(beginTime);
            console.log(endTime);
            $("#recorddatagrid").datagrid("load", {
                vipId: vipId,
                storeId: storeId,

            });

        },
        edit: function () {
            var rowData = employeeDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                employeeForm.form("clear");
                //2.设置对话框的标题
                employeeDialog.dialog("setTitle", "新增");
                //3.打开对话框
                employeeDialog.dialog("open");
                //特殊数据的处理
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                //4.回显数据
                employeeForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?employeeId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }), "json";

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }

        },
        quit: function () {
            var rowData = employeeDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要离职该员工吗?", function (yes) {
                    if (yes) {
                        $.get("/employee/quit?id=" + rowData.id, function (data) {
                            if (data.success) {
                                employeeDatagrid.datagrid("reload");
                                $.messager.alert("温馨提示", data.msg, "info");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要离职的员工记录.", "warning");
            }
        },
        reload: function () {
            //刷新数据表格
            employeeDatagrid.datagrid("reload");
        },
        save: function () {
            var url = "/saleBill/save";
            employeeForm.form("submit", {
                url: url,
                onSubmit: function (param) {
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            employeeDialog.dialog("close");
                            employeeDatagrid.datagrid("reload");
                            window.location.href = "/cash";
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            employeeDialog.dialog("close");
        }
    }
});


function snFormatter(value, record, index) {
    if (value) {
        return value.sn;
    }

}
function nameFormatter(value, record, index) {
    if (record.product) {
        return record.product.name;
    }

}
function pFormatter(value, record, index) {
    if (record.product) {
        return record.product.salePrice;
    }
}
function vpFormatter(value, record, index) {
    if (record.product) {
        return record.product.vsalePrice;
    }
}
function zeroFormat(value, record, index) {
    if (value.state == 0) {//有产品时
        return "<span>是</span>";
    } else {
        return "<span>否</span>";
    }
}
function totalpFormatter(value, record, index) {

    return record.storeNumber * record.product.salePrice;

}
function totalvpFormatter(value, record, index) {

    return record.storeNumber * record.product.vsalePrice;

}
function storeFormatter(value, record, index) {
    return "老郑";
}
function manFormat(value, record, index) {
    return "admin";
}
function tsFormatter(value, record, index) {
    if (record.tsNumber > 0) {
        console.debug(record.tsNumber);
        return record.tsNumber;
    } else {
        return value;
    }

}
function idFormatter(value, record, index) {
    if (value == null) {
        return "非会员";
    } else {
        return value;
    }

}















