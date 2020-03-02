<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<form class="reply" method="post">
<input name="c_p_id" id="c_p_id" type="hidden" value="${CMT.c_p_id}">
<input name="c_b_id" type="hidden" value="${CMT.c_b_id}">
<input name="c_writer" id="c_writer" placeholder="작성자" class="form-control">
<input name="c_subject" id="c_subject" placeholder="내용" class="form-control">
<button type="button" class="btn float-right btn-cmt-save">답변저장</button>
</form>