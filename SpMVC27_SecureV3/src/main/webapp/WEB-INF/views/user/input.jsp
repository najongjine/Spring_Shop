<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
.serif {
  font-family: "Times New Roman", Times, serif;
}
section{
margin-top:5%;
margin-bottom:5%;
}
</style>
<style>
input{
margin-top:2vh;
margin-bottom: 2vh;
}
button{
margin-left: 1vw;
margin-right:1vw;
margin-top:2vh;
margin-bottom: 2vh;
}
.message{
color: green;
font-weight: bold;
font-size: 0.3rem;
}
</style>
<script type="text/javascript">
$(function() {
	
})
</script>
<body>
<form:form method="post" class="form-group container">
<h2 class="jumbotron">회원 정보 수정</h2>
<!--  name="${_csrf.parameterName }" value="${_csrf.token }">  -->
<p class="serif"  style="font-size: 30px;">유저 name: ${userVO.username }</p>
<input id="email" name="email" placeholder="email" class="form-control" value="${userVO.email }">
<input id="phone" name="phone" placeholder="phone" class="form-control"  value="${userVO.phone }">
<input id="address" name="address" placeholder="address" class="form-control"  value="${userVO.address }">

<button class="btn btn-outline-primary" id="btn-join">저장</button>
</form:form>
</body>
</html>