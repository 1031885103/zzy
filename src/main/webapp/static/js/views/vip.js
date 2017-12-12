$(function () {
    var vipDatagrid, vipEditBtnAndQuitBtn, vipDialog, vipForm, vipSearchBtn, vipDatagridTb;
    vipDatagrid = $("#vip_datagrid");
    vipEditBtnAndQuitBtn = $("#vip_editBtn,#vip_quitBtn");
    vipDialog = $("#vip_dialog");
    vipForm = $("#vip_form");
    vipSearchBtn = $("#searchBtn");
    vipDatagridTb = vipDatagridTb;

    //数据表格
    vipDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        url: '/vip/list',
        // fitColumns:true,
        toolbar: '#vip_datagrid_tb',
        columns: [
            [
                {field: 'sn', align: 'center', width: 100, title: '会员号'},
                {field: 'pets', align: 'center', width: 100, title: '宠物名称', formatter: petNameFt},
                {field: 'pets1.petCategory', align: 'center', width: 100, title: '宠物品种', formatter: petPcNameFt},
                {field: 'pets2', align: 'center', width: 100, title: '性别', formatter: genderFt},
                {field: 'pets3', align: 'center', width: 100, title: '生日', formatter: petBirthdayFt},
                {field: 'pets4', align: 'center', width: 100, title: '血统', formatter: petAncestryFt},
                {field: 'pets5', align: 'center', width: 100, title: '毛色', formatter: petColourFt},
                {field: 'amount', align: 'center', width: 100, title: '余额'},
                {field: 'address', align: 'center', width: 380, title: '地址'},
                {field: 'remark', align: 'center', width: 120, title: '备注'}
            ]
        ]
    });
    //对话框
    vipDialog.dialog({
        width: 250,
        height: 380,
        buttons: '#vip_dialog_bt',
        closed: true
    });
    vipSearchBtn.textbox({
        width: 230,
        label: "关键字:",
        labelWidth: 50,
        prompt: "请输入搜索关键字",
        buttonText: '搜索',
        buttonIcon: 'icon-search',
        onClickButton: function () {
            var keyword = $(this).val();
            vipDatagrid.datagrid("load", {
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
        add: function () {
            //1.清空表单数据
            vipForm.form("clear");
            //2.设置对话框的标题
            vipDialog.dialog("setTitle", "新增");
            //3.打开对话框
            vipDialog.dialog("open");
        },
        edit: function () {
            var rowData = vipDatagrid.datagrid("getSelected");
            if (rowData) {
                //1.清空表单数据
                vipForm.form("clear");
                //2.设置对话框的标题
                vipDialog.dialog("setTitle", "新增");
                //3.打开对话框
                vipDialog.dialog("open");
                //特殊数据的处理
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                //4.回显数据
                vipForm.form("load", rowData);//基于同名匹配规则
                //回显角色信息.
                //[1,3]----->List<Long>
                $.post("/role/queryRoleIdListForEmployeeForm?vipId=" + rowData.id, function (data) {
                    $("#roleId").combobox("setValues", data);
                }), "json";

            } else {
                $.messager.alert("温馨提示", "请选择一条需要修改的数据.", "warning");
            }

        },
        quit: function () {
            var rowData = vipDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要离职该员工吗?", function (yes) {
                    if (yes) {
                        $.get("/vip/quit?id=" + rowData.id, function (data) {
                            if (data.success) {
                                vipDatagrid.datagrid("reload");
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
            vipDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/vip/update";
            } else {
                url = "/vip/save";
            }
            vipForm.form("submit", {
                url: url,
                onSubmit: function (param) {
                    //获取所有的角色信息
                    var roleIds = $("#roleId").combobox("getValues");
                    //把角色信息放入到表单中
                    for (var i = 0; i < roleIds.length; i++) {
                        param["roles[" + i + "].id"] = roleIds[i];
                    }
                    return true;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示消息,当点确定的时候,关闭对话框,刷新数据表格
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            vipDialog.dialog("close");
                            vipDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            vipDialog.dialog("close");
        }
    }
});


//============== 华丽丽的分割线  =======================


function petNameFt(value) {

    //因为这是一个数组,所以我们需要进行遍历
    var str = "";
    if (value) {
        for (var index = 0; index <= value.length; index++) {
            str = str + value[index].name;
            return str;
        }
    }
    return str;
}

//品种
function petPcNameFt(value, row) {

    /*for(var index = 0; index <= row.pets.petCategory.length; index++){
        console.log(row.pets[index]);
    }*/
    if(row.pets){
        console.log("进入");
        return  row.pets[0].petCategory.name;
    }

}

function genderFt(value, row, index) {
    if (row.pets) {
        for (var index = 0; index <= row.pets.length; index++) {
            if (row.pets[index].gender == 1) {
                return "<span style='color:green;'>弟弟</span>"
            } else {
                return "<span style='color:green;'>妹妹</span>"
            }
        }
    }
}

//生日
function petBirthdayFt(value, row) {
    if (row.pets) {
        for (var index = 0; index <= row.pets.length; index++) {
            return row.pets[index].birthday;
        }
    }
}

//血统
function petAncestryFt(value, row) {
    if (row.pets) {
        for (var index = 0; index <= row.pets.length; index++) {
            if (row.pets[index].ancestry == 1) {
                return "<span style='color:red;'>√</span>"
            } else {
                return "<span style='color:red;'>×</span>"
            }
        }
    }
}

//毛色
function petColourFt(value, row) {
    if (row.pets) {
        for (var index = 0; index <= row.pets.length; index++) {
            return row.pets[index].colour;
        }
    }
}

/*
{field:'sn',align:'center',width:100,title:'会员号'},
{field:'pet.Name',align:'center',width:100,title:'宠物名称',formatter:petNameFt},
{field:'petCategory',align:'center',width:100,title:'宠物品种',formatter:petPcNameFt},
{field:'gender',align:'center',width:100,title:'性别',formatter:genderFt},
{field:'birthday',align:'center',width:100,title:'生日',formatter:petBirthdayFt},
{field:'petAncestry',align:'center',width:100,title:'血统',formatter:petAncestryFt},
{field:'petColour',align:'center',width:100,title:'毛色',formatter:petColourFt},
{field:'amount',align:'center',width:100,title:'余额'},
{field:'address',align:'center',width:380,title:'地址'},
{field:'remark',align:'center',width:120,title:'备注'}
]*/
