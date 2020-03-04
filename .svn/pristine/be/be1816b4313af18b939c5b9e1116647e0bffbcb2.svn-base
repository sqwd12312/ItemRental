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
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
</script>
<style type="text/css">
</style>
<script type="text/javascript">
	var error = "${param.error}";
	if (error == "applysuccess") {
		alert("申请已提交。如需查看进度，可前往“退租申请列表”中查看");
	}
	if (error == "chongFuTiJiao") {
		alert("申请已提交,请勿重复操作！可前往“退租申请列表”中查看进度");
	}
</script>
</head>
<body>
	<div>
		<div class="result-title">
			<h1>我的租赁</h1>
		</div>
		<form id="houseForm" name="houseForm" action="zulist/myzulist.action"
			method=post>
			<div class="result-title">
				<div class="result-list"></div>
			</div>

			<div class="result-content">
				<table id=grid class="result-tab" width="100%">
					<tbody>
						<tr
							style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
							<td>物品编号</td>
							<td>物品名称</td>
							<td>价格/天</td>
							<td>出租人</td>
							<td>出租人联系电话</td>
							<td>起租时间</td>
							<td>停租时间</td>
							<td>已交租金</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${hetongList}" varStatus="i" var="hetongList">

							<tr
								style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
								
								    
								    <td>${hetongList.itemNumber}</td>
									<td>${hetongList.itemName}</td>
									<td>${hetongList.price}&nbsp;元</td>
									<td>${hetongList.chuZu}</td>
									<td>${hetongList.masterPhone}</td>
									<td>${hetongList.fromDate}</td>
									<td>${hetongList.toDate}</td>
									<td>${hetongList.totalPrice}&nbsp;元</td>
									<td>
									<c:choose>
										  <c:when test="${hetongList.status=='申请中'}">
										              退租申请中
										  </c:when >
										  <c:when test="${hetongList.status=='已拒绝'}">
										              退租申请被拒绝
										     <a class="link-del"
										     href="applyout/insertapplyout.action?itemId=${hetongList.itemId}"
										     onclick="return window.confirm('确定要申请退租吗？')">再次申请</a>
										  </c:when >
										  <c:otherwise>
										     <a class="link-del"
										     href="applyout/insertapplyout.action?itemId=${hetongList.itemId}"
										     onclick="return window.confirm('确定要申请退租吗？')">申请退租</a>
										  </c:otherwise>
									 </c:choose>
								     </td>
							</tr>
						


						</c:forEach>

					</tbody>
				</table>
			</div>


			<tr>
			<tr>
				<span id=pagelink>
					<div
						style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top: 10px">
						共[<B>${p.total}</B>]条记录，共[<B>${p.pages}</B>]页 ,

						<c:if test="${ p.pageNum > 1 }">
													[<A href="javascript:to_page(${p.prePage})">前一页</A>]
												</c:if>
						<input type="hidden" name="page" id="page" value="" /> 第<B>${p.pageNum}</B>页

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