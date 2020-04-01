<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>□□□ My JSP Page □□□</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function() {
	$(".userId").on("click",function(){
		$.ajax({
			url:"sendUserId",
			method:"POST",
			data:$("form").serialize(),
			success:function(result){
				alert(result.RET_CODE)
				if(result.RET_CODE=="RECV_OK"){
					let user=result.userVO.userId+"\n"
					user+=result.userVO.password+"\n"
					user+=result.userVO.userName+"\n"
					user+=result.userVO.rolle+"\n"
					alert("사용자 ID: "+user)
				}
			}
		})
	})
	$("button.user").click(function(){
		$.ajax({
			url:"sendUser",
			method:"POST",
			data:$("form").serialize(),//form data를 통채로 json으로 변경
			success:function(userVO){
				//json 객체 형태의 데이터는 alert로 확인하면
				//[object,object] 형식으로만 확인이 된다.
				//이 객체를 toString() 하는것처럼 문자열로 풀으려면
				// JSON.stringify(userVO)
				let html="<p>"+userVO.userId+"</p>"
				html+="<p>"+userVO.password+"</p>"
				html+="<p>"+userVO.userName+"</p>"
				html+="<p>"+userVO.rolle+"</p>"
				$("#ret_html").html(html)
			}
		})
	})
	$("button.user_html").click(function(){
		$.ajax({
			url:"html",
			data:$("form").serialize(),
			success:function(result){
				$("#ret_html").html(result)
			}
		})
	})
})
</script>
</head>
<body>
<section>
<h2>사용자정보</h2>
<h5>사용자ID: ${userVO.userId }</h5>
<h5>비밀번호: ${userVO.password}</h5>
<h5>사용자 이름: ${userVO.userName }</h5>
<h5>사용자 권한: ${userVO.rolle }</h5>
</section>

<section id="ret_html">

</section>

<section>
<form action="saveUser" method="post">
<div>
<input placeholder="사용자 id" name="userId" id="userId">
</div>
<div>
<input placeholder="password" name="password" id="password">
</div>
<div>
<input placeholder="사용자 이름" name="userName" id="userName">
</div>
<div>
<input placeholder="사용자 권한" name="rolle" id="rolle">
</div>
<button>save</button>
<button type="button" class="userId">사용자 id 전송</button>
<button type="button" class="user">입력값 전송</button>
<button type="button" class="user_html">입력값 HTML로 받기</button>
</form>
</section>
</body>
</html>