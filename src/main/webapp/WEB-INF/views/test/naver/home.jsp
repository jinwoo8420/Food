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
	<h3>오늘의 추천 메뉴는?</h3>
	<h4>* ${RAINFOOD} *</h4>
	<button class="kakao_btn">버튼</button>

	<br>

	<c:forEach items="${RAIN}" var="rainlist">
		<div>

			<h5>
				<b>${rainlist.title}</b>
			</h5>

			<c:if test="${empty rainlist.link}">
				<p>홈페이지 : 제공된 주소가 없습니다</p>
			</c:if>

			<c:if test="${not empty rainlist.link}">
				<p>홈페이지 : ${rainlist.link}</p>
			</c:if>

			<p>주소 : ${rainlist.address}</p>
			<p>도로명 주소 : ${rainlist.roadAddress}</p>

			<br>
		</div>

	</c:forEach>
</body>

</html>