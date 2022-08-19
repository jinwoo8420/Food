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

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

</head>

<body>
	<div>
		<!-- <a href="javascript:sendLink()"> -->
		<a id="kakaotalk-sharing-btn">
			<img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" />
		</a>
	</div>


	<script type="text/javascript">
		Kakao.init('be01097258cdbbe3042dc5464adc574e'); // 초기화

		Kakao.Share
				.createDefaultButton({
					container : '#kakaotalk-sharing-btn',
					objectType : 'list',
					headerTitle : 'WEEKLY MAGAZINE',
					headerLink : {
						mobileWebUrl : 'https://developers.kakao.com',
						webUrl : 'https://developers.kakao.com',
					},
					contents : [
							{
								title : '취미의 특징, 탁구',
								description : '스포츠',
								imageUrl : 'http://k.kakaocdn.net/dn/bDPMIb/btqgeoTRQvd/49BuF1gNo6UXkdbKecx600/kakaolink40_original.png',
								link : {
									mobileWebUrl : 'https://developers.kakao.com',
									webUrl : 'https://developers.kakao.com',
								},
							},
							{
								title : '크림으로 이해하는 커피이야기',
								description : '음식',
								imageUrl : 'http://k.kakaocdn.net/dn/QPeNt/btqgeSfSsCR/0QJIRuWTtkg4cYc57n8H80/kakaolink40_original.png',
								link : {
									mobileWebUrl : 'https://developers.kakao.com',
									webUrl : 'https://developers.kakao.com',
								},
							},
							{
								title : '감성이 가득한 분위기',
								description : '사진',
								imageUrl : 'http://k.kakaocdn.net/dn/c7MBX4/btqgeRgWhBy/ZMLnndJFAqyUAnqu4sQHS0/kakaolink40_original.png',
								link : {
									mobileWebUrl : 'https://developers.kakao.com',
									webUrl : 'https://developers.kakao.com',
								},
							}, ],
					buttons : [ {
						title : '웹으로 보기',
						link : {
							mobileWebUrl : 'https://developers.kakao.com',
							webUrl : 'https://developers.kakao.com',
						},
					}, {
						title : '앱으로 보기',
						link : {
							mobileWebUrl : 'https://developers.kakao.com',
							webUrl : 'https://developers.kakao.com',
						},
					}, ],
				});
	</script>
</body>

</html>