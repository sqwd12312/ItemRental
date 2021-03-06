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

	<script type="text/javascript">
	var error="${param.error}";
	if(error=="applycheck"){

	alert("你还没完善个人信息，请完善个人信息后再进行申请操作");
	}else if(error=="applysuccess"){
		alert("申请成功，请耐心等待房东联系您！");	
	}
		
	</script>
</head>
<body>
<div>
<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">我的租赁评价列表</strong><small></small></div>
    </div>
	<hr>
</div>
	<form id="houseForm" name="houseForm"
		action="myZulingEvaluation.action"
		method=post >
					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<td>物品名称</td>
									<td>物品编号</td>
									<td>评价内容</td>
									<td>评价时间</td>									
									<td>操作</td>																	
								</tr>
								<c:forEach items="${evaluationList}" var="evaluation">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">										
										<td>${evaluation.item.name}</td>
										<td>${evaluation.itemNumber }</td>
										<td>${evaluation.content}</td>										
										<td><fmt:formatDate value="${evaluation.time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
										<td>
										    <a class="link-update"										
											href="userToUpdateEvaluation.action?id=${evaluation.id}&&itemNumber=${evaluation.itemNumber }">更改评价</a>
											&nbsp;&nbsp;
											<a class="link-update"										
											href="deleteEvalutionById.action?id=${evaluation.id}"
											onclick="return window.confirm('确定删除吗？')">删除评价</a>
											&nbsp;&nbsp;										 										
									   </td>		
										
										
									</tr>

								</c:forEach>

							</tbody>
						</table>
						</div>
					

						<tr>
						<tr>
							<span id=pagelink>
								<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
									共[<B>${p.total}</B>]条记录，共[<B>${p.pages}</B>]页
									,

									<c:if test="${ p.pageNum > 1 }">
													[<A href="javascript:to_page(${p.prePage})">前一页</A>]
												</c:if>
										<input type="hidden" name="page" id="page" value=""/>
									第<B>${p.pageNum}</B>页

									<c:if test="${ p.pageNum < p.pages }">
													[<A href="javascript:to_page(${p.nextPage})">后一页</A>] 
												</c:if>

									
								</div>
							</span>
						
						</tr>
						</tbody>
					
					

					
						</tbody>
				

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