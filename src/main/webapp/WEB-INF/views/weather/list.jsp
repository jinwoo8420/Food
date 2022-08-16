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
	<table>
		<c:set var="loop_flag" value="false" />
		<c:forEach items="${TEST}" var="test1">
			<c:if test="${not loop_flag }">
				<c:if test="${test1.category eq 'SKY' || test1.category eq 'PTY'}">
					<thead>
						<tr>
							<th>분류</th>
							<th>예보날짜</th>
							<th>예보시간</th>
							<th>예보결과</th>
						</tr>
					</thead>

					<tr>
						<c:if test="${test1.category eq 'SKY'}">
							<td>날씨</td>
						</c:if>

						<c:if test="${test1.category eq 'PTY'}">
							<td>강수형태</td>
						</c:if>

						<td>${test1.fcstDate}</td>
						<td>${test1.fcstTime}</td>

						<c:if test="${test1.category eq 'SKY' && test1.fcstValue eq '1'}">
							<td>맑음</td>
						</c:if>

						<c:if test="${test1.category eq 'SKY' && test1.fcstValue eq '2'}">
							<td>비</td>
						</c:if>

						<c:if test="${test1.category eq 'SKY' && test1.fcstValue eq '3'}">
							<td>구름 많음</td>
						</c:if>

						<c:if test="${test1.category eq 'SKY' && test1.fcstValue eq '4'}">
							<td>흐림</td>
						</c:if>

						<c:if test="${test1.category eq 'PTY' && test1.fcstValue eq '0'}">
							<td>없음</td>
						</c:if>

						<c:if test="${test1.category eq 'PTY' && test1.fcstValue eq '1'}">
							<td>비</td>
						</c:if>

						<c:if test="${test1.category eq 'PTY' && test1.fcstValue eq '2'}">
							<td>비/눈</td>
						</c:if>

						<c:if test="${test1.category eq 'PTY' && test1.fcstValue eq '3'}">
							<td>눈</td>
						</c:if>

						<c:if test="${test1.category eq 'PTY' && test1.fcstValue eq '4'}">
							<td>소나기</td>
						</c:if>
					</tr>
					<c:set var="loop_flag" value="true" />
				</c:if>
			</c:if>
		</c:forEach>

	</table>

</body>
</html>