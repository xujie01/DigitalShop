<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
    <!-- 定义table, 用于创建easy ui的datagrid控件 -->
	<table id="dg" class="easyui-datagrid"></table>
	
	<!-- 创建datagrid控件的工具栏 -->
	<div id="tb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addProduct();">添加</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editProduct();">修改</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" onclick="removeProduct();" plain="true">删除</a>
	</div>
	
	<!-- 创建查询工具栏 -->
	<div id="searchtb" style="padding:2px 5px;">
		<form id="searchForm">
			<div style="padding:3px">
				商品编号&nbsp;&nbsp;<input class="easyui-textbox" name="pi.code"
					id="pi.code" style="width:110px" />
			</div>
			<div style="padding:3px">
				商品名称&nbsp;&nbsp;<input class="easyui-textbox" name="pi.name"
					id="pi.name" style="width:110px" /> 商品类型&nbsp;&nbsp;<input
					style="width:110px;" id="pi.type.id" class="easyui-combobox"
					name="pi.type.id"
					data-options="valueField:'id',textField:'name',url:'getAllType'">
				商品品牌&nbsp;&nbsp;<input class="easyui-textbox" name="pi.brand"
					id="pi.brand" style="width:110px" /> 价格: <input
					class="easyui-textbox" name="pi.priceFrom" id="pi.priceFrom"
					style="width:80px;" /> ~ <input class="easyui-textbox"
					name="pi.priceTo" id="pi.priceTo" style="width:80px;" /> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="searchProduct();">查找</a>
			</div>
		</form>
	</div>
	
	<!-- 添加产品信息对话框 -->
	<div id="dlg" class="easyui-dialog" title="New Topic" closed="true"
		style="width:500px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="ff" method="POST" action="" enctype="multipart/form-data">
				<table cellpadding="5">
					<tr>
						<td>产品状态:</td>
						<td><select id="pi.status" class="easyui-combobox" name="pi.status"	style="width:150px;">
								<option value="1">在售</option>
								<option value="0">下架</option>
						</select></td>
					</tr>
					<tr>
						<td>产品类型:</td>
						<td><input style="width:150px;" id="pi.type.id"
							class="easyui-combobox" name="pi.type.id"
							data-options="valueField:'id',textField:'name',url:'getAllType'"></input>
						</td>
					</tr>
					<tr>
						<td>产品名称:</td>
						<td><input class="easyui-textbox" type="text" id="pi.name"
							name="pi.name" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>产品编码:</td>
						<td><input class="easyui-textbox" type="text" id="pi.code"
							name="pi.code" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>产品品牌:</td>
						<td><input class="easyui-textbox" type="text" id="pi.brand"
							name="pi.brand" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>产品数量:</td>
						<td><input class="easyui-textbox" type="text" id="pi.num"
							name="pi.num" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>产品价格:</td>
						<td><input class="easyui-textbox" type="text" id="pi.price"
							name="pi.price" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>产品描述:</td>
						<td><input class="easyui-textbox" name="pi.intro"
							id="pi.intro" data-options="multiline:true" style="height:60px"></input></td>
					</tr>
					<tr>
						<td>产品小图:</td>
						<td><input class="easyui-filebox" id="pic" name="pic"
							style="width:200px" value="选择图片"></input></td>
					</tr>
					<tr>
						<td>产品大图:</td>
						<td><input class="easyui-filebox" id="bigPic" name="bigPic"
							style="width:200px" value="选择图片"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveProduct();">保存</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm();">清空</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#dg').datagrid({
				singleSelect : false, //设置datagrid为单选
				url : 'productList', //为datagrid设置数据源
				pagination : true, //启用分页
				pageSize : 10, //设置初始每页记录数（页大小）
				pageList : [ 10, 15, 20 ], //设置可供选择的页大小
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#tb', //为datagrid添加工具栏
				header : '#searchtb', //为datagrid标头添加搜索栏
				columns : [ [ { //编辑datagrid的列
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'name',
					title : '商品名称',
					width : 200
				}, {
					field : 'type',
					title : '商品类型',
					formatter : function(value, row, index) {
						if (row.type) {
							return row.type.name;
						} else {
							return value;
						}
					},
					width : 60
				}, {
					field : 'status',
					title : '商品状态',
					formatter : function(value, row, index) {
						if (row.status==1) {
							return "在售";
						} else {
							return "下架";
						}
					},
					width : 60
				}, {
					field : 'code',
					title : '商品编码',
					width : 100
				}, {
					field : 'brand',
					title : '品牌',
					width : 120
				}, {
					field : 'price',
					title : '价格',
					width : 50
				}, {
					field : 'num',
					title : '库存',
					width : 50
				}, {
					field : 'intro',
					title : '商品描述',
					width : 450
				} ] ]
			});
		});

		var urls;
		var data;

        // 删除商品
		function removeProduct() {
			var rows = $("#dg").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认要删除么?', function(r) {
					if (r) {
						var ids = "";
						for (var i = 0; i < rows.length; i++) {
							ids += rows[i].id + ",";
						}						
						$.post('updateStatus', {
							id : ids
						}, function(result) {
							if (result.success == 'true') {
								$("#dg").datagrid('reload'); // reload the user data
								$.messager.show({
									title : '提示信息',
									msg : result.message
								});
							} else {
								$.messager.show({
									title : '提示信息',
									msg : result.message
								});
							}
						}, 'json');
					}
				});
			} else {
				$.messager.alert('提示', '请选择要删除的行', 'info');
			}
		}

		// 打开新增商品对话框
		function addProduct() {
			$('#dlg').dialog('open').dialog('setTitle', '新增商品');
			$('#dlg').form('clear');
			urls = 'addProduct';
		}
		
		// 保存产品信息
		function saveProduct() {
			//data = convertArray($("#ff").serializeArray());
			//console.log(data);
			$("#ff").form("submit", {
				url : urls, //使用参数				
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						$("#dg").datagrid("reload");
						$("#dlg").dialog("close");
					}					
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});
		}
		
		function clearForm() {
			$('#ff').form('clear');
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

		// 修改产品
		function editProduct() {
			var row = $("#dg").datagrid("getSelected");
			if (row) {
				$("#dlg").dialog("open").dialog('setTitle', '修改产品信息');
				$("#ff").form("load", {
					"pi.type.id" : row.type.id,
					"pi.type.name" : row.type.name,
					"pi.name" : row.name,
					"pi.code" : row.code,
					"pi.brand" : row.brand,
					"pi.num" : row.num,
					"pi.price" : row.price,
					"pi.intro" : row.intro,
					"pi.status" : row.status,
				});
				urls = "updateProduct?pi.id=" + row.id;
			}
		}

		// 查询产品
		function searchProduct() {
			$("#dg").datagrid('load',
					convertArray($("#searchForm").serializeArray()));
		}
	</script>
</body>
</html>
