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
				<b>title:</b> <input name="uf_title" value="${userVO.uf_title }">
			</p>
			<p>
				<b>addr1:</b> <input name="uf_addr1" value="${userVO.uf_addr1 }">
			</p>
			<p>
				<b>addr2:</b> <input name="uf_addr2" value="${userVO.uf_addr2 }">
			</p>
			<p>
				<b>content:</b> <textarea class="summernote" name="uf_text" value="${userVO.uf_text }"></textarea>
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
						<c:if test="${MODE=='water' }">
						<a href="${rootPath }/fishUserWater/deletePic?strUfp_id=${vo.ufp_id}&strFk=${vo.ufp_fk}">
						<button>deletePic</button>
						</a>
						</c:if>
						<c:if test="${MODE=='sea' }">
						<a href="${rootPath }/fishUserSea/deletePic?strUfp_id=${vo.ufp_id}&strFk=${vo.ufp_fk}">
						<button>deletePic</button>
						</a>
						</c:if>
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