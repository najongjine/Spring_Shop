<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>□□□ My JSP Page □□□</title>
<style type="text/css">
.from, .to{
padding:5px;
}
.userName{
color: blue;
font-weight: bold;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
var socket = new SockJS('http://localhost:8080/socket/chat');
//소켓 서버에 접속 시작
socket.onopen = function() {
	// 소켓이 서버에 접속이 성공한 다음 최초에 실행될 코드
	let userName=prompt("채팅방 입장!! 이름을 입력하세요")
	if(userName && userName!=""){
		socket.send("userName:"+userName)
	}
	// socket.send('hello');
};
//서버로부터 수신되는 이벤트
socket.onmessage = function(e) {
    //console.log('message', e.data);
    
    //문자열형으로 수신된 json 문자열을 json 객체로 변환
    console.log(e.data)
    let mJson=JSON.parse(e.data);
    console.log(mJson)
    
    //msg 라는 key 있냐?
    if(mJson.msg&&mJson.msg=='userName'){
    	$("#userName").val(mJson.userName)
    	$("#userName").prop("readonly",true)
    	return false
    }
    if(mJson.msg && mJson.msg=="userList"){
    	let userList=JSON.parse(mJson.userList)
    	
    	//동적 tag 만드는 jq 코드
    	let options=$("<option/>",{value:all,text:"전체"}
    	)
    	$("#toList").append(options)
    	for(let i=0;i<userList.length;i++){
    		options.append($("<option/>",{value:userList[i].userName,text:userList[i].userName})
        	)
    	}
    }
    let html="<div class='from text-right'>"
    html+="<span class='userName'>"
    html+=mJson.userName
    html+=":</span>"
    html+=mJson.message
    html+="</div>"
    $("#message_list").append(html)
    //sock.close();
};
//소켓 서버와 접속 종료
/* socket.onclose = function() {
    console.log('close');
}; */
</script>
<script type="text/javascript">
$(function() {
	$(document).on("submit","form",function(event){
		event.preventDefault();
	})
	
	$(document).on("click","#btn_send",function(){
		let userName=$("#userName").val()
		if(userName==""){
			alert("보내는 사람 이름을입력 하세요")
			return false
		}
		//userName과 message를 묶어서 JSON 데이터로 생성
		let message={
			userName:userName,
			message:$("#message").val()
		}
		let html="<div class='to'>"
		html+="<span class='userName'>"
		html+="나 : "
		html+="</span>"
		html+=message.message
		html+="</div>"
		$("#message_list").append(html)
		//socket을 통해 json 데이터를 보내기 위해 json형 문자열로 변환시킨 후 전송
		socket.send(JSON.stringify(message))
		$("#message").val("")
	})
	socket.send("getUserList")
})
</script>
</head>
<body>
<header class="jumbotron bg-success">
<h2 class="text-white text-center">My Chat Service</h2>
</header>

<section class="container-fluid">
<form>
<div class="form-group">
<label for="userName">FROM</label>
<input id="userName" class="form-control" placeholder="보내는 사람"><br/>
</div>

<div class="form-group">
<label for="toList">받는사람</label>
<section id="toList" class="form-control">
<option value="all">전체</option>
</section>
</div>

<div class="form-group">
<label for="message">메시지</label>
<input id="message"  class="form-control" placeholder="메시지 입력">
</div>
<button id="btn_send" class="btn btn-primary">전송</button>
</form>
</section>

<section class="container-fluid">
<div id="user_list" class="col-3">
</div>

<div id="message_list" class="col-8">
</div>

</section>
</body>
</html>