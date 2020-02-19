<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<script>
$(function() {
	var toolbar=[
		['style',['bold','italic','underline']],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color',['color']],
		['para',['ul','ol','paragraph']],
		['height',['height']],
		['table',['table']],
		['insert',['link','hr','picture']],
		['view',['fullscreen','codeview']]
	]
	$("#p_detail").summernote({
		lang:'ko-KR',
		width:'100%',
		height:'500px',
		toolbar:toolbar,
		disableDragAndDrop:true
	})
})
</script>
<form:form action="${rootPath }/admin/product/input"
	modelAttribute="productVO">
	<div class="form-group">
		<form:textarea path="p_detail" rows="" cols="" placeholder="상세정보" />
	</div>
	<div class="form-group">
		<button>저장</button>
	</div>
</form:form>