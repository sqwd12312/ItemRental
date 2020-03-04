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
   <style>
   

.error {
  
  font-size:13px;
  color: red;
  
}

   </style>
    <script type="text/javascript">
    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        
        $("#time").datepicker();
        $("#content").validate({
        	
            rules : {
            	time:{
            		required:true,
            	},
            	content:{
            		required:true,
            	
            	}
            },
            messages : {
            	time:{
            		required:"日期不能为空",
            	},
            	content:{
            		required:"评价内容不能为空",
            		
            	}
            }
        });
    })
    
    
	</script>
	
</head>
<body>

<div class="result-title">
<h1>物品评价</h1>
</div>
<div class="result-content">
<div class="sidebar-title">
        <form action="insertEvaluation.action" method="post" id="myform" name="myform" enctype="multipart/form-data" >
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <input type="hidden" name="cId" value="${cId}"/>
                           <tr>
                                <th><i class="require-red">*</i>物品编号：</th>
                                <td>
                                   
                                    <input class="common-text required" value="${itemNumber}" id="itemNumber" name="itemNumber" size="50" type="text" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>物品名称：</th>
                                <td><input class="common-text" name="name" value="${itemName }" id="name" size="50" type="text" readonly></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>出租人姓名：</th>
                                <td><input class="common-text" name="chuzuName" value="${chuzuName}" id="chuzuName" size="50" type="text" readonly></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>评价内容：</th>
                                <td><textarea class="common-text" name="content"  id="content" style="width:430px;height:80px;"></textarea></td>
                            </tr>
								<tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
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