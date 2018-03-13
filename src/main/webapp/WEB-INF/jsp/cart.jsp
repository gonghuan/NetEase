<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link href="${cp }/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
<link href="${cp }/css/cart.css" rel="stylesheet" type="text/css">
<script src="${cp }/js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="${cp }/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${cp }/js/bootstrap-table-zh-CN.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="header.jsp" %>
	<div class="content">
		<div class="title">已添加到购物车的内容</div>
		<div class="goodsDiv">
			<table id="table"></table>
			<button id='backBtn' class='btn btn-warning' onclick='back()'>退出</button>
			<button id="buyBtn" class="btn btn-primary" onclick="buy()">购买</button>
		</div>
	</div>
</body>
<script>
	$(function(){
		$('#table').bootstrapTable({
			url: '${cp}/cart/getCart',
			method: 'post',
			pagination: true,
			pageNumber: 50,
			pageSize: 10,
			pageList: [10, 20, 30, 40, 50],
			sidePagination: 'client',
			striped: true,
			uniqueId: 'id',
			paginationPreText: '上一页',
			paginationNextText: '下一页',
			columns: [
				{checkbox: true},
				{title: '商品名', field: 'goodsname'},
				{title: '价格', field: 'price'},
				{title: '用户id', field: 'userid'},
				{title: '商品id', field: 'goodsid'},
				{title: '数量', field: 'amount', formatter: function(value, row, index){
					var html = "<span class='minusSpan' onclick='minus(this)'>-</span>&nbsp;&nbsp;<span class='amountSpan'>" + value
					+ "</span>&nbsp;&nbsp;<span class='addSpan' onclick='add(this)'>+</span>";
					return html;
				}},
				{title: '操作', field: 'id', formatter: function(value, row, index){
					var html = "<a href= 'javascript:deleteCart(" + value + ")'>删除</a>";
					return html;
				}}
			]
		});
	});
	
	function minus(obj){
		var amount = $(obj).next('span.amountSpan').text();
		amount = parseInt(amount);
		if(amount > 1){
			amount--;
			$(obj).next('span.amountSpan').text(amount);
		}
	}
	
	function add(obj){
		var amount = $(obj).prev('span.amountSpan').text();
		amount = parseInt(amount);
		if(amount < 999){
			amount++;
			$(obj).prev('span.amountSpan').text(amount);
		}
	}
	
	function deleteCart(id){
		if(confirm('确定从购物车中删除吗？')){
			$.ajax({
				url:'${cp}/cart/deleteCart/'+id,
				success: function(data){
					if(data == true){
						$('#table').bootstrapTable('refresh');
					}else{
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
	}
	
	function buy(){
		var selections = $('#table').bootstrapTable('getSelections');
		if(selections == ''){
			alert('请先选择要购买的商品');
		}else{
			var jsonArray = [];
			for(var i = 0; i < selections.length; i++){
				var json = {};
				json.id = selections[i].id;
				json.userid = selections[i].userid;
				json.goodsid = selections[i].goodsid;
				json.price = selections[i].price;
				json.amount = selections[i].amount;
				jsonArray.push(json);
 			}
			var jsonArrays = JSON.stringify(jsonArray);
			$.ajax({
				url: '${cp}/cart/buyFromCart',
				data: {'cartArray' : jsonArrays},
				type: 'post',
				success: function(data){
					if(data == true){
						alert('购买成功！');
						window.location.href = 'index.jsp';
					}else{
						alert('发生未知错误');
					}
				},
				error: function(data, e, status){
					alert('发生未知错误');
					console.log(e);
					console.status(status);
				}
			});
		}
	}
	
	function back(){
		window.history.back();
	}
	
</script>
</html>