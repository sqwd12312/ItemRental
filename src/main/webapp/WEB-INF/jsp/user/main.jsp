<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>租赁管理系统</title>
		<link rel="stylesheet" href="css2/layui.css">
		
	    <link rel="stylesheet" type="text/css" href="css/nav.css">
        <link rel="stylesheet" type="text/css" href="font/iconfont.css">
        


        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/nav.js"></script>
        <script type="text/javascript" src="js/jquery.cookie.js"></script>
	</head>

	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo" > <strong> <font  size="6" face="楷体" >租赁管理系统</font></strong></div>
				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;">
							<img src="images/1.gif" class="layui-nav-img">${sessionScope.user.username}
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="myDetails.action" target="right">我的基本信息</a>
							</dd>
							<dd>
								<a href="findhasuserlist.action" target="right">更新基本信息</a>
							</dd>
							<dd>
								<a href="gotoUpdatePwd.action" target="right">更新密码</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:if(confirm('确实要退出登录吗?'))location='login.action'">退出</a>
					</li>
				</ul>
			</div>

<div class="layui-side layui-bg-black">
  <div class="layui-side-scroll">
	<div class="nav">
	  <div class="sidebar-wrap" >	     
	    <div class="sidebar-content" >            
	     <ul>
		<li class="nav-item">
			<a href="javascript:;"><i class="iconfont nav-icon icon_4"></i><span>物品信息</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="item.action"  target="right" ><span>物品列表</span></a></li>
			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="iconfont nav-icon icon_5"></i><span>我的租赁</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="zulist/myzulist.action" target="right"><span>在租列表</span></a></li>
				<li><a href="checkout/getMyCheckout.action" target="right"><span>退租列表</span></a></li>
				<li><a href="getmyapply.action" target="right"><span>租赁申请</span></a></li>
				<li><a href="applyout/getmyapplyout.action" target="right"><span>退租申请</span></a></li>
			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="my-icon nav-icon icon_3" ></i><span>我的出租</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="itemListByZuke.action" target="right"><span>我的物品</span></a></li>
				<li><a href="toAddItemByZuke.action" target="right"><span>添加物品</span></a></li>
				<li><a href="getMyApply.action" target="right"><span>被租赁申请</span></a></li>
				<li><a href="hetong/findAllHetongByZuke.action" target="right"><span>出租列表</span></a></li>
				<li><a href="applyout/findAllApplyoutByZuke.action" target="right"><span>申请退租列表</span></a></li>
				<li><a href="checkout/getAllCheckoutByUser.action" target="right"><span>已退租列表</span></a></li>			
			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="iconfont nav-icon icon_6"></i><span>租金信息</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="paid/findmypaid.action" target="right"><span>已缴租金</span></a></li>
				<li><a href="paid/getMyPaidBack.action" target="right"><span>退回租金</span></a></li>
				<li><a href="paid/incomeByUser.action" target="right"><span>租金收入</span></a></li>
			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="iconfont nav-icon icon_8"></i><span>评价信息</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="myZulingEvaluation.action" target="right"><span>我的租赁评价</span></a></li>
				<li><a href="myChuzuEvaluation.action" target="right"><span>我的出租评价</span></a></li>			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="iconfont nav-icon icon_7"></i><span>其他操作</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="myDetails.action" target="right"><span>我的详情</span></a></li>
				<li><a href="findhasuserlist.action" target="right"><span>更新个人资料</span></a></li>			
				<li><a href="gotoUpdatePwd.action"  target="right"><span>修改密码</span></a></li>			
									
			</ul>
		</li>						
	</ul>
</div>
</div>
</div>					
</div>
</div>

<div class="layui-body" style="z-index: 0;background:rgba(255,255,255);">
	<!-- 内容主体区域 -->
	<div style="padding: 15px;" >
	<iframe src="welcome.jsp" name="right" frameborder="0" width="100%" height="1200"></iframe>
    </div>
</div>

<div class="layui-footer">
	<!-- 底部固定区域 -->
	底部固定区域
</div>
</div>
		
		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="myplugs/js/plugs.js">
		</script>
		<script type="text/javascript">
			//添加编辑弹出层
			function updatePwd(title, id) {
				$.jq_Panel({
					title: title,
					iframeWidth: 500,
					iframeHeight: 300,
					url: "updatePwd.jsp"
				});
			}
		</script>
		<script src="js/layui.js"></script>
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
		</script>
	</body>

</html>