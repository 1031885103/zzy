$(function () {

    var petDatagrid, petEditBtnAndQuitBtn, petDialog,
        petForm, petSearchBtn, petDatagridTb, petDogDialog,
        petDogForm, vipMoneyDialog, vipMoneyForm,
        selectKeyword, val;

    petDatagrid = $("#pet_datagrid");
    petEditBtnAndQuitBtn = $("#pet_editBtn,#pet_quitBtn");
    petDialog = $("#pet_dialog");
    petForm = $("#pet_form");
    petSearchBtn = $("#searchBtn");
    petDatagridTb = petDatagridTb;
    petDogDialog = $("#petDog_dialog");
    petDogForm = $("#petDog_form");
    vipMoneyDialog = $("#vipMoney_dialog");
    vipMoneyForm = $("#vipMoney_form");
    selectKeyword = $("#selectKeyword");


    //数据表格
    petDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/pet/list',
        toolbar: '#pet_datagrid_tb',
        columns: [
            [
                {field: 'vips', align: 'center', width: 100, title: '会员号', formatter: vipSnFt},
                {field: 'petName', align: 'center', width: 100, title: '宠物名称'},
                {field: 'petCategory', align: 'center', width: 100, title: '宠物品种', formatter: petPcNameFt},
                {field: 'petGender', align: 'center', width: 100, title: '性别', formatter: genderFt},
                {field: 'petBirthday', align: 'center', width: 100, title: '生日'},
                {field: 'petAncestry', align: 'center', width: 100, title: '血统', formatter: petAncestryFt},
                {field: 'petColour', align: 'center', width: 100, title: '毛色'},
                {field: 'cardBalance', align: 'center', width: 100, title: '余额', formatter: balance},
                {field: 'vips1', align: 'center', width: 380, title: '地址', formatter: vipAddressFt},
                {field: 'vips2', align: 'center', width: 120, title: '备注', formatter: vipRemarFt}
            ]
        ]
    });

//=========================================================================================

    //添加会员对话框
    petDialog.dialog({
        width: 1000,
        height: 440,
        buttons: '#pet_dialog_bt',
        closed: true,
        modal: true,
    });

    //添加宠物对话框
    petDogDialog.dialog({
        width: 580,
        height: 420,
        buttons: '#petDog_dialog_bt',
        closed: true,
        modal: true,
    });

    //充值对话框
    vipMoneyDialog.dialog({
        width: 550,
        height: 330,
        buttons: '#vipMoney_dialog_bt',
        closed: true,
        modal: true,
    });


