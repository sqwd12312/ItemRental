<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!--
	Author: W3layouts
	Author URL: http://w3layouts.com
	License: Creative Commons Attribution 3.0 Unported
	License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>物品租赁系统</title>
<link rel="stylesheet" href="css/style.css">

<link href="css/popup-box.css" rel="stylesheet" type="text/css" media="all" />
<!--Web-fonts-->
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href="//fonts.googleapis.com/css?family=Lora:400,700" rel="stylesheet">
<!--//Web-fonts-->
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Striking Dual form  Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //Meta tag Keywords -->
</head>
<body>
	<h1>物品租赁系统</h1>
	<div class="w3layouts">
	<!-- Sign in -->
		<div class="signin-agile">
			<h2>Login</h2>
			<label class="bar-w3-agile"></label>
			<form action="logincheck.action" method="post">
				<p>Username</p>
				<input type="text" name="username" class="name" placeholder="用户名" required="" />
				<p>Password</p>
				<input type="password" name="password" class="password" placeholder="密码" required="" />
				<br>
				<ul>
					<li>
						<input type="checkbox" id="brand1" value="">
						<label for="brand1"><span></span>Keep me signed in</label>
					</li>
				</ul>
				<a href="#">Forgot Password?</a><br>
				<div class="clear"></div>				
				<input type="submit" value="user" name="type">
				<input type="submit" value="admin" name="type">
			</form>
		</div>
	<!-- //Sign in -->
	<!-- Sign up -->
		<div class="signup-agileinfo">
			<h3>Sign Up</h3>
			<label class="bar-w3-agile"></label>
			<p>Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...</p>
			<h6>By creating an account, you agree to our <a href="#">Terms.</a></h6>
			<!-- Pop up -->
				<div class="more">
					<a class="book popup-with-zoom-anim button-isi zoomIn animated" data-wow-delay=".5s" href="#small-dialog">Sign Up</a>				
				</div>
			<!-- //Pop up -->
		</div>
	<!-- //Sign up -->
		<div class="clear"></div>
	</div>
	<div class="footer-w3l">
	</div>
	<!-- Pop up -->
	<div class="pop-up"> 
	<div id="small-dialog" class="mfp-hide book-form">
		<h3>Sign Up Form </h3>
			<form action="register.action" method="post">
				<p>Username</p>
				<input type="text" name="username" placeholder="" required=""/>
				<p>Password</p>
				<input type="password" name="password" class="password" placeholder="" required=""/>
				<p>IDNumber</p>
				<input type="text" name="idcard" placeholder="" required=""/>
				<p>Phone</p>
				<input type="text" name="phone" placeholder="" required=""/>
				<input type="submit" value="Sign Up">
			</form>
	</div>
</div>	
<!-- // Pop up -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
<script type="text/javascript" src="js/modernizr.custom.53451.js"></script> 
 <script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});
																						
						});
						
						var error="${error}";
						if(error=="error"){

						alert("帐户名或密码错误");
						}
						if(error=="success"){
							alert("恭喜您成功注册！");
						}
</script>
</body>
</html>