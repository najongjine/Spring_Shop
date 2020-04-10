<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<section>
<form>
<label for="mode">학생 리스트 기준:</label>

<select name="mode" id="mode">
  <option value="normal">노멀모드</option>
  <option value="rank">석차모드</option>
</select>
<button>목록다시 보여주기</button>
</form>
</section>

<section>
<table>
<tr>
	<th>학번</th>
	<th>이름</th>
	<th>학년</th>
	<th>반</th>
</tr>

	<c:forEach items="${studentList }" var="vo">
	<tr>
	<td>${vo.st_num }</td>
	<td><a href="${rootPath }/student/detail?st_num=${vo.st_num}">${vo.st_name }</a></td>
	<td>${vo.st_class }</td>
	<td>${vo.st_group }</td>
	</tr>
	</c:forEach>
</table>
</section>

<section>
<a href="${rootPath }/student/insert">
<button type="button">학생추가</button>
</a>
</section>
</body>
</html>