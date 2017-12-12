$(function(){
	//对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var commodityDatagrid,commodityEditBtnAndQuitBtn,commodityDialog,commodityForm,commoditySearchBtn;
	commodityDatagrid = $("#commodity_datagrid");
	commodityDialog = $("#commodity_dialog");
	commodityForm = $("#commodity_form");
	commoditySearchBtn = $("#searchBtn");
	//数据表格
	commodityDatagrid.datagrid({
		fit:true,
		rownumbers:true,
		rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/commodity/list',
		fitColumns:true,
		toolbar:'#commodity_datagrid_tb',
		columns:[
			[
				{field:'sn',align:'center',width:10,title:'商品69码'},
            	{field:'name',align:'center',width:10,title:'商品名称'},
                {field:'category',align:'center',width:10,title:'类别',formatter:categoryFormatter},
                {field:'brand',align:'center',width:10,title:'品牌',formatter:brandFormatter},
                {field:'origin',align:'center',width:10,title:'产地',formatter:originFormatter},
                {field:'ingredient',align:'center',width:10,title:'成分'},
                {field:'allergen',align:'center',width:10,formatter:allergenFormatter,title:'过敏物'},
                {field:'salePrice',align:'center',width:10,title:'售价'},
                {field:'image',align:'center',width:10,formatter:imageFormatter,title:'图片'},
                {field:'state',align:'center',width:10,formatter:stateFormatter,title:'状态'},
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
	commodityDialog.dialog({
		width:650,
		height:380,
		buttons:'#commodity_dialog_bt',
		closed:true
	});
    $("#searchBtn").textbox({
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
            var keyword1 = $("#proname").val();
            var keyword2 = $("#proele").val();
            var category = $("#categorySelect").combobox('getText');
            console.log(category);
            var brand = $("#brandSelect").combobox('getText');
            commodityDatagrid.datagrid("load",{
                keyword1:keyword1,
				keyword2:keyword2,
                categoryName:category,
				brandName:brand
            });


        }
     })

	//对按钮进行统一事件监听
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	
	//方法统一管理起来]
	var cmdObj = {
			add:function(){
				//1.清空表单数据
				commodityForm.form("clear");
				//2.设置对话框的标题
				commodityDialog.dialog("setTitle","新增");
				//3.打开对话框
				commodityDialog.dialog("open");
			},
			reload:function(){
				//刷新数据表格
				commodityDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/commodity/update";
				}else{
					url = "/commodity/save";
				}
				commodityForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							//提示消息,当点确定的时候,关闭对话框,刷新数据表格
							$.messager.alert("温馨提示",data.msg,"info",function(){
								commodityDialog.dialog("close");
								commodityDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function(){
                commodityDialog.dialog("close");
			}
	}
});

function editUser(index){
    $("#commodity_datagrid").datagrid('selectRow',index);// 关键在这里
    var rowData =  $("#commodity_datagrid").datagrid('getSelected');
    if(rowData){
        //1.清空表单数据
        $("#commodity_form").form("clear");
        //2.设置对话框的标题
        $("#commodity_dialog").dialog("setTitle","修改");
        //3.打开对话框
        $("#commodity_dialog").dialog("open");

        if(rowData.category){
            rowData["category.id"] = rowData.category.id;
        }
        if(rowData.brand){
            rowData["brand.id"] = rowData.brand.id;
        }
        if(rowData.origin){
            rowData["origin.id"] = rowData.origin.id;
        }
        //4.回显数据
        $("#commodity_form").form("load",rowData);//基于同名匹配规则
    }

}
function delUser(index) {
    $("#commodity_datagrid").datagrid('selectRow', index);// 关键在这里
    var rowData = $("#commodity_datagrid").datagrid('getSelected');
    if (rowData) {
        $.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function (yes) {
            if (yes) {
                $.get("/commodity/delete?id=" + rowData.id, function (data) {
                    if (data.success) {
                        $("#commodity_datagrid").datagrid("reload");
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }, "json")
            }
        });
    }
}

function categoryFormatter(value,record,index){
	if(value){
		return value.category;
	}else{
		return "宠物用品";
	}
}
function brandFormatter(value,record,index){
	if(value){
		return value.name;
	}else{
		return value;
	}
}
function originFormatter(value,record,index){
	if(value){
		return value.address;
	}else{
		return value;
	}
}
function allergenFormatter(value,record,index){
	if(value==0){
		return "无";
	}
}
function imageFormatter(value,record,index){
	if(value == null){
		return "暂无";
	}else{
		return value;
	}
}
function stateFormatter(value,record,index){
	if(value==0){
		return "正常";
	}
}

