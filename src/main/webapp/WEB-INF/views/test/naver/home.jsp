<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>

<style>
p {
	font-style: normal;
}
</style>

</head>

<body>
	<h3>음식 : ${FOOD}</h3>

	<br>

	<c:forEach items="${NAVER}" var="list">
		<div>
			<p>
				이름 : <b>${list.title}</b>
			</p>

			<c:if test="${empty list.link}">
				<p>홈페이지 : 제공된 주소가 없습니다</p>
			</c:if>

			<c:if test="${not empty list.link}">
				<p>홈페이지 : ${list.link}</p>

			</c:if>
			
			<p>주소 : ${list.address}</p>
			<p>도로명 주소 : ${list.roadAddress}</p>
			<br>
		</div>
	</c:forEach>
</body>

</html>