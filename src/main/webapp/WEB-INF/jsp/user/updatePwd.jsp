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
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-datepicker.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
    
    <link rel="stylesheet" href="css2/amazeui.min.css" />
    
   <style>
   

.info {
  
  font-size:13px;
  color: red;
  
}

   </style>
    <script type="text/javascript">
    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        
        $("#myform").validate({
        	
            rules : {
            	OriginalPwd:{
            		required:true,
            	},
            	NewPwd:{
            		required:true,
            	
            	},
            	ConfirmPwd:{
            		required:true,
            	
            	}
            },
            messages : {
            	OriginalPwd:{
            		required:"原密码为空",
            	},
            	NewPwd:{
            		required:"新密码不能为空",
            		
            	},
            	ConfirmPwd:{
            		required:"确认密码不能为空",
            		
            	}
            }
        });
    })
    
    
	</script>
	
</head>
<body>

<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">修改密码</strong><small></small></div>
    </div>
	<hr>
</div>
<div class="result-content">
        <form action="updatePwd.action" method="post" id="myform" name="myform" enctype="multipart/form-data" >
                    <table class="insert-tab" width="100%">
                        <tbody>
                           <tr>
                                <th><i class="require-red">*</i>原密码：</th>
                                <td>
                                	<input type="password"  name="OriginalPwd" id="OriginalPwd"> 
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>新密码：</th>
                                <td>
                                	<input type="password"  name="NewPwd"> 
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>确认密码：</th>
                                <td>
                                	<input type="password"  name="ConfirmPwd"> 
                                </td>
                            </tr>

        
        
								<tr>
                                <th></th>
                                <td>
                                    
                                    <input class="btn btn-primary btn6 mr10" value="修改" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
							<tr>
                                <td><font id="info" color="red">${info }</font></td>
                            </tr>	
                        </tbody></table>
                </form>
          </div>
    
</body>
</html>