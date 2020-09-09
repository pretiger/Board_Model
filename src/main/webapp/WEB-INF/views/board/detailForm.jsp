<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/layout/header.jsp"%>

<div class="container">
	<div class="card">
		<div class="card-body">
			<a href="#" class="btn btn-dark" onclick="history.back()">Back</a>
			<a href="#" class="btn btn-primary">Update</a>
			<a href="#" class="btn btn-danger">Delete</a>
			<a href="#" class="btn btn-success">Reply</a>
			<p>number:&nbsp;<i>${dto.num}</i>&nbsp;&nbsp;writer:&nbsp;<i>${dto.writer}</i></p>
			<h2 class="card-title">${dto.subject}</h2>
			<p class="card-text">${dto.content}</p>
		</div>
	</div>
</div>

<script>
	$(function() {
		$("#btn-login").click(function() {
			console.log("Login clicked");
			const data = {
				username : $("#username").val(),
				password : $("#password").val()
			}
			console.log(data);
			$.ajax({
				type : "post",
				url : "${path}/auth/login",
				contentType : "application/json;charset=utf-8",
				dataType : "json",
				data : JSON.stringify(data),
				success : function(result) {
					console.log(result);
					if (result.status === 200) {
						location.href = "${path}";
					} else {
						console.log("username or password mismatch!");
					}
				},
				error : function(error) {
					console.log(error);
				}
			});
		});
	});
</script>
<%@ include file="../include/layout/footer.jsp"%>
