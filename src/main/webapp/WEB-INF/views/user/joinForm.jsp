<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/layout/header.jsp"%>
<!-- 주석추가 -->
<!-- comment attach -->
<div class="container">
	<div class="form-group">
		<label for="username">Username:</label> <input type="text" class="form-control" id="username">
	</div>
	<div class="form-group">
		<label for="password">Password:</label> <input type="password" class="form-control" id="password">
	</div>
	<div class="form-group">
		<label for="email">Email:</label> <input type="email" class="form-control" id="email">
	</div>
	<div class="form-group">
		<label for="role">Role list:</label> <select class="form-control" id="role">
			<option value="ROLE_USER" selected>User</option>
			<option value="ROLE_GUEST">Guest</option>
			<option value="ROLE_ADMIN">Admin</option>
		</select>
	</div>
	<button id="btn-save" class="btn btn-primary">Save</button>
</div>

<script>
$(function(){
	$("#btn-save").click(function(){
		console.log("Save clicked");
		const data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			role: $("#role").val()
		}
		console.log(data);
		$.ajax({
			type: "post",
			url: "${path}/user/insert",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data),
			success: function(result){
				console.log(result);
				if(result.status === 200){
					location.href="${path}";
				}else{
					console.log("user insert fail!");
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
