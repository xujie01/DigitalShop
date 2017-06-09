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
    
    <title>电子商城-登录页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/Layout.css" rel="stylesheet" type="text/css" />
	<SCRIPT type=text/javascript src="js/scrolltop.js"></SCRIPT>

  </head>
  
  <body>
<!--顶部开始-->
	<div class="itop">
		<div class="itop_body">
			<div class="itop_left fl">欢迎光临好东东买卖网！</div>
			<div class="itop_right fl">
				<span class="blue"><a href="login.jsp">[登录]</a></span>
				<span class="red"><a href="javascript:void(0);" onclick="enableGO()">[注册]</a></span>
				<span><img src="images/d002.jpg"/></span>
				<span>购物车 <span class="red">0</span> 件</span>
			</div>
		</div>
	</div>
	<div class="clearall"></div>
<!--顶部结束-->
<!--头部搜索开始-->
	<div class="header">
		<div class="logo fl"><img src="images/d001.jpg"/></div>
		<div class="search fl">
			<div class="search_from">
				<div><input name="" type="text" class="s_input fl"/></div>
				<div class="s_botton fl" onclick="enableGO()"><input type="image" src="images/002.jpg"/></div>
			</div>
			<div class="s_hot hui">热门搜索：笔记本 台式机 一体机 平板电脑 手机 打印机</div>
		</div>
	</div>
<!--头部搜索结束-->
<!--菜单开始-->
	<div class="menu">
		<div class="menu_left fl">全部商品分类</div>
		<div class="menu_center fl">
			<div class="dh_topd"><A href="list.action">网站首页</A></div>
			<div class="dh_topd"><A href="list.action">购物流程</A></div>
			<div class="dh_topd"><A href="list.action">联系我们</A></div>
		</div>
	</div>
	<div class="clearall"></div>
<!--菜单结束-->

<!--主体开始-->
	<div class="main mt10">
		<div class="mleft fl ah">
		<!--登录开始-->
			<div class="login_a jiacu">登录部分</div>
			<div class="login_b fl">
				<p class="title fl">用户登录</p>
				<div class="login_d fl">
					<s:form action="doLogin">
						<table>
							<tr>
								<s:textfield name="ui.userName" value="my" label="用户名" />
							</tr>
							<tr>
								<s:textfield name="ui.password" value="123456" type="password" label="密码" />
							</tr>
							<tr>
								<s:submit value="登录" />
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<div class="login_c fl">
				<p class="tit fl">新用户注册</p>
				<div class="login_e fl">如果您不是会员请注册！！</br>
					<span class="jiacu">友情提示：</span></br>
					注册之后您可以：</br>
					1.保存修改您的个人资料</br>
					2.收藏您关注的商品</br>
					3.享受会员积分制度</br>
					4.订阅最新开发的商品资讯</br>
					<p><input onclick="enableGO()" type="image" src="images/d018.jpg"/></p>
				</div>
			</div>
		<!--登录结束-->
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
