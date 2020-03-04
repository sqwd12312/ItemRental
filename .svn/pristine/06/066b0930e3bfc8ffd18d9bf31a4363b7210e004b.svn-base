<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <title>物品租赁系统</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
   
   <style>
   

.error {
  
  font-size:13px;
  color: red;
  
}

   </style>
 
	
</head>
<body>

<div class="result-title">
<h1>我的资料</h1>
</div>
<div class="result-content">
<div class="sidebar-title">
        <form action="checkuserlist.action" method="post" id="myform" name="myform" enctype="multipart/form-data" >
                    <table class="insert-tab" width="100%">
                        <tbody>
                        	<input type="hidden" name="id" value="${userlist.id}"/>
                                <th><i class="require-red"></i>姓名：</th>
                                <td>
                                    <input class="common-text required" value="${userDetails.name}" id="name" name="name" size="50" type="text" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red"></i>身份证号码：</th>
                                <td><input class="common-text" name="idcard" value="${userDetails.idcard }" id="idcard" size="50" type="text" readonly></td >
                            </tr>
                            <tr>
                                <th><i class="require-red"></i>手机号码：</th>
                                <td><input class="common-text" name="phone" value="${userDetails.phone }" id="phone" size="50" type="text" readonly></td>
                            </tr>
                            <tr>
                                <th><i class="require-red"></i>我的余额/元：</th>
                                 <td><input class="common-text" name="balance" value="${userDetails.balance }" id="balance" size="50" type="text" readonly></td>                                
                            </tr>
                           
								<tr>
                                <th></th>
                            </tr>
							<tr>
                                <font id="error" color="red">${error }</font>
                            </tr>	
                        </tbody></table>
                </form>
          </div>
          </div>
    
</body>
</html>