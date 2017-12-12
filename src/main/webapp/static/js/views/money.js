$(function(){
    //对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var moneyDatagrid,moneyDialog,moneyForm,moneySearchBtn;
	moneyDatagrid = $("#money_datagrid");
	moneyDialog = $("#money_dialog");
	moneyForm = $("#money_form");
	moneySearchBtn = $("#searchBtn");
	//数据表格
	moneyDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/money/list',
		fitColumns:true,
		toolbar:'#money_datagrid_tb',
		columns:[
			[
				{field:'sn',align:'center',width:10,title:'工号'},
				{field:'name',align:'center',width:10,title:'姓名'},
				{field:'salary',align:'center',width:10,title:'基本工资'},
				{field:'job',align:'center',width:10,title:'岗位津贴'},
				{field:'traffic',align:'center',width:10,title:'交通补贴'},
				{field:'tel',align:'center',width:10,title:'话费补贴'},
				{field:'meal',align:'center',width:10,title:'餐补'},
				{field:'every',align:'center',width:10,title:'全勤'},
				{field:'proportion',align:'center',width:10,title:'提成'},
				{field:'holiday',align:'center',width:10,title:'请假'},
				{field:'total',align:'center',width:10,title:'总计'
					//,formatter:totalFormatter
				},
                {field:'operation',align:'center',width:10,title:'操作', formatter:function(value,row,index){
                    var str = "";
                    str += '<a href="#" class="edit" onclick="editUser('+index+')" ></a>|';
                    str += '<a href="#" class="remove" onclick="delUser('+index+')"></a>';
                    return str;
				}}
			]
		],
        onLoadSuccess:function(data){
            $(".edit").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
            $(".remove").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
        },
	});
	//对话框
	moneyDialog.dialog({
		width:650,
		height:550,
		buttons:'#money_dialog_bt',
		closed:true
	});
    moneySearchBtn.textbox({
		width:230,
		label:"品牌名称:",
        labelWidth:60,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            moneyDatagrid.datagrid("load",{
            	keyword:keyword
			});
		}
    });

	//对按钮进行统一事件监听
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	
	//方法统一管理起来
	var cmdObj = {
		add:function(){
			//1.清空表单数据
			moneyForm.form("clear");
			//2.设置对话框的标题
			moneyDialog.dialog("setTitle","新增");
			//3.打开对话框
			moneyDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/money/update";
				}else{
					url = "/money/save";
				}
				moneyForm.form("submit",{
					url:url,
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								moneyDialog.dialog("close");
								moneyDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			moneyDialog.dialog("close");
		}


	}

});
//
// function totalFormatter(value,row,index){
// 	if(index) {
//         var total = (parseFloat($("#salaryId").val()) + parseFloat($("#jobId").val()) +
//         parseFloat($("#trafficId").val()) + parseFloat($("#telId").val()) +
//         parseFloat($("#mealId").val()) + parseFloat($("#everyId").val()) +
//         parseFloat($("#proportionId").val()) - parseFloat($("#holidayId").val())).toFixed(2);
//
//         console.log(total);
//         return total;
//     }
// }






function editUser(index){
    $("#money_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#money_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#money_form").form("clear");
        //2.设置对话框的标题
        $("#money_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#money_dialog").dialog("open");
        //4.回显数据
        $("#money_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#money_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#money_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/money/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#money_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }

}
