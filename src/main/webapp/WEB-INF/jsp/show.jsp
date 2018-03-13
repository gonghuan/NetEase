<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.netease.onlineEducation.learning.Model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${cp }/css/show.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${cp }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${cp }/js/bootstrap.min.js"></script>
<title>商品详情</title>
</head>
<body style="background-color:#eee;">
	<%@include file="header.jsp"%>
	<div class="content">
		<div class="imgDiv">
			<img alt="${goods.name }" src="${goods.image }">
			<input type="hidden" value="${goods.id}" id="hiddenInput">
		</div>
		<div class="detailDiv">
			 <div class="nameDiv"><span>${goods.name }</span></div>
			 <div class="abstractsDiv"><span>${goods.abstracts }</span></div>
			 <div class="priceDiv">￥<span>${goods.price }</span></div>
			 <div class="amountDiv">
			 	<span class="minusSpan">-</span>
			 	<input type="text" value="1" id="amountInput">
			 	<span class="addSpan">+</span>
			 	<span class="warningSpan">单次购买下限是1，上限是999</span>
			 </div>
			 
			 <div class="buyDiv">
			 	<c:if test="${user.getRole() == 0 && price == '0'}">
			 		<button class="btn btn-primary" id="buyBtn">加入购物车</button>
			 	</c:if>
			 	<c:if test="${user.getRole() == 0 && price != '0'}">
			 		<button class="btn btn-primary" id="buyBtn" disabled="disabled">已购买</button>
			 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 		<span class='infoSpan'>当时购买价格：&nbsp;&nbsp;￥${price}</span>
			 	</c:if>
			 	<c:if test="${user.getRole() == 1 }">
			 		<a id='editA'>编辑</a>
			 	</c:if>
			 </div>
		</div>
		<div class="infoDiv">
			 <span class="labelSpan">详细信息</span>
			 <span>${goods.info }</span>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('a#editA').click(function(){
		var goodsid = $('#hiddenInput').val();
		window.location.href='${cp}/publish/' + goodsid;
	});

	$('.minusSpan').click(function(){
		$('.warningSpan').hide();
		var val = $('#amountInput').val();
		val = parseInt(val, 10);
		if(val > 1){
			
			$('#amountInput').val(val - 1);
		}else{
			$('.warningSpan').show();
		}
	});
	
	$('.addSpan').click(function(){
		$('.warningSpan').hide();
		var val = $('#amountInput').val();
		val = parseInt(val, 10);
		if(val < 100){
			$('#amountInput').val(val + 1);
		}else{
			$('.warningSpan').show();
		}
	});
	
	$('#amountInput').focus(function(){
		$('.warningSpan').hide();
	});
	
	$('#amountInput').blur(function(){
		var val = $('#amountInput').val();
		if(val == ''){
			$('.warningSpan').show();
		}else{
			val = parseInt(val, 10);
			if(val <= 1 || val > 999){
				$('.warningSpan').show();
			}
		}
	});
	
	$('#buyBtn').click(function(){
		var isConfirmed = window.confirm('确定加入购物车吗？');
		if(isConfirmed){
			var goodsid = $('#hiddenInput').val();
			var price = $('.priceDiv').find('span').text();
			var amount = $('#amountInput').val();
			var goodsName = $('.nameDiv').text();
			$.ajax({
				url : '${cp}/goods/addIntoCart',
				data : {'goodsid' : goodsid, 'price' : price, 'amount' : amount, 'goodsName' : goodsName},
				type : 'post',
				dataType : 'text',
				success: function(data){
					if(data == 'ok'){
						window.location.href = 'index.jsp';
					}
					if(data == 'no'){
						alert('发生未知错误');
					}
				},
				error: function(data, e, status){
					alert('发生未知错误');
					console.log(e);
					console.log(status);
				}
			});
			
		}
	});
</script>
</html>