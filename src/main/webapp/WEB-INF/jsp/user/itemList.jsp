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
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">物品列表</strong><small></small></div><br>
		<div class="search-wrap">
                <form action="getItemByCompositeQuery.action" method="post" >
                    <table class="search-tab">
                        <tr>
                            <th width="100">物品编号：</th>
                            <td><input class="common-text"  name="itemId" value="${qItem.itemId }"  ></td>
                            <th width="100">物品名称：</th>
                            <td><input class="common-text"  name="itemName" value="${qItem.itemName }"  ></td>
                            <th width="150">租金区间(元/天)：</th>
                            <td><input class="common-text"  name="minPrice" value="${qItem.minPrice }"  ></td>
                            <th width="5">—</th>
                            <td><input class="common-text"  name="maxPrice" value="${qItem.maxPrice }"  ></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
    </div>
	<hr>
</div>
	<form id="houseForm" name="houseForm"  action="getItemByCompositeQuery.action" method=post >

					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<td>物品编号</td>									
									<td>物品名称</td>
									<td>备注</td>
									<td>价格/天</td>
									<td>状态</td>									
									<td>操作</td>																	
								</tr>
								<c:forEach items="${itemList}" var="itemList">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">										
										<td>${itemList.itemNumber }</td>
										<td>${itemList.name}</td>
										<td>${itemList.note}</td>
										<td>${itemList.price}&nbsp;元</td>
										<td>${itemList.status}</td>
										<td>
										<c:choose>
										  <c:when test="${ itemList.status=='未租赁'}">
										    <a class="link-update"										
											href="getEvalutionByItemNumber.action?itemNumber=${itemList.itemNumber}">查看评价</a>
											&nbsp;&nbsp;
											<a class="link-update"										
											href="paid/gotopay.action?itemId=${itemList.itemId}">申请租赁</a>
											&nbsp;&nbsp;										 
										  </c:when >
										  <c:when test="${ itemList.status=='已租赁'}">
													该物品已被租赁
											&nbsp;&nbsp; 
										  </c:when >
										  <c:otherwise>
												    该物品已被申请
										  </c:otherwise>
										 </c:choose>
										
											
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