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
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
	</script>
	<style type="text/css">
	
	
	</style>
	<script type="text/javascript">
	var info="${param.info}";
	if(info=="zusuccess"){
	alert("该物品成功租赁！可前往在租列表查看");
	}
		
	</script>
</head>
<body>
<div class="result-title">
<h1>租赁申请列表</h1>
</div>

<div>
	<form id="houseForm" name="houseForm"
		action="findapplylist.action"
		method=post >

					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<td>物品编号</td>
									<td>物品名称</td>
									<td>价格/天</td>
									<td>申请人姓名</td>
									<td>申请人电话号码</td>
									<td>起租时间</td>
									<td>停租时间</td>
									<td>支付租金</td>	
									<td>出租人姓名</td>
									<td>出租人电话号码</td>																										
									<td>操作</td>
								
									
								</tr>
								<c:forEach items="${applylist}" var="applylist">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
										<c:forEach items="${applylist.apply}" var="apply">
										<td>${apply.itemNumber }</td>
										<td>${apply.itemName}</td>
										<td>${apply.price}&nbsp;元</td>
										<td>${applylist.name}</td>
										<td>${applylist.phone}</td>
										<td>${apply.fromDate}</td>
										<td>${apply.toDate}</td>
										<td>${apply.totalPrice}&nbsp;元</td>
										<td>${apply.masterName}</td>
										<td>${apply.masterPhone}</td>
										<td>
										<a class="link-update"
											href="hetong/inserthetong.action?itemId=${apply.itemId}">同意租赁</a>
											&nbsp;&nbsp; 
											<input type="hidden" name="id" value="${apply.itemId}"/>
											<a class="link-del"
											 href="refuseapply.action?itemId=${apply.itemId}&&id=${apply.orderNumber}"
											onclick="return window.confirm('确定要拒绝该租客的申请吗？')">拒绝租赁</a></td>
										
											
									</td>		
										
										
									</tr>
                                   </c:forEach>
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