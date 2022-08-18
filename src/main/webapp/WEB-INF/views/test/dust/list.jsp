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

<style>
table {
	text-align: center;
}
</style>

</head>
<body>
	<c:forEach items="${TEST}" var="test1">
		<table>
			<c:if test="${test1.pm10Value <= '30'}">
				<thead>

					<tr>
						<th>지역</th>
						<th>세부 지역</th>
						<th>측정일시</th>
						<th>PM10 농도</th>
						<th>PM10 등급</th>
						<th>PM2.5 농도</th>
						<th>PM2.5 등급</th>
					</tr>
				</thead>

				<tr>
					<td>${test1.sidoName}</td>
					<td>${test1.stationName}</td>
					<td>${test1.dataTime}</td>
					<td>${test1.pm10Value}</td>
					<td>좋음</td>
					<td>${test1.pm25Value}</td>

					<c:if test="${test1.pm25Value <= '15'}">
						<td>좋음</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '35' && test1.pm25Value > '15'}">
						<td>보통</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '75' && test1.pm25Value > '35'}">
						<td>나쁨</td>
					</c:if>
				</tr>
			</c:if>

			<c:if test="${test1.pm10Value <= '80' && test1.pm10Value > '30'}">
				<thead>

					<tr>
						<th>지역</th>
						<th>세부 지역</th>
						<th>측정일시</th>
						<th>PM10 농도</th>
						<th>PM10 등급</th>
						<th>PM2.5 농도</th>
						<th>PM2.5 등급</th>
					</tr>
				</thead>

				<tr>
					<td>${test1.sidoName}</td>
					<td>${test1.stationName}</td>
					<td>${test1.dataTime}</td>
					<td>${test1.pm10Value}</td>
					<td>보통</td>
					<td>${test1.pm25Value}</td>

					<c:if test="${test1.pm25Value <= '15'}">
						<td>좋음</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '35' && test1.pm25Value > '15'}">
						<td>보통</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '75' && test1.pm25Value > '35'}">
						<td>나쁨</td>
					</c:if>
				</tr>
			</c:if>

			<c:if test="${test1.pm10Value <= '150' && test1.pm10Value > '80'}">
				<thead>
					<tr>
						<th>지역</th>
						<th>세부 지역</th>
						<th>측정일시</th>
						<th>PM10 농도</th>
						<th>PM10 등급</th>
						<th>PM2.5 농도</th>
						<th>PM2.5 등급</th>
					</tr>
				</thead>

				<tr>
					<td>${test1.sidoName}</td>
					<td>${test1.stationName}</td>
					<td>${test1.dataTime}</td>
					<td>${test1.pm10Value}</td>
					<td>나쁨</td>
					<td>${test1.pm25Value}</td>

					<c:if test="${test1.pm25Value <= '15'}">
						<td>좋음</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '35' && test1.pm25Value > '15'}">
						<td>보통</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '75' && test1.pm25Value > '35'}">
						<td>나쁨</td>
					</c:if>
				</tr>
			</c:if>

			<%-- 			<c:if test="${test1.pm10Value > '150'}">
				<thead>
					<tr>
						<th>지역</th>
						<th>세부 지역</th>
						<th>측정일시</th>
						<th>PM10 농도</th>
						<th>PM10 등급</th>
						<th>PM2.5 농도</th>
						<th>PM2.5 등급</th>
					</tr>
				</thead>

				<tr>
					<td>${test1.sidoName}</td>
					<td>${test1.stationName}</td>
					<td>${test1.dataTime}</td>
					<td>${test1.pm10Value}</td>
					<td>매우 나쁨</td>
					<td>${test1.pm25Value}</td>
					
					<c:if test="${test1.pm25Value <= '15'}">
						<td>좋음</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '35' && test1.pm25Value > '15'}">
						<td>보통</td>
					</c:if>

					<c:if test="${test1.pm25Value <= '75' && test1.pm25Value > '35'}">
						<td>나쁨</td>
					</c:if>
				</tr>
			</c:if> --%>
		</table>
	</c:forEach>
</body>
</html>