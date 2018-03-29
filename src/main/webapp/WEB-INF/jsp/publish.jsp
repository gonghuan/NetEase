<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='${cp }/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
<link href='${cp }/css/publish.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src='${cp }/js/jquery-3.1.1.min.js'></script>
<script type="text/javascript" src='${cp }/js/bootstrap.min.js'></script>
<script type='text/javascript' src='${cp }/js/ajaxfileupload.js'></script>
<title>发布</title>
</head>
<body style="background-color:#eee;">
	<%@include file='header.jsp' %>
	<div class="content">
		<div class="title">内容发布</div>
		<div class='publishDiv'>
			<form class='form-horizontal' role='form'>
				<div class='form-group'>
					<label for='name' class='col-sm-2'>标题：</label>
					<div class='col-sm-8'>
						<input type='text' class='form-control' id='name' placeholder='2-80字符' name='name' value='${goods.getName() }'>
					</div>
					
				</div>
				<div class='form-group'>
					<label for='abstracts' class='col-sm-2'>摘要：</label>
					<div class='col-sm-8'>
						<input type='text' class='form-control col-sm-8' id='abstracts' placeholder='2-140字符' name='abstracts' value='${goods.getAbstracts() }'>
					</div>
				</div>
				<div class='form-group'>
					<label class='col-sm-2'>图片：</label>
					<div class='col-sm-8'>
						<input type='radio' name='imageRadio' id='netUrl'  value='图片地址' checked="checked">图片上传&nbsp;
						<input type='radio' name='imageRadio' id='localUrl'  value='本地上传'>本地上传
					</div>
				</div>
				<div class='form-group'>
					<div class=' col-md-offset-2 col-sm-8'>
						<input type='text' class='form-control' id='imageUrl' placeholder='图片地址' name='imageUrl' value='${goods.image }'>
						<input type='file' class='file' id='imgFile' name='file'>
						<button type='button' class='btn btn-primary' id='uploadImgBtn'>上传</button>
						
					</div>
				</div>
				<div class='form-group'>
					<label for='info' class='col-sm-2'>正文：</label>
					<div class='col-sm-10'>
						<textarea rows='10' class='form-control' id='info' placeholder='2-1000字符' style='resize:none' name='info'>
						${goods.getInfo() }
						</textarea>
					</div>
				</div>
				<div class='form-group'>
					<label for='price' class='col-sm-2'>价格(元)：</label>
					<div class='col-sm-3'>
						<input type='text' class='form-control' id='price' style='display: inline' name='price' value='${goods.getPrice() }'>
					</div>
				</div>
				<div class='form-group'>
					<div>
						<button type="button" class='btn btn-primary' id='publishBtn'>发布</button>
					</div>
				</div>
				<div class='imgDiv'>
					<img src='${goods.getImage() }'>
				</div>
				<input type="hidden" name="id" value='${goods.id }'>
			</form>
		</div>
	</div>
</body>
<script>
	$(function(){
		if($('img').attr('src') == ''){
			$('img').hide();
		}else{
			$('img').show();
		}
		$('.file').hide();
		$('#uploadImgBtn').hide();
	});
	$('input:radio').click(function(){
		if($(this).val() == '图片地址'){
			$('#imageUrl').val('');
			$('#imageUrl').show();
			$('.file').hide();
			$('#uploadImgBtn').hide();
			$('img').attr('src', '').hide();
		}
		if($(this).val() == '本地上传'){
			$('#imageUrl').hide();
			$('.file').show();
			$('#uploadImgBtn').show();
			$('img').attr('src', '').hide();
		}
	});
	$('input#imageUrl').on('input', function(){
		var imageUrl = $(this).val();
		if(imageUrl == ''){
			$('img').hide();
		}else{
			$('img').attr('src', imageUrl).show();
		}
	});
	$('button#uploadImgBtn').click(function(){
		var path = $('.file').val();
		if(path == ''){
			alert('请先选择文件！');
		}else{
			var file = $(this).prev('.file');
			var imagePath = $(file).val();
			var strExtension = imagePath.substr(imagePath.lastIndexOf('.')+1);
			if(strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'png' && strExtension != 'bmp'){
				alert('您选择的不是图片格式，需要时jpg,gif,png,bmp格式中的一种！');
			}else{
				$.ajaxFileUpload({
					type: 'post',
					url: '${cp}/goods/uploadImg',
					fileElementId: 'imgFile',
					dataType: 'text',
					success: function(data){
						if(data == 'false'){
							alert('上传图片失败');
						}else{
							$('#imageUrl').val(data);
							$('img').attr('src', data).show();
						}
					},
					error: function(data, status, e){
						alert('发生未知错误');
						console.log(status);
						console.log(e);
					}
				});	
			}
		}
	});
	$('button#publishBtn').click(function(){
		if(confirm('确定发布该商品吗？')){
			$.ajax({
				url: '${cp}/goods/publishGoods',
				data: $('form').serialize(),
				fileElementId: 'imageUrl',
				type: 'post',
				success: function(data){
					if(data == true){
						alert('发布成功！');
						location.reload();
					}else{
						alert('发生未知错误！');
					}
				},
				error: function(data, e, status){
					alert('发生未知错误！');
					console.log(e);
					console.log(status);
				}
			});
		}
		
	});
	
</script>
</html>