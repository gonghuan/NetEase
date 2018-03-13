<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.netease.onlineEducation.learning.Model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="${cp }/css/index.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${cp }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${cp }/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#eee;">
	<%@include file="WEB-INF/jsp/header.jsp" %>
	<div class="content">
		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#allGoodsDiv" data-toggle="tab">所有商品</a>
			</li>
			<c:if test='${user.getRole() == 0}'>
				<li>
					<a href="#GoodsNotBoughtDiv" data-toggle="tab">未购买商品</a>
				</li>
			</c:if>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="allGoodsDiv"></div>
			<c:if test="${user != null}">
				<div class="tab-pane fade in" id="GoodsNotBoughtDiv"></div>
			</c:if>
		</div>
	</div>
</body>

<script type="text/javascript">
	var user = '${user}';
	$(function(){
		if(user == ''){
			$.ajax({
				url: "${cp}/goods/listAllGoods",
				type: 'post',
				dataType: 'json',
				success: function(data){
					var html = '';
					var length = data.length;
					for(var i = 0; i < length; i++){
						html += "<div class='goodsDetailDiv' data-id='" + data[i].id +"'><img src='" + data[i].image
						+ "'/><div class='nameDiv'>" + data[i].name +
						"</div><div class='priceDiv'><span>￥ </span><span class='priceSpan'>" + 
						data[i].price + "</span></div></div>";
					}
					$('div#allGoodsDiv').html(html);
					$('div.goodsDetailDiv').click(function(){
						var id = $(this).data('id');
						window.location.href = '${cp}/show?id=' + id;
					});
				},
				error: function(data, e, status){
					alert('发生错误');
					console.log(e);
					console.log(status);
				}
			});
		}else{
			var userid = '${user.getId()}';
			$.ajax({
				url: "${cp}/orders/getAllGoodsAndOrders",
				type: 'post',
				success: function(data){
					var html = '';
					var secondHtml = '';
					var length = data.length;
					var userId = '${user.getId()}';
					for(var i = 0; i < length; i++){
						if(userId == 1 || userId == '1'){
							if(data[i].orders == null || data[i].orders.userid != userId){
								html += "<div class='goodsDetailDiv' data-id='" + data[i].id +"' data-price='"+
								0+"'><img src='" + data[i].image
								+ "'/><div class='nameDiv'>" + data[i].name +
								"</div><div class='priceDiv'><span>￥ </span><span class='priceSpan'>" + 
								data[i].price + "</span></div></div>";
								
								secondHtml += "<div class='goodsDetailDiv' data-id='" + data[i].id +"' data-price='"+
								0+"'><img src='" + data[i].image
								+ "'/><div class='nameDiv'>" + data[i].name +
								"</div><div class='priceDiv'><span>￥ </span><span class='priceSpan'>" + 
								data[i].price + "</span></div></div>";
							}else{
								html += "<div class='goodsDetailDiv' data-id='" + data[i].id +"' data-price='"+
								data[i].orders.price+"'><img src='" + data[i].image
								+ "'/><div class='nameDiv'>" + data[i].name +
								"</div><div class='priceDiv'><span>￥ </span><span class='priceSpan'>" + 
								data[i].price + "</span></div><span class='had'><b>已购买</b></span></div>";
							}
						}
						if(userId == 2 || userId == '2'){
							if(data[i].orders == null || data[i].ownerid != userId){
								html += "<div class='goodsDetailDiv' data-id='" + data[i].id +"' data-price='"+
								0+"'><img src='" + data[i].image
								+ "'/><div class='nameDiv'>" + data[i].name +
								"</div><div class='priceDiv'><span>￥ </span><span class='priceSpan'>" + 
								data[i].price + "</span></div></div>";
							}else{
								html += "<div class='goodsDetailDiv' data-id='" + data[i].id +"' data-price='"+
								data[i].orders.price+"'><img src='" + data[i].image
								+ "'/><div class='nameDiv'>" + data[i].name +
								"</div><div class='priceDiv'><span>￥ </span><span class='priceSpan'>" + 
								data[i].price + "</span></div><span class='had'><b>已售出</b></span></div>";
							}
						}
					}
					$('div#allGoodsDiv').html(html);
					$('div#GoodsNotBoughtDiv').html(secondHtml);
					$('div.goodsDetailDiv').click(function(){
						var id = $(this).data('id');
						if($(this).data('price') == 0 || $(this).data('price') == '0'){
							window.location.href = '${cp}/show?id=' + id + '&price=' + 0;
						}
						else{
							window.location.href= '${cp}/show?id=' + id + '&price=' + $(this).data('price');
						}
					});
				},
				error: function(data, e, status){
					alert('发生错误');
					console.log(e);
					console.log(status);
				}
			})
		}
		
	});
</script>
</html>