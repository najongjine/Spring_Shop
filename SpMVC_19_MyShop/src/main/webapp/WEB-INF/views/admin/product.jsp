<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
	<article class="col-md-7 col-sm-12 bg-light pro-input">
		<form:form action="${rootPath }/admin/product/input" modelAttribute="productVO">
			<div class="container-fluid form-group row">
				<form:select path="p_pcode" class="custom-select-sm col-6">
					<form:option value="0">품목을 선택해세요</form:option>
					<form:option value="B0001">공산품</form:option>
					<form:option value="B0002">농산물</form:option>
					<form:option value="B0003">수산물</form:option>
				</form:select>
				
				<form:select path="p_dcode" class="custom-select-sm col-6">
					<form:option value="0">거래처를 선택해세요</form:option>
					<form:option value="D0001">대덕물산</form:option>
					<form:option value="D0002">삼성농산</form:option>
					<form:option value="D0003">목포수산</form:option>
				</form:select>
			</div>
			<div class="container-fluid row">
			<form:errors path="p_pcode" class="in-errors col-6"/>
			<form:errors path="p_dcode" class="in-errors col-6"/>
			</div>
			
			<div class="form-group">
				<form:input path="p_code" class="form-control" placeholder="상품코드"/>
				<form:errors path="p_code" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:input path="p_name" class="form-control" placeholder="상품이름"/>
				<form:errors path="p_name" class="in-errors"/>
			</div>
			<div class="container-fluid form-group row">
				<form:input path="p_iprice" class="form-control col-6" placeholder="매입단가"/>
				<form:errors path="p_iprice" class="in-errors"/>
				<form:input path="p_oprice" class="form-control col-6" placeholder="판매단가"/>
				<form:errors path="p_oprice" class="in-errors"/>
			</div>
			<div class="form-group">
				<form:textarea path="p_detail" rows="" cols="" placeholder="상세정보"/>
			</div>
			
			<div class="form-group">
				<button>저장</button>
			</div>
		</form:form>
	</article>
	<article class="col-md-4 col-sm-12 bg-light pro-list">
		<%@ include file="/WEB-INF/views/admin/product_list.jsp" %>
	</article>
</section>