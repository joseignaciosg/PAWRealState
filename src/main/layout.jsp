<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Hotel Application</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="${ basePath }/assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="${ basePath }/assets/css/custom_style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ basePath }/assets/js/jquery.js"></script>
	<script type="text/javascript" src="${ basePath }/assets/js/bootstrap-modal.js"></script>
	<script type="text/javascript">
		basePath = "${ basePath }";
	</script>
	<script type="text/javascript" src="${ basePath }/assets/js/app.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="../${ page }"></jsp:include>
	</div>
</body>
</html>
