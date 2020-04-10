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
<form:form modelAttribute="scoreVO" method="post">
	<form:input path="s_id" readonly="readonly"/>
	<label>학번: </label><form:input path="s_num"/>
	<label>과목: </label><form:input path="s_subject"/>
	<label>점수: </label><form:input path="s_score"/>
	<form:button>저장</form:button>
</form:form>
</section>
</body>
</html>