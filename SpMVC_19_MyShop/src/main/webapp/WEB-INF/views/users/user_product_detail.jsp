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
<title>□□□ My JSP Page □□□</title>
</head>
<body>
<section class="container">
<p><a href="${rootPath }/">
홈으로
</a></p>
</section>

<section class="container card">
<p>
	<input name="p_code" placeholder="" type="hidden">
</p>

<p>
	<label class="card-header">상품이름: </label>${prodVO.p_name}
</p>

<p>
	<label class="card-header">상품코드: </label>${prodVO.p_pcode}
</p>

<p>
	<label class="card-header">거래처코드: </label>${prodVO.p_dcode}
</p>

<p>
	<label class="card-header">판매가격: </label>${prodVO.p_oprice}
</p>

<p>
	<label class="card-header">설명: </label>${prodVO.p_detail}
</p>

<button class="btn btn-primary" type="button">상품을 바구니에 담기</button>
</section>
</body>
</html>