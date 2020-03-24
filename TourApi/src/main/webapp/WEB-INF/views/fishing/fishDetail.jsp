<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp"%>
<title>Detail</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jsp"%>
	<section>
	<c:if test="${fishVO.booktour!=null}">
		<p>Introduced in Text book :${fishVO.booktour}</p>
	</c:if>
	<c:if test="${fishVO.homepage!=null}">
		<p>Home Page: ${fishVO.homepage}</p>
	</c:if>
	<c:if test="${fishVO.tel!=null}">
		<p>tel: ${fishVO.tel}</p>
	</c:if>
	<c:if test="${fishVO.telname!=null}">
		<p>tel name: ${fishVO.telname}</p>
	</c:if>
	
	<p>title: ${fishVO.title}</p>
	<p><img src="${fishVO.firstimage}" ></p>
	<p><img src="${fishVO.firstimage2}"></p>
	<p>area code: ${fishVO.areacode}</p>
	<p>city code: ${fishVO.sigungucode}</p>
	<p>Addr 1: ${fishVO.addr1}</p>
	<p>Addr 2: ${fishVO.addr2}</p>
	<p>zipcode: ${fishVO.zipcode}</p>
	<p>mapX: ${fishVO.mapx}</p>
	<p>mapY: ${fishVO.mapy}</p>
	<p>mlevel: ${fishVO.mlevel}</p>
	<p>description : ${fishVO.overview}</p>
	</section>
</body>
</html>