<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/layout/header.jsp"%>
<!-- 로그인주석추가1 -->
<!-- 로그인주석추가2 -->
<!-- 로그인주석추가3 -->
<div class="container">
	<div class="form-group">
		<label for="username">Username:</label> <input type="text" class="form-control" id="username">
	</div>
	<div class="form-group">
		<label for="password">Password:</label> <input type="password" class="form-control" id="password">
	</div>
	<button id="btn-login" class="btn btn-primary">Login</button>
</div>

<script>
$(function(){
	const header = $("meta[name='_csrf_header']").attr("content");
	const token = $("meta[name='_csrf']").attr("content");
	
	$("#btn-login").click(function(){
		console.log("Login clicked");
		const data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		console.log(data);
		$.ajax({
			type: "post",
			url: "${path}/auth/login",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: data,
			success: function(result){
				console.log(result);
				if(result === ""){
					/* location.href="/"; */
					console.log("login success!");
				}else{
					console.log(result);
				}
			},
			error: function(error){
				console.log(error);
			}
		});
	});
});
</script>
<%@ include file="../include/layout/footer.jsp"%>
