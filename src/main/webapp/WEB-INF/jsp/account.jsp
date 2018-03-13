<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务</title>
<link href="${cp }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${cp }/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
<link href="${cp }/css/account.css" rel="stylesheet" type="text/css">
<script src="${cp }/js/jquery-3.1.1.min.js"></script>
<script src="${cp }/js/bootstrap.min.js"></script>
<script src="${cp }/js/bootstrap-table.min.js"></script>
<script src="${cp }/js/bootstrap-table-zh-CN.js"></script>
</head>
<body>
	<%@include file='header.jsp' %>
	<div class="content">
		<div class="title">已购买的内容</div>
		<div class="orders">
			<table id="table"></table>
		</div>
	</div>
</body>
<script>
	$(function(){
		$('#table').bootstrapTable({
			url:'${cp}/orders/getAllOrdersByUserId',
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
			showFooter: true,
			columns:[
			   {field: 'image', title: '内容图片', formatter: function(value, row, index){
				  return "<img src='" + value + "' class='image'>" 
			   }},
			   {field: 'id', visible: false},
			   {field: 'name', title: '商品名称'},
			   {field: 'orders.amount', title: '购买数量', footerFormatter: '合计'},
			   {field: 'orders.price', title: '购买价格', footerFormatter: function(value){
				   var count = 0;
				   for(var i in value){
					   count += value[i].orders.price;
				   }
				   return count.toFixed(2);
			   }},
			   {field: 'orders.time', title: '购买时间'}
			]
		});
	});
</script>
</html>