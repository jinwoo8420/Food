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

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">

<style>
html::-webkit-scrollbar {
	width: 0.0em;
	height: 0.0em;
}

.splash {
	width: 100%;
	height: 100vh;
}

body {
	background-color: #e7dcdc;
}

p {
	font-family: 'Nanum Gothic Coding', monospace;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	font-size: 30px;
	margin-top: 50px;
	font-weight: bold;
}

img {
	width: 250px;
	margin: 0 auto;
	position: absolute;
	top: 40%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
</head>
<body>
	<div class="splash">
		<div class="d-flex align-items-center">
			<a href="${rootPath}/intro">
				<img src="${rootPath}/static/img/main.png" class="img-responsive">
			</a>
			<p>오늘은 뭐먹지?</p>
		</div>
	</div>
</body>
</html>