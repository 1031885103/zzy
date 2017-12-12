$(function(){
    //对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var petBreedDatagrid,petBreedDialog,petBreedForm,petBreedSearchBtn;
	petBreedDatagrid = $("#petBreed_datagrid");
	petBreedDialog = $("#petBreed_dialog");
	petBreedForm = $("#petBreed_form");
	petBreedSearchBtn = $("#searchBtn");
	//数据表格
	petBreedDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/petBreed/list',
		fitColumns:true,
		toolbar:'#petBreed_datagrid_tb',
		columns:[
			[
				{field:'category',align:'center',width:10,title:'宠物类别'},
				{field:'type',align:'center',width:10,title:'宠物名称'},
                {field:'display',align:'center',width:10,formatter:stateFormatter,title:'是否启用'},
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
	petBreedDialog.dialog({
		width:250,
		height:250,
		buttons:'#petBreed_dialog_bt',
		closed:true
	});
    petBreedSearchBtn.textbox({
		width:230,
		label:"品种名称:",
        labelWidth:60,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            petBreedDatagrid.datagrid("load",{
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
			petBreedForm.form("clear");
			//2.设置对话框的标题
			petBreedDialog.dialog("setTitle","新增");
			//3.打开对话框
			petBreedDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/petBreed/update";
				}else{
					url = "/petBreed/save";
				}
				petBreedForm.form("submit",{
					url:url,
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								petBreedDialog.dialog("close");
								petBreedDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			petBreedDialog.dialog("close");
		}


	}

});
function stateFormatter(value){
	if(value==0){
		return "是";
	}else if(value==1){
		return "否";
	}
}

function editUser(index){
    $("#petBreed_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#petBreed_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#petBreed_form").form("clear");
        //2.设置对话框的标题
        $("#petBreed_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#petBreed_dialog").dialog("open");
        //4.回显数据
        $("#petBreed_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#petBreed_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#petBreed_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/petBreed/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#petBreed_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }

}
