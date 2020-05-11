<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
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

<body>
<%@ include file="/WEB-INF/views/include/nav.jspf" %>
<section>
<br/>
<p class="serif" style="font-size: 30px;">유저명: ${userVO.username }</p>
<p class="serif">활성화 상태: ${userVO.enabled }</p>
<br/>
<c:forEach items="${userVO.authorities }" var="vo">
<p class="serif">권한: ${vo.authority }</p>
</c:forEach>
<br/>
<p class="serif">유저 Email: ${userVO.email }</p>
<p class="serif">전화번호: ${userVO.phone }</p>
<p class="serif">주소: ${userVO.address }</p>
</section>

<section>
<br/>
<a href="${rootPath }/user/update?username=${userVO.username}">
<button class="btn btn-primary" type="button">수정</button>
</a>
</section>
</body>
</html>