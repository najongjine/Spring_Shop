<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		if(${MODE=='water'}){
		$("#userSearch").click(function() {
			var formData=$(".userSearch form").serialize()
			$.ajax({
					url : "${rootPath}/fishUserWater/findAndShow",
					data:formData,
					method : "GET",
					success : function(result) {
						$(".userList").html("")
						$(".userList").html(result)
					}
				})
		})
		
		$(".page-item").click(function() {
			var pageno=$(this).data("page")
			$.ajax({
					url : "${rootPath}/fishUserWater/findAndShow",
					data:{pageno:pageno},
					method : "GET",
					success : function(result) {
						$(".userList").html("")
						$(".userList").html(result)
					}
				})
		})
		
		$(".userListTitle").click(function() {
			var searchOption="";
			$.ajax({
				url : "${rootPath}/fishUserWater/findAndShow",
				data:{searchOption:searchOption},
				method : "GET",
				success : function(result) {
					$(".userList").html("")
					$(".userList").html(result)
				}
			})
		})
		}// 민물 낚시용 데이터 처리
	})
</script>

<body>
	<h4 class="userListTitle">User Fishing Spot</h4>
	<br />
	<section class="userSearch">
		<form method="get" action="${rootPath }/fishUserWater/findAndShow">
			<label for="searchOption">검색</label> <select name="searchOption">
				<option value="titleSearch">제목으로 검색</option>
			</select> <input name="inputStr">
			<button type="button" id="userSearch">검색</button>
		</form>
		<br />

	</section class="userList">
	<section>
		<c:forEach items="${userList }" var="vo" varStatus="i">
			<div class="container">
				<h2>${vo.uf_title}</h2>
				<div class="card" style="width: 400px">
					<img class="card-img-top" src="${rootPath }/files/${vo.mainPic }"
						alt="Card image" style="width: 100%">
					<div class="card-body">
						<h4 class="card-uf_title">${vo.uf_title }</h4>
						<a href="${rootPath }/fishUserWater/view?uf_id=${vo.uf_id}"
							class="btn btn-primary">Detail</a>
					</div>
				</div>
				<br>
			</div>
		</c:forEach>
	</section>
	<section>
	<c:if test="${MODE=='water' }">
	<a href="${rootPath }/fishUserWater/waterInsert">
	<button>insert new water fishing spot</button>
	</a>
	</c:if>
	<c:if test="${MODE=='sea' }">
	<a href="${rootPath }/fishUserSea/seaInsert">
	<button>insert new sea fishing spot</button>
	</a>
	</c:if>
	</section>
	<section class="pagi">
		<%@ include file="/WEB-INF/views/include/userListPagi.jsp" %>
	</section>
	
</body>
</html>