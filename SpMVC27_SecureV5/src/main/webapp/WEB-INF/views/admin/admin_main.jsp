<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
.serif {
	font-family: "Times New Roman", Times, serif;
}

section {
	margin: 5%;
	margin-bottom: 5%;
	width:70%;
}
#body{
position:fixed;
top:30px;
left:0;
display: flex;
}
#body menu{
flex:1;
border:1px solid blue;
margin:5px;
}
#body menu li{
list-style: none;
}
#body menu li a{
display: inline-block;
padding: 5px 10px;
text-decoration: none;
width: 150px;
margin-left: 10px;
}
#body menu li a:hover{
border-bottom: 2px solid yellow;
transition: ease 0.3s;
}
#body article{
flex:3;
border:1px solid blue;
margin:5px;
}

body,form,section #admin_content{
overflow: auto;
height: 90%;
}
</style>
<script>
$(function() {
	$(document).on("click","#user_list",function(){
		$.get("${rootPath}/admin/user_list",function(result){
			$("#admin_content").html("")
			$("#admin_content").html(result)
		})
	})
	$(document).on("click","tr.tr_user",function(){
		let username=$(this).data("id")
		$.get("${rootPath}/admin/user_detail_view/"+username,
				function(result){
			$("#admin_content").html("")
			$("#admin_content").html(result)
		})
	})
	$(document).on("click","#btn-save",function(){
		let formdata=$("form").serialize()
		$.post("${rootPath}/admin/user_detail_view",formdata,function(result){
			$("#admin_content").html("")
			$("#admin_content").html(result)
			alert("update 성공")
		})
	})
	$(document).on("click","#auth_append",function(){
			let auth_input=$("<input/>",{class:"auth1",name:"auth"} )
			auth_input.append($("<p/>",{text:"제거",class:"auth_delete"}))
			$("div#auth_box").append(auth_input)
		})
})
</script>
<body>
<%@ include file="/WEB-INF/views/include/nav.jspf" %>

<section id="body">
	<menu>
	<h3>관리자 페이지</h3>
	<ul>
		<li>
		<a href="javascript:void(0)" id="user_list">User List</a>
		</li>
		<li>
		<a href="#">menu1</a>
		</li>
		<li>
		<a href="#">menu2</a>
		</li>
	</ul>
	</menu>
	
	<article id="admin_content">
	</article>
</section>
</body>
</html>