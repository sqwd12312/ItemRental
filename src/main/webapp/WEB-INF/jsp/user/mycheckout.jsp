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

	<script type="text/javascript">
	var error="${param.error}";
	if(error=="applysuccess"){

	alert("申请已提交，请耐心等待管理员的处理。如需查看进度，可前往“我的退租申请”中查看");
	}
	</script>
</head>
<body>
<div>
<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">已退租列表</strong><small></small></div>
    </div>
	<hr>
</div>
	<form id="houseForm" name="houseForm" action="deleteCheckOutByIds.action" method="post" >
		<input class="btn btn-primary btn6 mr10" value="删除" type="submit" onclick="return window.confirm('确定要删除这些记录吗？')"><br><br>
					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">									
									<td align='center'><input type="checkbox" id="checkAll" onclick="checkall()"></td>
									<td>物品编号</td>
									<td>物品名称</td>
									<td>出租人</td>
									<td>出租人联系方式</td>
									<td>状态</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${checkoutList}" varStatus="i" var="list">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">										
										<td align='center'><input type="checkbox" name="ids" value="${list.id}"></td>
										<td>${list.itemNumber}</td>										
										<td>${list.itemName}</td>
										<td>${list.userlist.name}</td>
										<td>${list.userlist.phone}</td>
										<td>${list.status}</td>
										<td>
										  <c:choose>
										    <c:when test="${list.evaStatus=='0'}">					     
											  <a class="link-del"						  
											   href="../toInsertEvaluation.action?itemNumber=${list.itemNumber}&&cId=${list.id}">评价</a>				   
											   &nbsp;&nbsp;
											   	
											</c:when >
											<c:when test="${list.evaStatus=='1'}">
											已评价
											&nbsp;&nbsp;
											</c:when >											 
										  </c:choose>										  										
											  <a class="link-del"
											   href="deletecheckout.action?cId=${list.id}"
											   onclick="return window.confirm('确定要删除该记录吗？')">删除</a>
										 
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

<script type="text/javascript">
    //全选，全不选；方法核心就是让所有的复选框的状态与全选复选框的状态一致
    function checkall(){
    var isChecked = (document.houseForm.checkAll.checked == true);//全选框的状态
    var elements = document.houseForm.elements;//选出所有的元素
    var counter = elements.length;
    for(i=0;i<counter;i++){
        var element = elements[i];
        if(element.type=="checkbox" & element.name=="ids"){//过滤需要的复选框
            element.checked=isChecked;//将复选框的状态改为全选框的状态
        }
    }
}
    </script>

</body>
</html>