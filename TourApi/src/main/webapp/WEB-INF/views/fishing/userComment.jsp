<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />

<br/>
<hr/>
<c:choose>
	<c:when test="${commentList!=null}">
		<c:forEach items="${commentList}" var="vo">
		<p>
		id-${vo.ufc_id }, pid-${vo.ufc_pid } usr-${vo.ufc_username } : text-${vo.ufc_text }
		</p>
		</c:forEach>
	</c:when>
</c:choose>