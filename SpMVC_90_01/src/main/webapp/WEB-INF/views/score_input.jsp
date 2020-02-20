<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>□□□ My JSP Page □□□</title>
</head>
<body>
<h3>학생점수</h3>
<form action="${rootPath }/number/score_input" method="post">
	<input name="kor" type="number" placeholder="국어점수" 
	value="<c:out value="${scoreVO.kor }" default="0"/>">
	<input name="eng" type="number" placeholder=" 영어점수" 
	value="<c:out value="${scoreVO.eng }" default="0"/>">
	<input name="math" type="number" placeholder="수학점수" 
	value="<c:out value="${scoreVO.math }" default="0"/>">
	<input name="sci" type="number" placeholder="과학점수" 
	value="<c:out value="${scoreVO.sci }" default="0"/>">
	<input name="music" type="number" placeholder="음악점수" 
	value="<c:out value="${scoreVO.music }" default="0"/>">
	<button>계산</button>
</form>
<div><b>총점</b>:${scoreVO.sum }</div>
<div><b>평균</b>:${scoreVO.avg }</div>
</body>
</html>