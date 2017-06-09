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
    
    <title>电子商城——商品详细页显示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
   	<link href="css/Layout.css" rel="stylesheet" type="text/css" />
	<SCRIPT type=text/javascript src="js/scrolltop.js"></SCRIPT>
  </head>
  
  <body>
    <!--顶部注册开始-->
	<div class="itop">
		<div class="itop_body">
			<div class="itop_left fl">欢迎光临好东东买卖网！</div>
			<div class="itop_right fl">	
				 	
	    	 <s:if test="#session.CURRENT_USER==null">
		     	<span class="blue"><a href="login.jsp">[登录]</a></span>
		 	 	<span class="blue"><a href="javascript:void(0);" onclick="enableGO()">[注册]</a></span>		
		     </s:if>
		     <s:else>
		     	欢迎您：
		     	<span class="red">${sessionScope.CURRENT_USER.userName}</span>
		     	<span class="blue"><a href="logOut">[退出]</a></span>
		     	<span class="blue"><a href="toMyOrderInfo">[我的订单]</a></span>
		     </s:else> 	 		
			 	
				<a href="cart.jsp">
		 	 		<span><img src="images/d002.jpg"/></span>&nbsp;
					<span>购物车 </span>
					<!-- <span class="red">5</span> 件  -->	
				 </a>
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
		<!--商品详细信息开始-->
		<div class="show_a fl">
			<div class="img fl"><img src="product_big_images/${requestScope.detialProductInfo.bigpic}"/></div>
			<div class="canshu fl">
				<p></p>
				<p>编号：${requestScope.detialProductInfo.code }</p>
				<p>品名：${requestScope.detialProductInfo.name }</p>
				<p>品牌：${requestScope.detialProductInfo.brand }</p>
				<p><font color="red">价格：${requestScope.detialProductInfo.price }</font></p>
				<p>库存：${requestScope.detialProductInfo.num }</p>
				<p></p>
				<p></p>
				<p></p>
				<p></p>
				<div class="pic">
					<div class="img fl">
						<div class="tupian"><img src="product_images/s${requestScope.detialProductInfo.pic }"/></div>
						<div class="huohao">这是小图</div> 
					</div>
					<div class="img fl">
						<div class="tupian"><img src="product_images/s${requestScope.detialProductInfo.pic }"/></div>
						<div class="huohao"></div> 
					</div>
					<div class="img fl">
						<div class="tupian"><img src="product_images/s${requestScope.detialProductInfo.pic }"/></div>
						<div class="huohao"></div> 
					</div>
					<div class="img fl">
						<div class="tupian"><img src="product_images/s${requestScope.detialProductInfo.pic }"/></div>
						<div class="huohao"></div> 
					</div>
				</div>
			</div>
		</div>
		<!--商品详细信息结束-->
		<div class="show_b fl">
			<a href="addtoshopcart?productInfoId=${requestScope.detialProductInfo.id } "><img src="images/d010.jpg"/></a>	
		</div>
		<div class="show_c fl">产品详情</div>
		<div class="show_d fl ah">
			商品名称：<br/>
			${requestScope.detialProductInfo.name }<br/>
			商品简介：<br/>
			${requestScope.detialProductInfo.intro }
		</div>
		<div class="show_c fl">商品图片</div>
			<div class="show_e fl">
				<div class="yy fl"><img src="product_images/${requestScope.detialProductInfo.pic}"/></div>
				<div class="yy fl"><img src="product_images/${requestScope.detialProductInfo.pic}"/></div>
				<div class="yy fl"><img src="product_images/${requestScope.detialProductInfo.pic}"/></div>
			</div>
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
