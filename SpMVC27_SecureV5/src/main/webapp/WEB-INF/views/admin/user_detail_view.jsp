<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
input {
	margin-top: 2vh;
	margin-bottom: 2vh;
}

button {
	margin-left: 1vw;
	margin-right: 1vw;
	margin-top: 2vh;
	margin-bottom: 2vh;
}

.message {
	color: green;
	font-weight: bold;
	font-size: 0.3rem;
}

form p.password {
	display: none;
}
form input.auth{
display: block;
}

</style>
<script type="text/javascript">
	$(function() {
		
	})
</script>
<section>
	<form:form modelAttribute="userVO" class="form-group container">
		<h2 class="jumbotron">${userVO.username } 회원 정보 수정</h2>
		<form:input path="username" placeholder="username" class="form-control" type="hidden" readonly="readonly"/>
		
		<p>
		<label for="email">Email: </label>
		<form:input path="email" placeholder="email" class="form-control" />
		</p>
		
		<p>
		<label for="phone">phone: </label>
		<form:input path="phone" placeholder="phone" class="form-control" />
		</p>
		
		<p>
		<label for="address">address: </label>
		<form:input path="address" placeholder="address" class="form-control" />
		</p>
		
		<p>
		<label for="enabled">계정활성화: </label>
		<form:checkbox path="enabled" class="form-control" />
		</p>
		
		<div id="auth_box">
		<button id="auth_append" type="button">권한 정보 입력 추가</button>
		<c:if test="${not empty userVO.authorities }">
			<c:forEach items="${userVO.authorities }" var="auth">
				<input name="auth" value="${auth.authority }" class="auth">
			</c:forEach>
		</c:if>
		</div>

		<p><button class="btn btn-outline-primary" id="btn-save" type="button">저장</button></p>
	</form:form>
</section>