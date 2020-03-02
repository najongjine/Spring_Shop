<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<%@ include file="/WEB-INF/views/include/include-head.jsp"%>
	<script type="text/javascript">
	$(function() {
		//$(".cmt-item").click(function() {
		$(document).on("click",".cmt-item",function(){
			let id=$(this).data("id")
			let writer=$(this).find("div.writer").text()
			let subject=$(this).find("div.subject").text()
			$("#c_id").val(id)
			$("#c_writer").val(writer)
			$("#c_subject").val(subject)
			return false
		})
		$(document).on("click",".cmt-item-del",function(event){
			if(!confirm("커멘트를 삭제할까요?")){
				return false;
			}
		
			//event.stopPropagation()
			/*
			현재 자신을 감싸고 있는 가장 가까운 div을 찾아라
			*/
			let c_id=$(this).parent("div").data("id")
			$.ajax({
				url:"${rootPath}/comment/delete",
				data:{
					c_id:c_id,
					b_id:"${BBS.b_id}"
				},
				
				type:"POST",
				success:function(result){
					$(".cmt-list").html(result)
				},
				error:function(){
					alert("서버 통신 오류")
				}
			})
			
			return false
		})//
		
		$(document).on("click",".cmt-item-reply",function(event){
			event.stopPropagation()
			let b_id="${BBS.b_id}"
			let c_id=$(this).parent("div").data("id")
			let data={c_b_id:b_id, c_p_id:c_id}
			$.get("${rootPath}/comment/reply",
					data,
					function(result){
				$(".modal-body").html(result)
				$(".modal-main").css("display","block")
			})
		})
		
		
		$(document).on("click","button",function() {
			let txt=$(this).text()
			if(txt=='수정'){
				document.location.href="${rootPath}/update?b_id=${BBS.b_id}"
			}else if(txt=='삭제'){
				let b_id=${BBS.b_id}
					if(confirm("게시글을 삭제할까요?")){
						$.post("${rootPath}/delete",
								{b_id:b_id},
								function(result){
									if(parseInt(result)>0){
										alert("삭제성공")
										document.location.replace("${rootPath}/list")
									} else{
										alert("삭제 실패")
									}
								})
						return false
					}
					
			}
			else if(txt=='답변저장'){
				var formData=$("form.reply").serialize()
				$.ajax({
					url:"${rootPath}/comment/insert",
					data:formData,
					type:"POST",
					success:function(result){
						$(".modal-main").css("display","none")
						$(".cmt-list").html("")
						$(".cmt-list").html(result)
					},
					error: function() {
						alert("서버와 통신 오류")
					}
				})
				return false
			}
			else if(txt=='답글달기'){
				/*
				ajax를 사용해서 form에 담긴 데이터를 controller로 전송
				.serialize()함수를 사용해서 form에 담긴 데이터를 문자열화하고, query 형식으로 변경하고
				ajax의 data에 담아서 전송.
				*/
				var formData=$("form.main").serialize()
				$.ajax({
					url:"${rootPath}/comment/insert",
					data:formData,
					type:"POST",
					success:function(result){
						$(".cmt-list").html("")
						$(".cmt-list").html(result)
					},
					error: function() {
						alert("서버와 통신 오류")
					}
				})
				return false
			}
			else if(txt=='답글'){
				document.location.href="${rootPath}/reply?b_id=${BBS.b_id}"
				return false
			}
			else{
				document.location.href="${rootPath}/list"
			}
		})
		
	})
	</script>
	
<title>detail view</title>

</head>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jsp"%>
<section class="contrainer-fluid">
<input name="b_id" value="${BBS.b_id }" type="hidden">
<input name="b_p_id" value="${BBS.b_p_id }" type="hidden">
<p>
작성자:${BBS.b_writer }
</p>

<p>
작성시각:${BBS.b_date_time }
</p>

<p>
제목:${BBS.b_subject }
</p>

<p>
내용:${BBS.b_content }
</p>

<p>
파일:${BBS.b_file }
</p>

<hr/>

<p  class="form-group d-flex justify-content-end">
<a href="${rootPath }/update?b_id=${BBS.b_id}">
<button class="btn btn-primary mr-3">수정</button>
</a>
<button type="button" class="btn btn-danger mr-3">삭제</button>
<button type="button" class="btn btn-info mr-3">답글</button>
<a href="${rootPath }/list"><button class="btn btn-success">목록으로</button></a>
</p>
</section>
<hr/>

<section class="container-fluid p-4 cmt-list">
<%@ include file="/WEB-INF/views/comment_list.jsp" %>
</section>
<hr/>

<section class="contrainer-fluid">
<form class="main" method="post">
<input name="c_id" id="c_id" type="hidden" value="0">
<input name="c_b_id" type="hidden" value="${BBS.b_id }">
<input name="c_writer" id="c_writer" placeholder="작성자" class="form-control">
<input name="c_subject" id="c_subject" placeholder="내용" class="form-control">
<button type="button" class="btn float-right btn-cmt-save">답글달기</button>
</form>
</section>

<style>
div.modal-main{
position: fixed;
top:0;
left:0;
width: 100%;
height: 100%;
overflow: auto;
background-color: rgba(0,0,0.4);
z-index: 10;
display: none;
}
div.modal-content{
position:relative;
margin: auto;
width:80%;
top:300px;
padding: 0;
}
div.modal-header{
display: flex;
justify-content: flex-end;
}
span.modal-close{
cursor: pointer;
float: right;
font-size: 30px;
font-weight: bold;
color: black;
}
span.modal-close:hover,span.modal-close:focus{
color: #000;
}
</style>
<script>
$(function() {
	$(".modal-close").click(function() {
		$(".modal-main").css("display","none")
	})
})
</script>
<div class="modal-main">
	<div class="modal-content">
		<div class="modal-header">
			<span class="modal-close">&times;</span>
		</div>
		<div class="modal-body">
		</div>
	</div>
</div>
</body>
</html>