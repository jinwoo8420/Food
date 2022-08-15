<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${TEST}" var="test1">

		<table>
			<thead>
				<tr>
					<th>분류</th>
					<th>예보날짜</th>
					<th>예보시간</th>
					<th>예보결과</th>
				</tr>
			</thead>

			<tr>
				<td>${test1.category}</td>
				<td>${test1.fcstDate}</td>
				<td>${test1.fcstTime}</td>
				<td>${test1.fcstValue}</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>