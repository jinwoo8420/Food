<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<th>지역</th>
					<th>측정일시</th>
					<th>PM10 농도</th>
					<th>PM25 농도</th>
				</tr>
			</thead>

			<tr>
				<td>${test1.sidoName}</td>
				<td>${test1.dataTime}</td>
				<td>${test1.pm10Value}</td>
				<td>${test1.pm25Value}</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>