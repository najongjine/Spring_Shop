<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<section>
<form:form modelAttribute="studentVO" method="post">
	<form:input path="st_num" readonly="readonly"/>
	<label>이름: </label><form:input path="st_name"/>
	<label>학년: </label><form:input path="st_class"/>
	<label>반: </label><form:input path="st_group"/>
	<form:button>저장</form:button>
</form:form>
</section>
</body>
</html>