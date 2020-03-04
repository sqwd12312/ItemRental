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
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../js/jquery-ui-datepicker.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/jquery-ui.css"/>
    
    <script type="text/javascript" src="../js/laydate.js"></script>
    <link rel="stylesheet" href="../css2/amazeui.min.css" />
    
    
	<style type="text/css">
	.sum{
	float:right;
	}
	
	</style>

	
</head>
<body>
<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">已缴租金</strong><small></small></div>
    </div>
	<hr>
</div>
<div class="result-content">
           <div class="search-wrap">
                <form action="getAllPaidRent.action" method="post" name="myform">
                    <table class="search-tab">
                        <tr>
                            <th width="120">租客Id：</th>
                            <td><input class="common-text"  name="zukeId" value="${vo.zukeId }" id="zukeId" type="text"></td>
                            <th width="100">起始日期:</th>
                            <td><input class="laydate-icon" name="fromdate"  value="${vo.fromdate}" id="fromdate" type="text" readonly></td>
                            <th width="100">截止日期:</th>
                            <td><input class="laydate-icon"  name="todate" value="${vo.todate}" id="todate" type="text" readonly></td>
                            <td><input type="hidden" id="page" name="page" value=""></td>
                           
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>

			<div class="result-content">
				<table id=grid  class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<td>订单号</td>
									<td>物品编号</td>
									<td>物品名称</td>
									<td>缴纳人</td>									
									<td>缴纳租金</td>
									<td>缴纳日期</td>
									<td>状态</td>
									<td>操作</td>							
									
								</tr>
								<c:forEach items="${queryPaids}" var="paid">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
										<td>${paid.paid.id}</td>
										<td>${paid.paid.itemNumber}</td>										
										<td>${paid.paid.itemName}</td>
										<td>${paid.zuke.name}</td>
										<td>${paid.paid.price}</td>
										<td>${paid.paid.paydate}</td>
										<td>${paid.paid.status}</td>
										<td>
										<a class="link-update"
											href="deletepaid.action?payId=${paid.paid.id}"
											onclick="return window.confirm('确定删除吗？')">删除</a>
											&nbsp;&nbsp; 																																
									    </td>		
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
						以上共收入租金：<B style="color:red">${sum} </B>元
						</div>
							<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
								
									共[<B>${p.total}</B>]条记录，共[<B>${p.pages}</B>]页
									,

									<c:if test="${ p.pageNum > 1 }">
													[<A href="javascript:to_page(${p.prePage})">前一页</A>]
												</c:if>
									第<B>${p.pageNum}</B>页

									<c:if test="${ p.pageNum < p.pages }">
													[<A href="javascript:to_page(${p.nextPage})">后一页</A>] 
												</c:if>									
	                       </div>	
</div>
 <script language=javascript>
	// 提交分页的查询的表单
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		document.myform.submit();
	}
</script>

<script type="text/javascript">
        //时间选择器
        !function () {
            laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
            laydate({ elem: '#todate' });//绑定元素
        }();

        !function () {
            laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
            laydate({ elem: '#fromdate' });//绑定元素
        }();
        
</script>
</body>
</html>