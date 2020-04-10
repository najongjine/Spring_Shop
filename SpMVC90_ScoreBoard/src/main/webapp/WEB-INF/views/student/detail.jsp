<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script type="text/javascript">
$(function() {
	$(document).on("click","#delete",function(){
		if(!confirm("진짜 학생을 삭제할까요?")){
			return false
		}
		document.location.replace("${rootPath}/student/delete?st_num=${studentVO.st_num}")
	})
	$(document).on("click","#scoreDelete",function(){
		let scoreid=$(this).data("scoreid")
		if(!confirm("진짜 점수를 삭제할까요?")){
			return false
		}
		document.location.replace("${rootPath}/score/delete?s_id="+scoreid)
	})
})
</script>
</head>
<body>
<section>
<p>
<label>학번: </label><label>${studentVO.st_num }</label>
</p>

<p>
<label>이름: </label><label>${studentVO.st_name }</label>
</p>

<p>
<label>학년: </label><label>${studentVO.st_class }</label>
</p>

<p>
<label>반: </label><label>${studentVO.st_group }</label>
</p>
</section>

<section>
<table>
<tr>
	<th>점수id</th>
	<th>학번</th>
	<th>과목명</th>
	<th>점수</th>
</tr>
	<c:forEach items="${studentVO.scoreList }" var="vo">
	<tr>
	<td>${vo.s_id }</td>
	<td>${vo.s_num }</td>
	<td>${vo.s_subject }</td>
	<td>${vo.s_score }</td>
	<td><a href="${rootPath }/score/update?s_id=${vo.s_id}">수정</a></td>
	<td data-scoreid="${vo.s_id }" style="color:red;" id="scoreDelete">&nbsp;&nbsp;&nbsp;점수삭제</td>
	</tr>
	</c:forEach>
</table>
</section>

<section>
<p>
<label>총점: </label><label>${studentVO.totalScore }</label>
<label>평균: </label><label>${studentVO.averageScore }</label>
<label>석차: </label><label>${studentVO.rank }</label>
</p>
</section>

<section>
<a href="${rootPath }/student/update?st_num=${studentVO.st_num}">
<button type="button">학생수정</button>
</a>

<a href="${rootPath }/score/insert?s_num=${studentVO.st_num}">
<button type="button">점수추가</button>
</a>

<button type="button" id="delete">학생삭제</button>
</section>
</body>
</html>