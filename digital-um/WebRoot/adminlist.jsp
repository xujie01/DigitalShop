<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
  if(session.getAttribute("admin")==null)
  	response.sendRedirect("/digital-um/admin_login.jsp");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'newslist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	 <!-- 创建一个table -->
	<table id="adminListDg" class="easyui-datagrid"></table>
	
	<!-- 工具栏 -->
	<div id="adminListTb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" onclick="addAdminInfo();" plain="true">添加管理员</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editPowers();">设置/修改管理员权限</a>
	</div>
	
	<!-- 新增或修改权限的对话框  -->
	<div id="powerDlg" class="easyui-dialog" title="修改/设置管理员权限"
		closed="true" style="width:500px;height: 350px">
		<div style="padding:10px 60px 20px 60px">
			<form id="adminForm" method="POST" action="">
				<table cellpadding="5">					
					<tr>
						<td>管理员权限:</td><td><ul id="powerTree"></ul>
							<input type="hidden" name="editAdminId" id="editAdminId" style="width:10px"  /> 
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="savePowers();">保存</a>
			</div>
		</div>
	</div>
	
	
	<!-- 新增管理员的对话框  -->
	<div id="addAdminDlg" class="easyui-dialog" title="添加管理员" closed="true"
		style="width:500px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="addAdminForm" method="POST" action="">
				<table cellpadding="5">
					<tr>
						<td>登录名</td>
						<td><input class="easyui-textbox" type="text" id="name"
							name="name" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input class="easyui-textbox" type="text" id="pwd"
							name="pwd" data-options="required:true"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveAdminInfo();">保存</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearAddAdminForm();">清空</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#adminListDg').datagrid({
				singleSelect : false,
				url : 'admininfo/adminlist',
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#adminListTb', //为datagrid添加工具栏
				columns : [ [ { //编辑datagrid的列
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'name',
					title : '登录名',
					width : 100
				}, {
					field : 'pwd',
					title : '密码',
					width : 80
				}, {
					field : 'role',
					title : '角色',
					width : 100,
					formatter : function(value, row, index) {
						if (row.role == 1) {
							return "超级管理员";
						} else {
							return "普通管理员";
						}
					}
				} ] ]
			});
		});

		var urls;
		var data;

		function convertArray(o) { //主要是推荐这个函数。它将jquery系列化后的值转为name:value的形式。 
			var v = {};
			for ( var i in o) {
				if (typeof (v[o[i].name]) == 'undefined')
					v[o[i].name] = o[i].value;
				else
					v[o[i].name] += "," + o[i].value;
			}
			return v;
		}

        // 设置或修改权限
		function editPowers() {
			var rows = $("#adminListDg").datagrid('getSelections');
			if (rows.length > 0) {
				var row = $("#adminListDg").datagrid("getSelected");
				// 打开“设置/修改权限”对话框
				$("#powerDlg").dialog("open").dialog('setTitle', '修改/设置管理员权限');
				// 通过id为“editAdminId”的隐藏文本域保存选中管理员记录的id号
				$('#editAdminId').val(row.id);
				// 使用id为“powerTree”的Tree控件显示系统功能，并绑定该管理员已分配的权限
				$('#powerTree').tree({
					checkbox : true,
					url : 'functions/getTree',					
					onLoadSuccess : function() {
						// 绑定权限  
						$.ajax({
							url : 'admininfo/getFidByAdminId?' ,
							// cache必须设置为false,意思为不缓存当前页，否则更改权限后绑定的权限还是上一次的操作结果
							cache : false,
							// dataType表示获取服务器发送的数据，"text"表示纯文本
							dataType: 'text',
							data: {adminid : row.id},						
							success : function(data) {		
							    if(data!=""){
							    	var array = data.split(',');
									for (var i = 0; i < array.length; i++) {
										var node = $('#powerTree').tree('find', array[i]);
										$('#powerTree').tree('check',node.target);
									}
							    }
							}
						})
					}
				});
			} else {
				$.messager.alert('提示', '请选择要修改的管理员', 'info');
			}
		}

		// 保存设置或修改的功能权限
		function savePowers() {
			var fids=getChecked();
			 $.ajax({  
	              url:'powers/savePowers?fids='+fids +'&editadminid='+$('#editAdminId').val(),  
	              cache:false,  
	              success:function(data){  
	                  eval('data='+data);  
	                  if(data.success=="true"){  
	                  	  $("#powerDlg").dialog("close");
	                      $.messager.alert('提示', '权限修改成功', 'info');
	                  }else{
		                  $.messager.alert('提示', '权限修改失败', 'info');
	                  }
	              }  
             })  
		}
		
		// 获取“设置/修改权限”对话框中Tree控件上选中的功能节点的id号
function getChecked(){  
	var node = $('#powerTree').tree('getChecked');  
	var cnodes='';  
	var pnodes='';  
	  
	var prevNode=''; //保存上一步所选父节点  
	for(var i=0;i<node.length;i++){  
		 
		if($('#powerTree').tree('isLeaf',node[i].target)){  
			cnodes+=node[i].id+',';    
			  
		   var pnode = $('#powerTree').tree('getParent',node[i].target); //获取当前节点的父节点  
		   if(prevNode!=pnode.id) //保证当前父节点与上一次父节点不同  
		   {  
				pnodes+=pnode.id+',';  
				prevNode = pnode.id; //保存当前节点  
		   }  
		}  
	}  
	cnodes = cnodes.substring(0,cnodes.length-1);  
	pnodes = pnodes.substring(0,pnodes.length-1);  
	return pnodes+","+cnodes;
}	
		
		// 打开新增管理员对话框
		function addAdminInfo() {
			$('#addAdminDlg').dialog('open').dialog('setTitle', '新增管理员');
			$('#addAdminDlg').form('clear');
			urls = 'admininfo/addAdminInfo';
		}
		
		// 保存新增的管理员信息
		function saveAdminInfo() {
			$("#addAdminForm").form("submit", {
				url : urls, //使用参数				
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						$("#adminListDg").datagrid("reload");
						$("#addAdminDlg").dialog("close");
					}					
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});
		}
		
		function clearAddAdminForm() {
			$('#addAdminForm').form('clear');
		}
		
	</script>
</body>
</html>
