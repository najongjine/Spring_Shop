<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
input{
margin-top:2vh;
margin-bottom: 2vh;
}
button{
margin-left: 1vw;
margin-right:1vw;
margin-top:2vh;
margin-bottom: 2vh;
}
.message{
color: green;
font-weight: bold;
font-size: 0.3rem;
}
</style>
<script type="text/javascript">
$(function() {
	$(document).on("click","#btn-join",function(){
		let username=$("#username")
		let password=$("#password")
		let re_password=$("#re_password")
		
		if(username.val()==""){
			alert("아이디를 입력하세요")
			username.focus()
			return false
		}
		if(password.val()==""){
			alert("비밀번호를 입력하세요")
			password.focus()
			return false
		}
		if(re_password.val()==""){
			alert("비밀번호 재입력을 입력하세요")
			re_password.focus()
			return false
		}
		if(password.val()!=re_password.val()){
			alert("비밀번호와 비밀번호 확인이 다릅니다")
			password.focus()
			return false
		}
		
		$("form").submit()
	})
	$(document).on("blur","#username",function(){//입력박스에서 포커스가 벗어났을때
		let username=$(this).val()
		if(username==""){
			$("#m_username").text("아이디는 반드시 입력해야 합니다")
			return false
		}
		$.ajax({
			url:"${rootPath}/user/idcheck",
			method:"GET",
			data:{username:username},
			success:function(result){
				if(result=="USE"){
					$("#m_username").text("이미 가입된 사용자 이름입니다")
					$("#m_username").css("color","red")
					return false
				}
			},
			error:function(){
				//alert("통신오류")
			}
		})
	})
})
</script>
<body>
<form:form action="${rootPath }/user/join" method="post" class="form-group container">
<h2 class="jumbotron">회원가입</h2>
<!--  name="${_csrf.parameterName }" value="${_csrf.token }">  -->
<input id="username" name="username" placeholder="User ID" class="form-control">
<p class="message" id="m_username"></p>

<input type="password" id="password" name="password" placeholder="password" class="form-control">
<input type="password" id="re_password" name="re_password" placeholder="password" class="form-control">
<button class="btn btn-outline-primary" id="btn-join" type="button">회원가입</button>
<button class="btn btn-outline-success" id="btn-loss" type="button">id/pass 찾기</button>
</form:form>
</body>
</html>