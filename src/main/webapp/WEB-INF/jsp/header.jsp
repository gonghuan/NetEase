<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.netease.onlineEducation.learning.Model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link href="${cp }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${cp }/css/header.css" rel="stylesheet" type="text/css">
</head>
<%User user = (User)session.getAttribute("user"); %>
<body>
	<div class="header">
		<c:if test="${user == null }">
			<div class="pull-left pullLeft">请<a href="${cp}/login"> [登录]</a></div>
		</c:if>
		<c:if test="${user != null }">
			<div class="pull-left pullLeft">欢迎使用，<span class="nameSpan">${user.getName() }</span></div>
			<div class="pull-left pullLeft"><span class="loginOutSpan">退出登录</span></div>
		</c:if>
		<div class="pull-right pullRight"><a href="${cp }/index.jsp" class="indexA">首页</a></div>
		<c:if test="${user.getRole() == 0  }">
			<div class="pull-right pullRight"> <a href='${cp }/cart'>购物车</a> </div>
			<div class="pull-right pullRight"> <a href='${cp }/account'>财务</a></div>
		</c:if>
		<c:if test="${user.getRole() == 1 }">
			<div class="pull-right pullRight"><a href='${cp }/publish'>发布</a></div>
		</c:if>
	</div>
</body>
<script type="text/javascript">
	$('span.loginOutSpan').mouseover(function(){
		$(this).css('color', '#0084ff');
	}).mouseout(function(){
		$(this).css('color', '#fff');
	});
	
	$('span.loginOutSpan').click(function(){
		$.ajax({
			url: '${cp}/user/logout',
			type: 'post',
			success: function(data){
				if(data == true){
					window.location.href = '${cp}/login';
				}else{
					alert('发生未知错误');
					console.log(e);
					console.log(status);
				}
			},
			error: function(data, e, status){
				alert('发生未知错误');
				console.log(e);
				console.log(status);
			}
		});
	});
</script>
</html>