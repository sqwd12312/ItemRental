<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>物品租赁系统</title>  
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>

    <link rel="stylesheet" href="css2/amazeui.min.css" />

   <style>
.info {
  
  font-size:30px;
  color: red;
  
}
   </style>
   	
</head>
<body>
<div>
<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">评价详情</strong><small></small></div>
    </div>
	<hr>
</div>
	<form id="houseForm" name="houseForm" action="getEvalutionByItemNumber.action" method=post>
		<tr>
              <font id="info" color="red">${info }</font>
        </tr>
			<div class="result-title">
                 <div class="result-list">                   
                      <B style="color:red">物品名称：${itemName}&nbsp;&nbsp;物品编号：${itemNumber}</B>                        
                 </div>
             </div>

					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<td>用户</td>									
									<td>评价内容</td>
									<td>评价时间</td>																											
								</tr>
								<c:forEach items="${evaluationList}" var="evaluation">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">										
										<td>${evaluation.userName}</td>	
										<td>${evaluation.content }</td>	
										<td><fmt:formatDate value="${evaluation.time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>																			
									</tr>
								</c:forEach>								
							</tbody>							
						</table>
						</div>
						<tr>
							<span id=pagelink>
								<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
									共[<B>${p.total}</B>]条记录，共[<B>${p.pages}</B>]页
									,

									<c:if test="${ p.pageNum > 1 }">
									           <input type="hidden" name="itemNumber" value="${itemNumber}"/>
										      [<A href="javascript:to_page(${p.prePage})">前一页</A>]
									</c:if>
									<input type="hidden" name="page" id="page" value=""/>
									第<B>${p.pageNum}</B>页

									<c:if test="${ p.pageNum < p.pages }">
																				
											  <input type="hidden" name="itemNumber" value="${itemNumber}"/>
										      [<A href="javascript:to_page(${p.nextPage})">后一页</A>] 
											
									</c:if>

									
								</div>
							</span>
						
						</tr>
					</form>
</div>
 <script language=javascript>
	// 提交分页的查询的表单
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		document.houseForm.submit();
	}
</script>
</body>
</html>