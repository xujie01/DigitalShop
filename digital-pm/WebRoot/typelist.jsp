<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'typelist.jsp' starting page</title>
    
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
    <table id="typeDg" class="easyui-datagrid"></table>
	<div id="typeTb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addType();">添加</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editType();">修改</a> 
	</div>
	
	<div id="typeDlg" class="easyui-dialog" title="New Type" closed="true"
		style="width:500px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="typeForm" method="POST" action="">
				<table cellpadding="5">					
					<tr>
						<td>产品名称:</td>
						<td><input class="easyui-textbox" type="text" id="type.name"
							name="type.name" data-options="required:true"></input></td>
					</tr>					
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveType();">保存</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm();">清空</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#typeDg').datagrid({
				singleSelect : false, //设置datagrid为单选
				url : 'typeList', //为datagrid设置数据源
				pagination : true, //启用分页
				pageSize : 4, //设置初始每页记录数（页大小）
				pageList : [ 4, 8, 12 ], //设置可供选择的页大小
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#typeTb', //为datagrid添加工具栏
				columns : [ [ { //编辑datagrid的列
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'name',
					title : '商品类型',
					width : 200
				}] ]
			});
		});

		var urls;
		var data;

		
		function addType() {
			$('#typeDlg').dialog('open').dialog('setTitle', '新增商品类型');
			$('#typeDlg').form('clear');
			urls = 'addType';
		}

		function saveType() {
			$("#typeForm").form("submit", {
				url : urls, //使用参数				
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						$("#typeDg").datagrid("reload");
					}
					$("#typeDlg").dialog("close");
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});
		}
		function clearForm() {
			$('#typeForm').form('clear');
		}

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

		function editType() {
			var row = $("#typeDg").datagrid("getSelected");
			console.log(row);
			if (row) {
				$("#typeDlg").dialog("open").dialog('setTitle', '修改产品信息');
				$("#typeForm").form("load", {
					"type.name" : row.name
				});
				urls = "updateType?type.id=" + row.id;
			}
		}		
		
	</script>
  </body>
</html>
