<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp"%>
<title>insert</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jsp"%>
	<section>
		<form method="post" enctype="multipart/form-data">
			<p>
				<input name="uf_id" type="hidden">
			</p>
			<p>
				<b>title:</b> <input name="uf_title">
			</p>
			<p>
				<b>content:</b> <input name="uf_title">
			</p>
			<p>
				<b>pictures:</b> <input type="file" multiple="multiple" name="uploaded_files[]">
			</p>
			<button>submit</button>
		</form>
	</section>
</body>
</html>