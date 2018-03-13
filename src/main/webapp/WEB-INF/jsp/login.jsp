<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${cp }/css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${cp }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${cp }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${cp }/js/md5.js"></script>
</head>
<body style="background-color:#eee;">
	<%@include file="header.jsp" %>
	<div class="content">
		<form role="form" class="form-horizontal" method="post">
			<div class="form-group">
				<label for="nameInput" class="control-label col-sm-3">用户名：</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="nameInput" placeHolder="请输入用户名" name="name">
				</div>
			</div>
			<div class="form-group">
				<label for="passwordInput" class="control-label col-sm-3">密码:</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="passwordInput" placeHolder="请输入用户名" name="password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4">
					<button type="button" class="btn" id="loginBtn" onclick='login()'>登录</button>
				</div>
			</div>
		</form>
		<div id="errorDiv" style="display:none;">
				输入的用户名或者密码错误！
		</div>
	</div>
</body>
<script type="text/javascript">
	function login(){
		var name = $('#nameInput').val();
		var password = $('#passwordInput').val();
		password = $.md5(password);
		$.ajax({
			url:"${cp}/user/login",
			data: {'name':name, 'password':password},
			dataType: 'text',
			type: 'post',
			success: function(data){
				if(data == 'yes'){
					window.location.href = 'index.jsp';
				}
				if(data == 'no'){
					$('div#errorDiv').show();
				}
			},
			error: function(data, e, status){
				alert('发生错误');
				console.log(e);
				console.log(status);
			}
		});
	}
	
	$(function(){
		$('input').focus(function(){
			$('div#errorDiv').hide();
		}); 
		
		document.onkeydown = function(event){
			if(event && event.keyCode == 13){
				login();
			}
		}
	});
</script>
</html>