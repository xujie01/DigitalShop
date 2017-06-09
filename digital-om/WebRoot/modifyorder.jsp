<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<title>My JSP 'createorder.jsp' starting page</title>

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
	<table id="editodbox"></table>

	<div id="editordertb" style="padding:2px 5px;">
		<div>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="edit_addOrderDetail();">添加订单明细</a><a href="#"
				class="easyui-linkbutton" iconCls="icon-save" plain="true"
				onclick="edit_saveorder();">保存订单</a><a href="#" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="edit_removeOrderDetail();">删除订单明细</a>
		</div>

		<div id="editdivOrderInfo">
			<div style="padding:3px">
				客户名称&nbsp;<input style="width:115px;" id="edit_uid" class="easyui-combobox"
					name="edit_uid" value="${requestScope.oi.ui.id }"
					data-options="valueField:'id',textField:'userName',url:'userinfo/getValidUser'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				订单金额&nbsp;<input type="text" name="edit_orderprice" id="edit_orderprice"
					class="easyui-textbox" readonly="readonly" style="width:115px" /> &nbsp;&nbsp;		
			</div>
			<div style="padding:3px">
				订单日期&nbsp;<input type="text" name="edit_ordertime" id="edit_ordertime" value="${requestScope.oi.ordertime }"
					class="easyui-datebox" style="width:115px" /> &nbsp;&nbsp;
			        <input type="hidden" name="oid" id="oid" value="${requestScope.oi.id }" style="width:10px"  /> &nbsp;&nbsp;
				订单状态&nbsp;<select id="edit_status" class="easyui-combobox" name="edit_status" style="width:115px;">		
				    <c:if test='${requestScope.oi.status == "未付款"}'>
				    	<option value="未付款" selected>未付款</option>
				    </c:if>	
				    <c:if test='${requestScope.oi.status != "未付款"}'>
				    	<option value="未付款">未付款</option>
				    </c:if>	
				    <c:if test='${requestScope.oi.status == "已付款"}'>
				    	<option value="已付款" selected>已付款</option>
				    </c:if>	
				    <c:if test='${requestScope.oi.status != "已付款"}'>
				    	<option value="已付款">已付款</option>
				    </c:if>								
					<c:if test='${requestScope.oi.status == "待发货"}'>
				    	<option value="待发货" selected>待发货</option>
				    </c:if>	
				    <c:if test='${requestScope.oi.status != "待发货"}'>
				    	<option value="待发货">待发货</option>
				    </c:if>		
				    
				    <c:if test='${requestScope.oi.status == "已发货"}'>
				    	<option value="已发货" selected>已发货</option>
				    </c:if>	
				    <c:if test='${requestScope.oi.status != "已发货"}'>
				    	<option value="已发货">已发货</option>
				    </c:if>	
				    
				    <c:if test='${requestScope.oi.status == "已完成"}'>
				    	<option value="已完成" selected>已完成</option>
				    </c:if>	
				    <c:if test='${requestScope.oi.status != "已完成"}'>
				    	<option value="已完成">已完成</option>
				    </c:if>		
				</select>				
			</div>

		</div>
	</div>
	<script type="text/javascript">
		var $editodbox = $('#editodbox');	
		$(function() {
			$editodbox.datagrid({
			    url: 'oi/getOrderDetails?oid=${requestScope.oi.id }',
	             onLoadSuccess: function (data) {
	                var rows = $editodbox.datagrid('getRows');		                 
	                for (var i = 0; i < rows.length; i++) {
	                    var index = $editodbox.datagrid('getRowIndex', rows[i]);	                    
	                    $editodbox.datagrid('beginEdit', index);
	                }     
	            },  
				rownumbers : true,
				singleSelect : false, 
				fit : true,
				toolbar : '#editordertb',
				header : '#editdivOrderInfo',
				columns : [ [ {
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'pid',
					title : '产品名称',					
					width : 300,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							url : 'pi/getOnSaleProduct',								
							onChange: function (newValue, oldValue) {
                                 var rows = $editodbox.datagrid('getRows');
                                 var orderprice=0; 
                                  for (var i = 0; i < rows.length; i++) { 
                                   
                                     var pidEd = $editodbox.datagrid('getEditor', {
                                         index: i,
                                         field: 'pid'
                                     });
                                     var priceEd = $editodbox.datagrid('getEditor', {
                                         index: i,
                                         field: 'price'
                                     });
                                     var totalpriceEd = $editodbox.datagrid('getEditor', {
                                         index: i,
                                         field: 'totalprice'
                                     });
                                     var numEd = $editodbox.datagrid('getEditor', {
                                         index: i,
                                         field: 'num'
                                     }); 
                                     
                                     if (pidEd != null ) {
                                     	var pid=$(pidEd.target).combobox('getValue'); 
                                     	$.ajax({
											  type: 'POST',
											  url: 'pi/getPriceById',
											  data: {pid : pid},
											  success:  function(result) {
													$(priceEd.target).numberbox('setValue',result);
													$(totalpriceEd.target).numberbox('setValue',result * $(numEd.target).numberbox('getValue'));
													orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
											  },
											  dataType: 'json',
											  async : false
										});
                                     } 
                                     $("#edit_orderprice").textbox("setValue",orderprice);
                                 } 
                                 
                             }
						}
					}
				}, {
					field : 'price',
					title : '单价',
					width : 80,
					editor: {
						type : "numberbox",		
						options: {
							editable : false
						}	
					  }
					}, {
					field : 'num',
					title : '数量',
					width : 50,
					editor : {
						type : 'numberbox',
						options :{
							onChange: function (newValue, oldValue) {
		                        var rows = $editodbox.datagrid('getRows');		
		                        var orderprice=0;                        
		                         for (var i = 0; i < rows.length; i++) { 
		                             var pidEd1 = $editodbox.datagrid('getEditor', {
                                         index: i,
                                         field: 'pid'
                                     });
		                             var priceEd1 = $editodbox.datagrid('getEditor', {
		                                index: i,
		                                field: 'price'
		                            }); 
		                            var totalpriceEd1 = $editodbox.datagrid('getEditor', {
		                                index: i,
		                                field: 'totalprice'
		                            });  
		                            
		                             var numEd1 = $editodbox.datagrid('getEditor', {
		                                index: i,
		                                field: 'num'
		                            }); 
		                            if (pidEd1 != null && numEd1 !=null){
		                            	$(totalpriceEd1.target).numberbox('setValue',$(priceEd1.target).numberbox('getValue') * $(numEd1.target).numberbox('getValue')); 	
		                            	orderprice=Number(orderprice)+Number($(totalpriceEd1.target).numberbox('getValue'));	                            
		                            }
		                        } 
		                        $("#edit_orderprice").textbox("setValue",orderprice);
							}
						}
                    }
				}, {
					field : 'totalprice',
					title : '小计',
					width : 100,
					editor: {
						type : "numberbox",		
						options: {
							editable : false
						}	
					}	
				}  ] ]
			});	
		});

		
		function edit_addOrderDetail() {
			$editodbox.datagrid('appendRow', {
				num : '1',
				price : '0',
				totalprice : '0'
			});
			var rows = $editodbox.datagrid('getRows');
			$editodbox.datagrid('beginEdit', rows.length - 1);
		}

		function edit_removeOrderDetail() {
			var rows = $editodbox.datagrid('getSelections');
			var edit_orderprice =  $("#edit_orderprice").textbox("getValue");
			alert(edit_orderprice);			
			if (rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					var index = $editodbox.datagrid('getRowIndex', rows[i]);
					var totalpriceEd2 = $editodbox.datagrid('getEditor', {
                        index: index,
                        field: 'totalprice'
                    }); 
                    alert($(totalpriceEd2.target).numberbox('getValue'));		
                    edit_orderprice = edit_orderprice - Number($(totalpriceEd2.target).numberbox('getValue'));
					alert(edit_orderprice);
					$editodbox.datagrid('deleteRow', index);
				}
				$("#edit_orderprice").textbox("setValue",edit_orderprice);
			} else {
				$.messager.alert('提示', '请选择要删除的行', 'info');
			}
		}
		
		// 保存订单修改
		function edit_saveorder() {	
		    // 获取订单客户
			var uid = $("#edit_uid").combobox("getValue");
			if(uid==0){
				$.messager.alert('提示', '请选择客户名称', 'info');
			} else {
				// 取消datagrid控件的编辑状态
				edit_endEdit();
				// 定义orderinfo存放订单主表数据
				var orderinfo = [];
				// 获取订单时间
				var ordertime = $("#edit_ordertime").datebox("getValue");
				// 获取订单状态
				var status = $("#edit_status").combobox("getValue");
				// 获取订单的id号
				var oid = $("#oid").val();
				// 获取订单总金额
				var orderprice = $("#edit_orderprice").textbox("getValue");
				orderinfo.push({
					ordertime : ordertime,
					uid : uid,
					status : status,
					id : oid,
					orderprice : orderprice
				});
				// 获取订单明细（即datagrid控件中的记录）
				// 定义effectRow,保存inserted和orderinfo
				var effectRow = new Object();
				effectRow["orderinfo"] = JSON.stringify(orderinfo);
				if ($editodbox.datagrid('getChanges').length) {
					// 获取datagrid控件中插入的记录行
					var inserted = $editodbox.datagrid('getChanges', "inserted");
					// 获取datagrid控件中删除的记录行
					var deleted = $editodbox.datagrid('getChanges', "deleted");
					// 获取datagrid控件中更新的记录行
					var updated = $editodbox.datagrid('getChanges', "updated");
					if (inserted.length) {
						effectRow["inserted"] = JSON.stringify(inserted);
					}		
					if (updated.length) {
						effectRow["updated"] = JSON.stringify(updated);
					}	
					if (deleted.length) {
						effectRow["deleted"] = JSON.stringify(deleted);
					}
				}
				// 提交请求
				$.post(
					"oi/commitModifyOrder",
					effectRow,
					function(data) {
						if (data == 'success') {
							$.messager.alert("提示", "修改成功！");
							$editodbox.datagrid('acceptChanges');
							if ($('#tabs').tabs('exists', '修改订单')) {
								$('#tabs').tabs('close', '修改订单');
							}
							$("#orderDg").datagrid('reload'); 
						} else {
							$.messager.alert("提示", "修改失败！");
						}
					});
			}	

		}

		function edit_endEdit() {
			var rows = $editodbox.datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				$editodbox.datagrid('endEdit', i);
			}
		}
		
		
	</script>

	

</body>
</html>
