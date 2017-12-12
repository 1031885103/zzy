$(function(){
    //对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var productBrandDatagrid,productBrandDialog,productBrandForm,productBrandSearchBtn;
	productBrandDatagrid = $("#productBrand_datagrid");
	productBrandDialog = $("#productBrand_dialog");
	productBrandForm = $("#productBrand_form");
	productBrandSearchBtn = $("#searchBtn");
	//数据表格
	productBrandDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/productBrand/list',
		fitColumns:true,
		toolbar:'#productBrand_datagrid_tb',
		columns:[
			[
				{field:'address',align:'center',width:10,title:'品牌产地名称'},
				{field:'name',align:'center',width:10,title:'商品品牌名称'},
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
	productBrandDialog.dialog({
		width:250,
		height:250,
		buttons:'#productBrand_dialog_bt',
		closed:true
	});
    productBrandSearchBtn.textbox({
		width:230,
		label:"品牌名称:",
        labelWidth:60,
        prompt:"请输入搜索关键字",
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
			var keyword = $(this).val();
            productBrandDatagrid.datagrid("load",{
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
			productBrandForm.form("clear");
			//2.设置对话框的标题
			productBrandDialog.dialog("setTitle","新增");
			//3.打开对话框
			productBrandDialog.dialog("open");
		},
		save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/productBrand/update";
				}else{
					url = "/productBrand/save";
				}
				productBrandForm.form("submit",{
					url:url,
					success:function(data){
						console.log(data)
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								productBrandDialog.dialog("close");
								productBrandDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
		cancel:function(){
			productBrandDialog.dialog("close");
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
    $("#productBrand_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#productBrand_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#productBrand_form").form("clear");
        //2.设置对话框的标题
        $("#productBrand_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#productBrand_dialog").dialog("open");
        //4.回显数据
        $("#productBrand_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#productBrand_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#productBrand_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/productBrand/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#productBrand_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }

}
