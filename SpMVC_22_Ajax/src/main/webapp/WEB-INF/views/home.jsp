<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>□□□ My JSP Page □□□</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
section{
border:1px solid blue;
padding:1rem;
}
</style>
<script type="text/javascript">
$(function() {
	//jq 의 event 핸들러를 작성할때
	//$("#call_ajax").click() 이라고 작성을 하는데 ajax를 사용하여 서버로부터 나중에 가져온 페이지는
	//해당 이벤트 핸들러로 작동이 안된다.
	$(document).on("click","#call_ajax",function(){
		$.ajax({
			url:"${rootPath}/ajax",
			method:"GET",
			success:function(result){
				if(result=="OK"){
					alert("서버에서 ok를 보냄")
				}
			},
			error:function(){
				alert("서버 통신 오류")
			}
		})
		
	})
})
</script>
<script type="text/javascript">
var rootPath="${rootPath}"
</script>
<script type="text/javascript" src="${rootPath }/resources/js/ajax.js"></script>
</head>
<body>
<section id="main">
<button id="call_ajax">Ajax 호출</button>
<input id="msg">
<button id="call_msg">msg 호출</button>
<button id="call_addr">addr 호출</button>
<button id="call_addr_view">addr view 호출</button>
</section>
<section id="sub">
</section>
</body>
</html>