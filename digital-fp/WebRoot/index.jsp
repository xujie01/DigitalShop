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
    
    <title>电子商城--首页</title>
    
    <sx:head />
    
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
		<!-- <div id="header">
				<sx:div id="tsdiv" updateFreq="8000" href="loginAction">				
				</sx:div>
			</div>
			<font color="red">${message }</font>
		 -->
	 
		<%--
			if(session!=null){
		 %>				
				<span class="red">${sessionScope.CURRENT_USER }</span>&nbsp;
				<span class="blue"><a href="#">[退出]</a></span>
		<%
			}else{
		 %>	
				<span class="blue"><a href="login.jsp">[登录]</a></span>
				<span class="red"><a href="reg.jsp">[注册]</a></span>				
		<%
			}
		--%>
		     <s:if test="#session.CURRENT_USER==null">
		     	<span class="blue"><a href="login.jsp">[登录]</a></span>
		 	 	<span class="blue"><a href="javascript:void(0);" onclick="enableGO()">[注册]</a></span>		
		     </s:if>
		     <s:else>
		     	欢迎您：
		     	<span class="red">${sessionScope.CURRENT_USER.userName}</span>
		     	<span class="blue"><a href="logOut">[退出]</a></span>
		     	<span class="blue"><a href="javascript:void(0);" onclick="enableGO()">[我的订单]</a></span>
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
<!--条件筛选开始-->
	<div class="sx mt10">
		<div class="sx_a"><span>所有分类 > 类型</span></div>
		  <s:iterator id="typeItem" value="#request.typeList">
			<div class="sx_b">		
				<p class="tit fl">${typeItem.name }</p>
				<p class="con fl">
					<s:iterator id="pi" value="#typeItem.pis">
						&nbsp;&nbsp;${pi.brand }&nbsp;&nbsp;
					</s:iterator>
				</p>
				<p class="more fl"><img src="images/d006.jpg"/></p>
			</div>
		  </s:iterator>
	</div>
	<!-- div class="clearall"></div>   --> 
	
<!--条件筛选结束-->
<!--主体开始-->
	<div class="main mt10">
	  
	  <div class="mleft fl ah">
		<div class="mleft_tit">
			<p class="fenlei fl">综合</p>
			<p class="fenlei fl">价格</p>
			<p class="fenlei fl">人气</p>
			<p class="fenlei fl">销量</p>
		</div>
		<!-- 产品循环开始 -->
	  <s:iterator id="piItem" value="#request.piList">
		<div class="mpro fl">
			<div class="mpro_tp" onmouseover="javascript:this.style.background='#fbc837'" onmouseout="javascript:this.style.background=''">
				<a href='show?pi.id=${piItem.id }' target="_blank">
					<img src="product_images/${piItem.pic }"/>
				</a>
			</div>
			<div class="mpro_con">
			  <table width="242" border="0" cellpadding="0" cellspacing="0">
			    <tr>
			      <td width="180" height="25" align="left" valign="middle" class="jiacu">${piItem.code }</td>
			      <td width="62" rowspan="3" align="right" valign="middle">
			      	 <a href="addtoshopcart?productInfoId=${piItem.id }"><img src="images/006.jpg"/></a>
			      	<!-- addtoshopcart?后面加上CartAction的字段属性 -->
			      </td>
			    </tr>
			   	<tr align="left" valign="middle">
			      <td width="180" height="25"><font color=blue>${piItem.name }</font></td>
			    </tr>
			    <tr align="left" valign="middle">
			      <td width="180" height="25"><font color=red>¥${piItem.price }</font></td>
			    </tr>
			  </table>
			</div>
		</div>
	  </s:iterator>
		<!-- 产品循环结束 -->
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
		<!-- 销量排行榜开始，该部分功能未实现 -->
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
	<div class="end">
	  	<div class="cont" ><div class="cont_a fl" ><a href="http://47.92.7.213:8080/digital-pm/admin_login.jsp" target="#">产品管理</a>|
	  		<a href="http://47.92.7.213:8080/digital-om/admin_login.jsp" target="#">订单管理</a>|
	  		<a href="http://47.92.7.213:8080/digital-um/admin_login.jsp" target="#">用户管理</a>|
			<a href="https://github.com/xujie01/DigitalShop" target="#">源代码</a><br />
			技术支持：www.magicgis.com
			</td></div>
		<div class="cont_b fl" ><img src="images/008.jpg"/></div>
	</div>
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
