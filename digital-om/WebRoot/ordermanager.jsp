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
<title>订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="Easyui/themes/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="Easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="Easyui/demo.css" rel="stylesheet" type="text/css" />
<script src="Easyui/jquery.min.js" type="text/javascript"></script>
<script src="Easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="Easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>

</head>
<!-- 使用easyui的layout控件定义页面布局 -->
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:60px;background:#B3DFDA;padding:10px">电子商城-订单管理模块</div>
	<div data-options="region:'west',split:true,title:'功能菜单'"
		style="width:200px;padding:10px;">
		<!-- 定义ul,用于创建Easy UI的Tree  -->
		<ul id="tt"></ul>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;background:#A9FACD;padding:10px;text-align:center;">技术支持：www.magicgis.com</div>
	<div data-options="region:'center',title:'主界面'">
		<div id="tabs" data-options="fit:true" class="easyui-tabs"
			style="width:500px;height:250px"></div>
	</div>
	<script type="text/javascript">
		$('#tt').tree({
			url : 'tree_data2.json'
		});
		$('#tt').tree({
			onClick : function(node) {
				if ("查询订单" == node.text) {
					if ($('#tabs').tabs('exists', '查询订单')) {
						$('#tabs').tabs('select','查询订单');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'searchorder.jsp',
							closable : true
						});
					}

				} else if ("创建订单" == node.text) {
					if ($('#tabs').tabs('exists', '创建订单')) {
						$('#tabs').tabs('select','创建订单');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'createorder.jsp',
							closable : true
						});
					}
				}
			}
		});
	</script>
</body>
</html>
