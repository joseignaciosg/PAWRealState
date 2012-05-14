<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ChinuProp </title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="${ assetPath }/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
	<link href="${ assetPath }/css/custom_style.css" rel="stylesheet"
	type="text/css" />
	<script type="text/javascript" src="${ assetPath }/js/jquery.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/jquery.placeholder.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/bootstrap-collapse.js"></script>
	<script type="text/javascript" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyBUU88FRtPaYJqd6RHpeLKqUEIvbTc5GC4"></script>
	<script type="text/javascript" src="${ assetPath }/js/jquery.gmap-1.1.0-min.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/bootstrap-carousel.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="${ assetPath }/js/bootstrap-button.js"></script>
	
	
	<script type="text/javascript">
		assetPath = "${ assetprPath }";
	</script>
	<script type="text/javascript" src="${ assetPath }/js/application.js"></script>
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<jsp:include page="navbar.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<div class="container" id="errors">
		<jsp:include page="errors.jsp"></jsp:include>
	</div>
	<div class="container" id="main">
		<jsp:include page="${ page }"></jsp:include>
	</div>
	
</body>
</html>
