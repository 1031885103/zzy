$(function(){
    //对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var workDatagrid,workDialog,workForm,workSearchBtn;
	workDatagrid = $("#work_datagrid");
	workDialog = $("#work_dialog");
	workForm = $("#work_form");
	workSearchBtn = $("#searchBtn");
	//数据表格
	workDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/work/list',
		fitColumns:true,
		toolbar:'#work_datagrid_tb',
		columns:[
			[
				{field:'position',align:'center',width:10,title:'职位名称'},
				{field:'job',align:'center',width:10,title:'岗位津贴'},
				{field:'traffic',align:'center',width:10,title:'交通补贴'},
				{field:'tel',align:'center',width:10,title:'话费补贴'},
				{field:'meal',align:'center',width:10,title:'餐补'},
				{field:'every',align:'center',width:10,title:'全勤'},
				{field:'proportion',align:'center',width:10,title:'提成比例(%)'},
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
	workDialog.dialog({
		width:350,
		height:430,
		buttons:'#work_dialog_bt',
		closed:true
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
			workForm.form("clear");
			//2.设置对话框的标题
			workDialog.dialog("setTitle","新增");
			//3.打开对话框
			workDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/work/update";
				}else{
					url = "/work/save";
				}
				workForm.form("submit",{
					url:url,
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								workDialog.dialog("close");
								workDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			workDialog.dialog("close");
		}


	}

});

function editUser(index){
    $("#work_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#work_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#work_form").form("clear");
        //2.设置对话框的标题
        $("#work_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#work_dialog").dialog("open");
        //4.回显数据
        $("#work_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#work_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#work_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/work/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#work_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }

}
