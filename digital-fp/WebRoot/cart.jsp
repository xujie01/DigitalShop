<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电子商城——购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/Layout.css" rel="stylesheet" type="text/css" />
	<SCRIPT type=text/javascript src="js/scrolltop.js"></SCRIPT>
  </head>
  
  <body>
<!--顶部注册开始-->
	<div class="itop">
		<div class="itop_body">
			<div class="itop_left fl">欢迎光临好东东买卖网！</div>
			<div class="itop_right fl">			 	
			     <s:if test="(#session.CURRENT_USER==null)">
			     	<span class="blue"><a href="login.jsp">[登录]</a></span>
			 	 	<span class="blue"><a href="javascript:void(0);" onclick="enableGO()">[注册]</a></span>		
			     </s:if>
			     <s:else>
			     	欢迎您：
			     	<span class="red">${sessionScope.CURRENT_USER.userName}</span>
			     	<span class="blue"><a href="logOut">[退出]</a></span>
			     	<span class="blue"><a href="toMyOrderInfo">[我的订单]</a></span>
			     </s:else> 	 	
			 	
				<span><img src="images/d002.jpg"/></span>
				<span>购物车 </span>
			</div>
		</div>
	</div>
	<div class="clearall"></div>
<!--顶部注册结束-->
<!--头部搜索开始-->
	<div class="header">
		<div class="logo fl"><img src="images/d001.jpg"/></div>
		<div class="search fl">
			<div class="search_from">
				<div><input name="" type="text" class="s_input fl"/></div>
				<div class="s_botton fl" onclick="enableGO()"><input type="image" src="images/002.jpg"/></div>
			</div>
			<div class="s_hot hui">热门搜索：笔记本 台式机 一体机 平板电脑 手机 打印机 </div>
		</div>
	</div>
<!--头部搜索结束-->
<!--菜单开始-->
	<div class="menu">
		<div class="menu_left fl">全部商品分类</div>
		<div class="menu_center fl">
			<div class="dh_topd"><A href="list">网站首页</A></div>
			<div class="dh_topd"><A href="list">购物流程</A></div>
			<div class="dh_topd"><A href="list">联系我们</A></div>
		</div>
	</div>
	<div class="clearall"></div>
<!--菜单结束-->

<!--主体开始-->
	<div class="main mt10">
		<div class="mleft fl ah">
		<!--购物车开始-->
			<div class="car_a jiacu"><span><img src="images/d002.jpg"/></span>您的购物车中有以下商品</div>
			<div class="car_b fl mt10">
				<p class="bh fl">编号</p>
				<p class="spmc fl">商品名称</p>
				<p class="dj fl">单价</p>
				<p class="sl fl">数量</p>
				<p class="je fl">金额</p>
				<p class="del fl">删除</p>
			</div>
			
			<s:set var="sumPrice" value="0" />
			<s:iterator id="cartItem" value="#session.cart">
			
				<div class="car_c fl">
					<p class="bh fl"><s:property value="value.pi.id"/> </p>
					<p class="spmc fl"><s:property value="value.pi.name"/></p>
					<p class="dj fl">￥<s:property value="value.pi.price"/></p>
					<p class="sl fl">
						<input type="text" value="${value.quantity }" size="10"
						 onchange="window.location='updateSelectedQuantity?productInfoId=${value.pi.id}&quantity='+this.value;" />				
					</p>
					<p class="je fl">￥<s:property value="value.quantity*value.pi.price"/></p>
					<p class="del fl"><a href="deleteSelectedOrders?productInfoId=${value.pi.id }">
									<img src="images/d013.jpg"/></a>
					</p>
				</div>
				<s:set var="sumPrice" value="#sumPrice+value.quantity*value.pi.price" />
			</s:iterator>
	
			<div class="car_c fl" style="background-color:#ccccee;">
				<p class="bh fl">合计</p>
				<p class="spmc fl">-</p>
				<p class="dj fl">-</p>
				<p class="sl fl">-</p>
				<p class="je fl">￥<s:property value="#sumPrice" />
					<s:set var="sumPrice" value="#sumPrice" scope="session"></s:set>					
				</p>
				<p class="del fl">-</p>
			</div>
			<div class="car_d fl">
				<p class="cz fl"><a href="clearCart"><img src="images/d014.jpg"/></a></p>
				<p class="cz fl"><a href="list"><img src="images/d015.jpg"/></a></p>
				<p class="cz fl"><a href="addOrderInfo"><img src="images/d016.jpg"/></a></p>
			</div>
		<!-- 购物车结束-->
		</div>
		<div class="mright fl">
			<!-- 浏览排行榜开始 -->
			<div class="mright_b mt10">
				<p class="tit">浏览排行榜</p>
				<div class="con">
				  	<s:iterator id="browsePiItem" value="#session.browsePiList" status="status">
					  <s:if test="#status.index==1">
						<div class="conshow">
							<p class="img fl">
							<img height='50px' width='65px' src="product_images/${browsePiItem.pic }"/></p>
							<p class="content fl">${browsePiItem.name }</p>
						</div>
				      </s:if>
					  <s:if test="#status.index>1 && #status.index<13 ">
						<p class="paihang">${browsePiItem.name }</p>		
					  </s:if>		
	
					</s:iterator>			  
				</div>
			</div>
			<!-- 浏览排行榜结束 -->
			<!-- 销量排行榜开始 -->
			<div class="mright_a">
				<p class="tit">销量排行榜</p>
				<div class="con"> 
					<div class="conshow">
						<p class="img fl"><img src="images/d007.jpg"/></p>
						<p class="content fl">笔记本电脑/联想S200</p>
					</div>
					<p class="paihang">笔记本电脑/联想S200</p>
					<p class="paihang">笔记本电脑/联想S230</p>
					<p class="paihang">笔记本电脑/联想S400</p>
	
				</div>
			</div>
			<!-- 销量排行榜结束 -->
		</div>
	</div>

<!--主体结束-->


<!--尾部开始-->
	<div class="end">建议您使用1366*768分辨率，Chrome浏览器使用本系统<br />
		技术支持：www.magicgis.com</div>
	<!--尾部结束-->
	<DIV style="DISPLAY: none;POSITION: fixed; TEXT-ALIGN: center; LINE-HEIGHT: 30px; WIDTH: 30px; BOTTOM: 100px; HEIGHT: 33px; FONT-SIZE: 12px; CURSOR: pointer; RIGHT: 0px; _position: absolute; _right: auto" id=goTopBtn><IMG border=0 src="images/lanren_top.jpg"></DIV>
	<SCRIPT type=text/javascript>goTopEx();</SCRIPT>
	<script>
		function enableGO(){
            alert("抱歉，暂不开放此功能");
        }
	</script>
  </body>
</html>