//=========================================================================================

    //高级查询(渲染按钮,添加点击事件,提交参数)
    selectKeyword.linkbutton({
        onClick: function () {
            //获取到参数值
            var selectPetName = $("[name='selectPetName']").val();
            var selectVipTel = $("[name='selectVipTel']").val();
            var selectVipRemark = $("[name='selectVipRemark']").val();

            petDatagrid.datagrid("load", {
                selectPetName: selectPetName,
                selectVipTel: selectVipTel,
                selectVipRemark: selectVipRemark
            });
        }
    });

    petSearchBtn.textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 50,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: function () {
            var keyword = $(this).val();
            petDatagrid.datagrid("load", {
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

    //方法统一管理起来]
    var cmdObj = {

        //添加会员
        addVip: function () {
            //1.清空表单数据
            petForm.form("clear");
            petDatagrid.datagrid("reload");
            //2.设置对话框的标题
            petDialog.dialog("setTitle", "添加会员");
            //3.打开对话框
            petDialog.dialog("open");
        },
        //添加宠物
        addPet: function () {
            //1.清空表单数据
            petDogForm.form("clear");
            //2.设置对话框的标题
            petDogDialog.dialog("setTitle", "添加宠物");
            //3.打开对话框
            petDogDialog.dialog("open");
        },

        //===============================================添加宠物====================================================
        addPet: function () {
            var selectRow = petDatagrid.datagrid("getSelected");
            console.log(selectRow);
            if (selectRow) {

                //打开对话框(清空数据)
                petDogForm.form("clear");
                petDogDialog.dialog("open");
                petDogDialog.dialog("setTitle", "添加宠物");
                //回显
                //会员编号
                if (selectRow.vips)
                //把vips中的sn 作为 selectRow的属性
                    selectRow["vips.vipTel"] = selectRow.vips.vipTel;


                petDogForm.form("load", selectRow);//基于同名匹配规则
            } else {
                $.messager.alert("温馨提示", "请选择主人,么么哒!", "warning");
            }
        },
        cancelPet: function () {
            //刷新数据表格
            petDatagrid.datagrid("reload");
        },

        //==================================================  添加会员 ==========================================
        saveVip: function () {
            var url;
            var idVal = $("[name='vipId']").val();
            if (idVal) {
                url = "/pet/update";
            } else {
                url = "/pet/save";
            }
            petForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);

                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            petDialog.dialog("close");
                            petDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //取消
        cancelVip: function () {
            petDialog.dialog("close");
        },

        //添加宠物
        savePet: function () {
            //我们还需要获取到选中行的 vipId ,添加宠物时保存关系
            var selectRow = petDatagrid.datagrid("getSelected");
            var vipId = selectRow.vips.vipId;

            petDogForm.form('submit', {
                url: "/pet/save",
                onSubmit: function (param) {
                    param.vipId = vipId;
                },
                success: function (data) {
                    data = $.parseJSON(data);

                    if (data.success) {

                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            petDogDialog.dialog("close");
                            petDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },

        cancelPet: function () {
            petDogDialog.dialog("close");
        },

        //=========================================================  充值回显  =================================
        money: function () {
            //获取到选中行,回显操作
            var selectRow = petDatagrid.datagrid("getSelected");

            if (!selectRow) {
                $.messager.alert("温馨提示", "请选择一条需要充值的数据", "warning");
                return;
            }

            if (selectRow.vips) {



                //获取到 该选中行的 vipId ,发送请求 查询出该 vipId对应的会员卡信息
                var vipId = selectRow.vips.vipId;
                $.post("/vipCard1/queryCardMsgByVipId?vipId=" + vipId, function (data) {
                    $("#cardTotalMoney").text(data.cardTotalMoney);
                    $("#cardBalance").text(data.cardBalance);
                    //订单编号: 生成随机数
                    var number = Math.random();
                    var number2 = Math.floor(number * 1000);
                    var number3 = ("20171114" + number2);
                    $("#orderId").text(number3);

                console.log(selectRow.vips.vipTel);
                    selectRow["vips.vipTel"] = selectRow.vips.vipTel;
                    selectRow["vipLevel.vleName"] = selectRow.vipLevel.vleName;

                    vipMoneyForm.form("load", selectRow);
                }, "json");

                //打开对话框(清空数据)
                vipMoneyForm.form("clear");
                vipMoneyDialog.dialog("open");
                vipMoneyDialog.dialog("setTitle", "充值");

            }
        },

        //======================================================== 一键充值  ======================================
        vipMoney: function () {

            var selectRow = petDatagrid.datagrid("getSelected");
            var vipId = selectRow.vips.vipId;

            vipMoneyForm.form('submit', {
                url: "/vipCard1/update",
                onSubmit: function (param) {
                    param.vipId = vipId;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {

                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            vipMoneyDialog.dialog("close");
                            petDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancelMoney: function () {
            vipMoneyDialog.dialog("close");
        },

    }

    // 充值卡 会员等级  回显
    $("#cardBVipLevel").combobox({
        panelHeight: 'auto',
        width: 120,
        valueField: 'vleId',
        textField: 'vleName',
        url: '/vipLevel/selectListVipLevel'
    });

    //会员等级 显示 折扣
    $("#vipGender").combobox({
        onChange: function () {
            var selectId = $("#vipGender").combobox("getValue");
            $.post("/vipLevel/queryLevelCount?selectId=" + selectId, function (data) {
                //给span元素设置文本值
                if (data) {

                    $("#productCount").text("商品折扣  " + data.vleProductDiscount + " 折");
                    $("#serveCount").text("商品折扣  " + data.vleServeDiscount + " 折");
                }

            });
        },
    });


    $("#cardBVipLevel").combobox({
        onChange: function () {
            var selectId = $("#cardBVipLevel").combobox("getValue");

            if(!isNaN(selectId)) {

                $.post("/vipLevel/queryLevelCount?selectId=" + selectId, function (data) {
                    //给span元素设置文本值
                    if (data) {
                        $("#productCount2").text("商品折扣  " + data.vleProductDiscount + " 折");
                        $("#serveCount2").text("商品折扣  " + data.vleServeDiscount + " 折");
                    }

                });
            }
        },
    });


});


//============== 分割线  =======================

function vipSnFt(value, row) {


    if (row.vips) {
        return row.vips.vipId;
    }

}

function vipAddressFt(value, row) {

    if (row.vips) {  //虽然上面 使用的是 vips1 但是只是为了区分开 , 取值的话,还是通过vips 来获取到 , 记得要加上row.
        return row.vips.vipAddress;
    }
}

function vipRemarFt(value, row) {
    if (row.vips) {
        return row.vips.vipRemark;
    }
}

//品种
function petPcNameFt(value, row) {
    if (value) {
        return value.name;
    }
}

//性别
function genderFt(value, row, index) {
    if (value == 1) {
        return "<span style='color:green;'>弟弟</span>"
    } else {
        return "<span style='color:green;'>妹妹</span>"
    }
}

//血统
function petAncestryFt(value, row) {

    if (value == 1) {
        return "<span style='color:red;'>√</span>"
    } else {
        return "<span style='color:red;'>×</span>"
    }
}

//余额
function balance(value, row) {
    if (row.vipCard) {
        return row.vipCard.cardBalance;
    }
}
