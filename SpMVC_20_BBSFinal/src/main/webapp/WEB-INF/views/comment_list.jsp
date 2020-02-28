<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>


<c:forEach items="${CMT_LIST}" var="CMT" varStatus="i">
<div class="cmt-item row p-2" data-id="${CMT.c_id}">
<div class="col-2">${CMT.c_writer }:</div>
<div class="col-9">${CMT.c_subject }</div>
<div class="col-1 cmt-item-del"><b>&times;</b></div>
<br/>
</div>
</c:forEach>
