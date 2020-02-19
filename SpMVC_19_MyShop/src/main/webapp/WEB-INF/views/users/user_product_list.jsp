<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>user product list</title>

<script type="text/javascript">
$(function() {
	$(".product").click(function() {
		let id=$(this).data("id")
		document.location.href="${rootPath}/user/product/detail?id="+id
	})
})
</script>
</head>
<body>
<section>
	<table>
		<tr>
		<th>상품이름</th>
		<th>가격</th>
		</tr>
		
		<c:choose>
			<c:when test="${prodList != null }">
				<c:forEach items="${prodList }" var="vo" varStatus="i">
					<tr class="product" data-id=${vo.id }>
						<td>${vo.p_name }</td>
						<td>${vo.p_oprice }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</section>
</body>
</html>