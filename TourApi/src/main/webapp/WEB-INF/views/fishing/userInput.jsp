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
				<b>uf_username:</b> <input name="uf_username" value="${userVO.uf_username }" type="hidden">
			</p>
			<p>
				<b>date:</b> <input name="uf_date" value="${userVO.uf_date }" type="hidden">
			</p>
			<p>
				<b>uf_addr1:</b> <input name="uf_addr1" value="${userVO.uf_addr1 }">
			</p>
			<p>
				<b>uf_addr2:</b> <input name="uf_addr2" value="${userVO.uf_addr2 }">
			</p>
			<p>
				<b>content:</b> <input name="uf_text" value="${userVO.uf_text }">
			</p>
			<p>
				<b>pictures:</b> <input type="file" multiple="multiple" name="uploaded_files">
			</p>
			<button>submit</button>
		</form>
		<section>
		<br/>
		<hr/>
			<c:choose>
				<c:when test="${picsList!=null }">
					<c:forEach items="${picsList}" var="vo">
						<p>
						<img src="${rootPath }/files/${vo.ufp_uploadedFName }">
						<a href="${rootPath }/fishUser/deletePic?strUfp_id=${vo.ufp_id}&strFk=${vo.ufp_fk}">
						<button>deletePic</button>
						</a>
						</p>
					</c:forEach>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</section>
	</section>
</body>
</html>