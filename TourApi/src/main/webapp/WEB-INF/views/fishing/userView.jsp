<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp"%>
<title>insert</title>
<script type="text/javascript">
$(function() {
	var ufc_fk=${userVO.uf_id}
	$.ajax({
		url:"${rootPath}/fishUserWater/comments",
		data:{ufc_fk:ufc_fk},
		type:"GET",
		success:function(result){
			$(".comments").html("")
			$(".comments").html(result)
		}
	})
	
	//원글에서 댓글쓴거 보내기
	$(document).on("click","#commentSubmit",function(){
		var formData=$("form#pcomment").serialize()
		$.ajax({
			url:"${rootPath}/fishUserWater/comments",
			data:formData,
			type:"POST",
			success:function(result){
				$(".comments").html("")
				$(".comments").html(result)
			}
		})
	})
	
	//댓글의 댓글에서 쓴거 보내기
	$(document).on("click","#btnReplyReply",function(){
		var formData=$("form.replyreply").serialize()
		$.ajax({
			url:"${rootPath}/fishUserWater/comments",
			data:formData,
			type:"POST",
			success:function(result){
				$(".comments").html("")
				$(".comments").html(result)
			}
		})
	})
	$(document).on("click","#btn-delete",function(){
		if(${U_NAME!=userVO.uf_username}){
			return false
		}
		if(${MODE=='water'}){
			document.location.replace("${rootPath}/fishUserWater/delete?strId=${userVO.uf_id}")
		}
		else if(${MODE=='sea'}){
			document.location.replace("${rootPath}/fishUserSea/delete?strId=${userVO.uf_id}")
		}
	})
	$(document).on("click",".btn-update",function(){
		if(${U_NAME!=userVO.uf_username}){
			return false
		}
		if(${MODE=='water'}){
			document.location.replace("${rootPath}/fishUserWater/waterUpdate?strId=${userVO.uf_id}")
		}
		else if(${MODE=='sea'}){
			document.location.replace("${rootPath}/fishUserSea/seaUpdate?strId=${userVO.uf_id}")
		}
	})
	
})
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jsp"%>
	<section>
			<p>
				<b>uf_username:</b> ${userVO.uf_username }
			</p>
			<p>
				<b>date:</b> ${userVO.uf_date }
			</p>
			<p>
				<b>uf_addr1:</b> ${userVO.uf_addr1 }
			</p>
			<p>
				<b>uf_addr2:</b> ${userVO.uf_addr2 }
			</p>
			<p>
				<b>content:</b> ${userVO.uf_text }
			</p>
			
		<section class="pics">
		<br/>
		<hr/>
			<c:choose>
				<c:when test="${picsList!=null }">
					<c:forEach items="${picsList}" var="vo">
						<p>
						<img src="${rootPath }/files/${vo.ufp_uploadedFName }">
						</p>
					</c:forEach>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</section>
		<button class="btn-update" type="button">modify</button>
		<button id="btn-delete" type="button">delete</button>
	</section>
	<section class="commentInput">
	<br/>
	<hr/>
	<form method="post" id="pcomment">
	<p>
		<input name="ufc_fk" value="${userVO.uf_id }" type="hidden">
	</p>
	<p>
		<b>comment: </b>
		<textarea  class="summernote" name="ufc_text" id="ufc_text" rows="" cols=""></textarea>
	</p>
	<p>
		<button id="commentSubmit" type="button">submit comment</button>
	</p>
	</form>
	</section>
	<section class="comments">
		
	</section>
</body>
</html>