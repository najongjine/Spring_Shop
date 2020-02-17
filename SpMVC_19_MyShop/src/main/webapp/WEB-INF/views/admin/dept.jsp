<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<style>
.in-errors{
color: red;
font-size: 8px;
}
tr,td,th{
white-space:nowrap; 
}
.pro-list{
overflow: auto;
}
td.p_name{
display:inline-block;
width: 100px;
padding:0 5px;
overflow: hidden;
text-overflow: ellipsis;
}
/*
col-md-7 col-12 해상도가 768보다 크면 7칸만 차지하고
그 이하면 12칸을 차지하여 full width로 보여라
*/
</style>
<section class="container-fluid row">
	<article class="col-md-7 col-sm-12 bg-light dept-input">
		<form:form action="${rootPath }/admin/dept/input" modelAttribute="deptVO">
			<div class="form-group">
				<form:input path="d_code" class="form-control" placeholder="거래처 코드"/>
				<form:errors path="d_code" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:input path="d_name" class="form-control" placeholder="거래처이름"/>
				<form:errors path="d_name" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:input path="d_ceo" class="form-control" placeholder="CEO"/>
				<form:errors path="d_ceo" class="in-errors"/>
			</div>
			<div class="container-fluid form-group row">
				<form:input path="d_sid" class="form-control col-6" placeholder="사업자번호"/>
				<form:errors path="d_sid" class="in-errors"/>
				<form:input path="d_tel" class="form-control col-6" placeholder="전화번호"/>
				<form:errors path="d_tel" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:input path="d_addr" class="form-control" placeholder="주소"/>
				<form:errors path="d_addr" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:input path="d_manager" class="form-control" placeholder="메니저"/>
				<form:errors path="d_manager" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:textarea path="d_mtel" rows="" cols="" placeholder="핸드폰번호"/>
			</div>
			<div class="form-group">
				<form:textarea path="d_rem" rows="" cols="" placeholder="비고"/>
			</div>
			
			<div class="form-group">
				<button>저장</button>
			</div>
		</form:form>
	</article>
</section>