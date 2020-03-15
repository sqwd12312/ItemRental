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

    <link rel="stylesheet" href="css2/amazeui.min.css" />
   
   <style>
   

.error {
  
  font-size:13px;
  color: red;
  
}

   </style>
    <script type="text/javascript">
    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        $("#myform").validate({
        	
            rules : {
            	name : {
                    required : true,
                },
               
                idcard : {
                    required : true,
                    
                  
                },
                phone : {
                    required : true,
                   	number: true
                  
                }
                
            },
            messages : {
            	name : {
                    required : "姓名不能为空！",
                },
               
                idcard : {
                    required : "身份证号码不能为空！",
                   
                },
                phone : {
                    required : "手机号码不能为空！",
                    number: "请输入正确的手机号码"
                  
                }
                
            }
        });
    })
	</script>
	
</head>
<body>

<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">更新我的基本信息</strong><small></small></div>
    </div>
	<hr>
</div>
<div class="result-content">
        <form action="checkuserlist.action" method="post" id="myform" name="myform" enctype="multipart/form-data" >
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                        	<input type="hidden" name="id" value="${userlist.id}"/>
                                <th><i class="require-red">*</i>姓名：</th>
                                <td>
                                    <input class="common-text required" value="${userlist.name}" id="name" name="name" size="50" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>身份证号码：</th>
                                <td><input class="common-text" name="idcard" value="${userlist.idcard }" id="idcard" size="50" type="text"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>手机号码：</th>
                                <td><input class="common-text" name="phone" value="${userlist.phone }" id="phone" size="50" type="text" ></td>
                            </tr>
                           
								<tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
							<tr>
                                <td><font id="error" color="red">${error }</font></td>
                            </tr>	
                        </tbody></table>
                </form>
          </div>
    
</body>
</html>