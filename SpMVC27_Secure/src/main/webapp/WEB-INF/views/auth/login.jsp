<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
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
</style>
<script type="text/javascript">
$(function() {
	$(document).on("click","button.join",function(){
		document.location.href="${rootPath}/user/join"
	})
})
</script>
<body>
<form action="${rootPath }/user/login" method="post" class="form-group container">
<h2 class="jumbotron">로그인</h2>
<input id="username" name="username" placeholder="User ID" class="form-control">
<input type="password" id="password" name="password" placeholder="password" class="form-control">
<button class="btn btn-outline-primary">login</button>
<button class="btn btn-outline-success join" type="button">회원가입</button>
</form>
</body>
</html>