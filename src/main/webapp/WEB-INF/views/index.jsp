<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/layout/header.jsp"%>

<div class="container">
	<h2>Board List</h2>
	<table class="table">
		<thead class="thead-dark">
			<tr align="center">
				<th>Number</th>
				<th>Writer</th>
				<th>Title</th>
				<th>Viewcount</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${dto}">
				<tr align="center">
					<td>${list.num}</td>
					<td>${list.writer}</td>
					<td align="left"><a href="${path}/detailForm/${list.num}">${list.subject}</a></td>
					<td>${list.viewcount}</td>
					<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<ul class="pagination justify-content-center">
	<c:if test="${1 < page.curPage}">
		<li class="page-item"><a class="page-link" href="javascript:list(1)">First</a></li>
	</c:if>
	<c:if test="${1 < page.curBlock}">
		<li class="page-item"><a class="page-link" href="javascript:list('${page.prePage}')">Previous</a></li>
	</c:if>
	<c:forEach var="i" begin="${page.blockStart}" end="${page.blockEnd}">
		<c:choose>
			<c:when test="${page.curPage == i}">
				<li class="page-item"><a class="page-link" href="#" style="color: red;">${i}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="javascript:list('${i}')">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${page.curBlock < page.totalBlock}">
		<li class="page-item"><a class="page-link" href="javascript:list('${page.nextPage}')">Next</a></li>
	</c:if>
	<c:if test="${page.curPage < page.totalPage}">
		<li class="page-item"><a class="page-link" href="javascript:list('${page.totalPage}')">Last</a></li>
	</c:if>
</ul>
</div>

<script>
function list(page) {
	/* location.href = "${path}/auth/list?curPage=" + page; */
	location.href = "${path}?curPage=" + page;
}
</script>

<%@ include file="include/layout/footer.jsp"%>
