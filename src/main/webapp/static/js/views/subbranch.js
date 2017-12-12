$(function(){
	//对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var subbranchDatagrid,subbranchDialog,subbranchForm,subbranchSearchBtn;
	subbranchDatagrid = $("#subbranch_datagrid");
	subbranchDialog = $("#subbranch_dialog");
	subbranchForm = $("#subbranch_form");
	subbranchSearchBtn = $("#searchBtn");
	//数据表格
	subbranchDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/subbranch/list',
		fitColumns:true,
		toolbar:'#subbranch_datagrid_tb',
		columns:[
			[
				{field:'name',align:'center',width:10,title:'分店名称'},
                {field:'location',align:'center',width:10,title:'分店地址'},
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
	subbranchDialog.dialog({
		width:250,
		height:250,
		buttons:'#subbranch_dialog_bt',
		closed:true
	});
    subbranchSearchBtn.textbox({
		width:300,
		label:"分店名称/地址:",
        labelWidth:100,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            subbranchDatagrid.datagrid("load",{
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
			subbranchForm.form("clear");
			//2.设置对话框的标题
			subbranchDialog.dialog("setTitle","新增");
			//3.打开对话框
			subbranchDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/subbranch/update";
				}else{
					url = "/subbranch/save";
				}
				subbranchForm.form("submit",{
					url:url,
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								subbranchDialog.dialog("close");
								subbranchDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			subbranchDialog.dialog("close");
		}


	}

});

function editUser(index){
    $("#subbranch_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#subbranch_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#subbranch_form").form("clear");
        //2.设置对话框的标题
        $("#subbranch_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#subbranch_dialog").dialog("open");
        //4.回显数据
        $("#subbranch_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#subbranch_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#subbranch_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/subbranch/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#subbranch_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }
}
