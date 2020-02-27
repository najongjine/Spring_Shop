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
		$("button").click(function() {
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
			else if(txt=='저장'){
				return false
			}
			else{
				document.location.href="${rootPath}/list"
			}
		})
		
		/* 내가만든거
		let b_id=${BBS.b_id}
		$(".btn-danger").click(function() {
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
			
		}) */
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
<a href="${rootPath }/list"><button class="btn btn-success">목록으로</button></a>
</p>
</section>
<hr/>

<section class="contrainer-fluid">
<form>
<input name="b_writer" placeholder="작성자" class="form-control">
<input name="b_content" placeholder="내용" class="form-control">
<button class="float-right">답글달기</button>
</form>
</section>
</body>
</html>