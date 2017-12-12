$(function(){
    //对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var vacateDatagrid,vacateDialog,vacateForm,vacateSearchBtn;
	vacateDatagrid = $("#vacate_datagrid");
	vacateDialog = $("#vacate_dialog");
	vacateForm = $("#vacate_form");
	vacateSearchBtn = $("#searchBtn");
	//数据表格
	vacateDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/vacate/list',
		fitColumns:true,
		toolbar:'#vacate_datagrid_tb',
		columns:[
			[
				{field:'sn',align:'center',width:10,title:'工号'},
				{field:'emp',align:'center',width:10,title:'姓名',formatter:userFormatter},
				{field:'type',align:'center',width:10,title:'请假类别',formatter:typeFormatter},
				{field:'reason',align:'center',width:10,title:'请假事由'},
				{field:'begin',align:'center',width:10,title:'请假开始时间',formatter:beginFormatter},
				{field:'end',align:'center',width:10,title:'请假结束时间',formatter:endFormatter},
				{field:'duration',align:'center',width:10,title:'请假时长'},
				{field:'remark',align:'center',width:10,title:'领导意见'},
				{field:'normal',align:'center',width:10,title:'是否正常请假',formatter:normalFormatter},
				{field:'state',align:'center',width:10,title:'状态',formatter:stateFormatter},
				{field:'cancel',align:'center',width:10,title:'取消原因',formatter:cancelFormatter},
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
	vacateDialog.dialog({
		width:650,
		height:550,
		buttons:'#vacate_dialog_bt',
		closed:true
	});
    vacateSearchBtn.textbox({
		width:230,
		label:"品牌名称:",
        labelWidth:60,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            vacateDatagrid.datagrid("load",{
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
			vacateForm.form("clear");
			//2.设置对话框的标题
			vacateDialog.dialog("setTitle","新增");
			//3.打开对话框
			vacateDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/vacate/update";
				}else{
					url = "/vacate/save";
				}
				vacateForm.form("submit",{
					url:url,
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								vacateDialog.dialog("close");
								vacateDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			vacateDialog.dialog("close");
		}


	}

});
function normalFormatter(value){
	if(value==0){
		return "是";
	}else if(value==1){
		return "否";
	}
}
function stateFormatter(value){
	if(value==0){
		return "正常";
	}else if(value==1){
		return "异常";
	}
}

function cancelFormatter(value){
	if(value == null){
		return "无";
	}else{
		return value;
	}
}

function userFormatter(value){
	if(value){
		return value.username;
	}else{
		return value;
	}
}

function beginFormatter(value){
	var date  = new Date(value);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
        + (d < 10 ? ('0' + d) : d);

}
function endFormatter(value){
		var date = new Date(value);
		var y=date.getFullYear();
		var m=date.getMonth()+1;
		var d=date.getDate();
		return y+'-'+m+'-'+d;
}

function typeFormatter(value){
	if(value ==1){
		return "事假";
	}
	if(value ==2){
		return "病假";
	}
	if(value ==3){
		return "婚假";
	}
	if(value ==4){
        return "丧假";
    }
	if(value ==5){
		return "公假";
	}
	if(value ==6){
		return "产假";
	}
	if(value ==7){
		return "工伤";
	}
	if(value ==8){
		return "护理假";
	}
	if(value ==9){
		return "其他";
	}

}

function editUser(index){
    $("#vacate_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#vacate_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#vacate_form").form("clear");
        //2.设置对话框的标题
        $("#vacate_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#vacate_dialog").dialog("open");
        //4.回显数据
        $("#vacate_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#vacate_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#vacate_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/vacate/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#vacate_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }

}
