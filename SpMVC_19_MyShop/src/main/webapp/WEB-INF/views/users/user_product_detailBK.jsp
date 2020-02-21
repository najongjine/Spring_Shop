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

<section class="container">
<article class="card">
<p >
	<input name="p_code" placeholder="" type="hidden">
</p>

<p>
	<img alt="#" src="#" width="50%" class="col-md-4">
	<label class="col-md-3">상품이름: </label><label class="col-md-4">${prodVO.p_name}</label>
</p>

<p>
	<label>상품코드: </label><label>${prodVO.p_pcode}</label>
</p>

<p>
	<label>거래처코드: </label><label>${prodVO.p_dcode}</label>
</p>

<p>
	<label>판매가격: </label><label>${prodVO.p_oprice}</label>
</p>

<p>
	<label>설명: </label><label>${prodVO.p_detail}</label>
</p>
</article>
<button class="btn btn-primary" type="button">상품을 바구니에 담기</button>
<button class="btn btn-primary" type="button">바로구매</button>
</section>
</body>
</html>