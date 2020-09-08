<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/layout/header.jsp"%>

<div class="container">
	<h2>Board List</h2>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>Number</th>
				<th>Writer</th>
				<th>Title</th>
				<th>Viewcount</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${dto}"> 
			<tr>
				<td>${list.num}</td>
				<td>${list.writer}</td>
				<td><a href="${path}/detailForm/${list.num}">${list.subject}</a></td>
				<td>${list.viewcount}</td>
				<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="include/layout/footer.jsp"%>
