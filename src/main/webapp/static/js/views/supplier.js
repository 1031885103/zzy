$(function(){
	//对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var supplierDatagrid,supplierDialog,supplierForm,supplierSearchBtn;
	supplierDatagrid = $("#supplier_datagrid");
	supplierDialog = $("#supplier_dialog");
	supplierForm = $("#supplier_form");
	supplierSearchBtn = $("#searchBtn");
	//数据表格
	supplierDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/supplier/list',
		fitColumns:true,
		toolbar:'#supplier_datagrid_tb',
		columns:[
			[
				{field:'sn',align:'center',width:10,title:'供应商编号'},
				{field:'name',align:'center',width:10,title:'供应商名称'},
				{field:'contacts',align:'center',width:10,title:'联系人'},
				{field:'phone',align:'center',width:10,title:'联系电话'},
				{field:'product',align:'center',width:10,title:'主供商品'},
				{field:'cooperation',align:'center',width:10,title:'合作时长(天)'},
				{field:'advantage',align:'center',width:10,title:'优势'},
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
	supplierDialog.dialog({
		width:650,
		height:300,
		buttons:'#supplier_dialog_bt',
		closed:true
	});
    supplierSearchBtn.textbox({
		width:250,
		label:"供应商名称:",
        labelWidth:80,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            supplierDatagrid.datagrid("load",{
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
			supplierForm.form("clear");
			//2.设置对话框的标题
			supplierDialog.dialog("setTitle","新增");
			//3.打开对话框
			supplierDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/supplier/update";
				}else{
					url = "/supplier/save";
				}
				supplierForm.form("submit",{
					url:url,
					onSubmit:function(param){
                        var beginTime = $("#targetDate").datebox("getValue");
                        var bt = new Date(beginTime).getTime();
                        var endTime = Date.now();
                        var dateTime =  Math.floor(Math.abs(endTime - bt) / (1000*60*60*24));
                        param.cooperation=dateTime;
                        return true;
					},
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								supplierDialog.dialog("close");
								supplierDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			supplierDialog.dialog("close");
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
    $("#supplier_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#supplier_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#supplier_form").form("clear");
        //2.设置对话框的标题
        $("#supplier_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#supplier_dialog").dialog("open");
        //4.回显数据
        $("#supplier_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#supplier_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#supplier_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/supplier/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#supplier_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }

}


// function dateFormatter(index,value,row){
// 	var beginTime = $("#targetDate").datebox("getValue");
// 	var bt = new Date(beginTime).getTime();
// 	var endTime = Date.now();
// 	var dateTime =  Math.floor(Math.abs(endTime - bt ) / (1000*60*60*24));
//
// 	return dateTime;
//
// }
