<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
    
   <style>
   

.info {
  
  font-size:13px;
  color: red;
  
}

   </style>
    <script type="text/javascript">
    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        $("#myform").validate({
        	
            rules : {
            	fromDate: {
                    required : true,
                },
                toDate: {
                    required : true,
                },
                totalPrice: {
                    required : true,
                } 
            },
            messages : {
            	fromDate: {
                    required : "起租时间不能为空",
                },
                toDate: {
                    required : "退租时间不能为空",
                }
                ,
                totalPrice: {
                    required : "总租金不能为空",
                    min: "请输入正确的总租金"                 
                }                
            }
        });
    })
    
    
	</script>
	
</head>
<body>


<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg" style="color:#F00" >请确认无误再提交，订单提交时默认支付！</strong></div>
    </div>
	<hr>
	
</div>

<div class="result-content">
        <form action="../InsertItemApply.action" method="post" id="myform" name="myform" enctype="multipart/form-data" >
                    <table class="insert-tab" width="100%">
                        <tbody>
                           	<tr>
                                <font id="info" color="red">${info }</font>
                            </tr>
                           <tr>
                           
                                <th><i class="require-red">*</i>物品编号：</th>
                                <td>
                                    <input class="common-text required" value="${apply.itemNumber}" id="itemNumber" name="itemNumber" size="50" type="text" readonly style="width:200px;">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>物品名称：</th>
                                <td><input class="common-text" name="itemName" value="${apply.itemName }" id="itemName" size="50" type="text" readonly style="width:200px;"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>出租方（甲方）：</th>
                                <td><input class="common-text" name="chuZu" value="${Chuzu}" id="chuzu" size="50" type="text" readonly style="width:200px;"></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>承租方（乙方）：</th>
                              <td><input class="common-text" name="zuKe" value="${Zuke}" id="zuke" size="50" type="text" readonly style="width:200px;"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>每日租金：</th>
                              <td><input class="common-text" name="price" id="price" value="${apply.price}" size="50"  type="text" readonly style="width:200px;"></td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>起租时间：</th>
                                
                              <td><input class="inline laydate-icon" name="fromDate" id="fromdate" value="${apply.fromDate}" size="50"  type="text" readonly style="width:180px;"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>退租时间：</th>
                              <td><input class="inline laydate-icon" name="toDate" id="todate" value="${apply.toDate}" size="50"  type="text" readonly style="width:180px;">
                              </td>
                            </tr> 
                            <tr>
                                <th><i class="require-red">*</i>需交租金：</th>
                              <td><input class="common-text" name="totalPrice" id="totalPrice" value="${apply.totalPrice}" size="50"  type="text" style="width:200px;"
                              onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"></td>
                            </tr>                                                       
								<tr>
                                <th></th>
                                <td>
                                    <input type="hidden" name=itemId value="${apply.itemId}"/>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                    
                                    
                                </td>
                            </tr>
	
                        </tbody></table>
                </form>
          </div>

    <script type="text/javascript">
        !function () {
            laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
        }();

        //日期范围限制
        var fromdate = {
            elem: '#fromdate',
            format: 'YYYY-MM-DD',
            min: laydate.now(), //设定最小日期为当前日期
            max: '2099-06-16', //最大日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                todate.min = datas; //开始日选好后，重置结束日的最小日期
                todate.fromdate = datas //将结束日的初始值设定为开始日
            }
        };

        var todate = {
            elem: '#todate',
            format: 'YYYY-MM-DD',
            min: laydate.now(),
            max: '2099-06-16',
            istime: true,
            istoday: false,
            choose: function (datas) {
                fromdate.max = datas; //结束日选好后，充值开始日的最大日期
            }
        };
        laydate(fromdate);
        laydate(todate);

        //自定义日期格式
        laydate({
            elem: '#test1',
            format: 'YYYY年MM月DD日',
            festival: true, //显示节日
            choose: function (datas) { //选择日期完毕的回调
                alert('得到：' + datas);
            }
        });

        //日期范围限定在昨天到明天
        laydate({
            elem: '#hello3',
            min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
            max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
        });
    </script>
    
    
    



   
</body>

</html>