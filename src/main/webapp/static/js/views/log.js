$(function(){
	//对页面中的元素进行抽取.
	//方法太凌乱,希望统一管理
	//按钮在JS统一进行监听
	var logDatagrid,logSearchBtn;
	logDatagrid = $("#log_datagrid");
	logSearchBtn = $("#searchBtn");
	//数据表格
	logDatagrid.datagrid({
		fit:true,
		rownumbers:true,
        rownumberHeight:25,
		singleSelect:true,
		pagination:true,
		url:'/log/list',
		fitColumns:true,
		toolbar:'#log_datagrid_tb',
		columns:[
			[
				{field:'opUser',align:'center',width:10,title:'登录账号',fmatter:opUserFmatter},
            	{field:'opIp',align:'center',width:10,title:'登录IP地址'},
                {field:'opMac',align:'center',width:10,title:'登录MAC值'},
                {field:'opDate',align:'center',width:10,title:'登录时间',fmatter:opDateFmatter},
                {field:'display',align:'center',width:10,formatter:stateFormatter,title:'是否启用'}
			]
		]
	});

    logSearchBtn.textbox({
        buttonText:'搜索',
        buttonIcon:'icon-search',
        onClickButton:function(){
            var keyword = $("#logUser").val();
            var beginTime = $("#beginTime").datebox("getValue");
            var endTime = $("#endTime").datebox("getValue");
            logDatagrid.datagrid("load",{
                keyword:keyword,
                beginTime:beginTime,
                endTime:endTime
            });
        }
     });
});

function stateFormatter(value){
    if(value==0){
        return "是";
    }else if(value==1){
        return "否";
    }
}
function opUserFmatter(vlaue){
    if(value){
        return value.username;
    }else{
        return value;
    }
}
function opDateFmatter(value){
    var date = new Date(value);
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var h= date.getHours();
    var ms = date .getMilliseconds();
    return y+"-"+m+"-"+d+" "+h+":"+ms;
}