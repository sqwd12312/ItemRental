<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>

    <link rel="stylesheet" href="../css2/amazeui.min.css" />
    
	<link rel="stylesheet" href="../css2/admin.css" />    
	
</head>
<body>
<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">已退租列表</strong><small></small></div>
    </div>
	<hr>
</div>

<div>
	<form id="houseForm" name="houseForm"
		action="getAllCheckoutByUser.action"
		method=post >

					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									
									<td>物品编号</td>
									<td>物品名称</td>																		
									<td>退租人姓名</td>
									<td>退租人联系电话</td>
									<td>状态</td>
									<td>操作</td>																	
								</tr>
								<c:forEach items="${checkoutList}" var="list">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
										<td>${list.itemNumber}</td>
										<td>${list.itemName}</td>									
										<td>${list.zukeName}</td>										
										<td>${list.zukePhone}</td>
										<td>${list.status}</td>
										<td>
										<input type="hidden" name="id" value="${list.itemId}"/>
										<div class="am-btn-toolbar" >
											<div class="am-btn-group am-btn-group-xs">
												<a class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												style="background:rgba(255,255,255);"
												href="deleteCheckoutByUser.action?cId=${list.id}"
												onclick="return window.confirm('确定要删除该记录吗？')">
												<span class="am-icon-trash-o"></span> 删除</a>
											</div>
										</div>
									    </td>	
									</tr>
								</c:forEach>

							</tbody>
						</table>
						</div>
					

						
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