<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	    <link rel="stylesheet" href="css2/amazeui.min.css">
		<link rel="stylesheet" href="css2/admin.css">
		<link rel="stylesheet" href="css2/app.css">
		
<link rel="stylesheet" href="./example/example.css">
<link rel="stylesheet" href="./example/buttons.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<script src="lib/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="dist/sweetalert2.min.js"></script>

<link rel="stylesheet" href="./dist/sweetalert2.min.css">

		
		<style>
			.admin-main{
				padding-top: 0px;
			}
		</style>
	</head>
	<body>
		<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-g">
					<form class="am-form am-form-horizontal"
					     action="updatePwd.action"
						 method="post"
						 style="padding-top:30px;" data-am-validator>
						 <div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">原密码 </label>
							<div class="am-u-sm-9">
								<input type="password" id="doc-vld-pwd-1" required placeholder="请输入新密码" 
									name="OriginalPwd"> <small>输入原密码。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">新密码 </label>
							<div class="am-u-sm-9">
								<input type="password" id="doc-vld-pwd-1" required placeholder="请输入新密码" 
									name="NewPwd"> <small>输入新密码。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								重复密码</label>
							<div class="am-u-sm-9">
								<input type="password" id="doc-vld-pwd-2" required placeholder="请输入重复密码" 
									name="ConfirmPwd"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入重复密码。</small>
							</div>
						</div>
						<div class="am-form-group">

						
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="修改密码" />
							</div>
						
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/js/libs/jquery-1.10.2.min.js">
	</script>
	<script type="text/javascript" src="myplugs/js/plugs.js">
	</script>
	<!-- 弹框 -->

	</body>
</html>
