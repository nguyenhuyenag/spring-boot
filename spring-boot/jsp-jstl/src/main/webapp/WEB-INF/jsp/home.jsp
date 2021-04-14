<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Home</title>
		<link rel="stylesheet"  href="./static/style.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- global variable -->
		<script type="text/javascript">
			<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
			<c:set var="CONTEXT_CORE" value="${CONTEXT_PATH}/core" scope="session"/>
			<c:set var="CONTEXT_FORMAT" value="${CONTEXT_PATH}/format" scope="session"/>
		</script>
	</head>
<body>
	<!-- menu -->
	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="home">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<!-- <li class="active"><a href="/">Home</a></li> -->
					<li><a href="${CONTEXT_PATH}/core/language">Language</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end menu -->
	<div class="container">
		<div class="starter-template">
			<h2>Spring Boot JSP + JSTL</h2>
			<h3>0) HttpServletRequest</h3>
			<ul>
				<li><a href="${CONTEXT_PATH}/request">Request</a></li>
				<li><a href="${CONTEXT_PATH}/view">Create View</a></li>
				<li><a href="${CONTEXT_PATH}/view1">Create View 1</a></li>
				<li><a href="${CONTEXT_PATH}/view2">Create View 2</a></li>
				<li><a href="${CONTEXT_PATH}/view3">Create View 3</a></li>
				<li><a href="${CONTEXT_PATH}/pass-data/page-1">Send data to another JSP</a></li>
			</ul>
			<h3>1) Core</h3>
			<ul>
				<li><a href="${CONTEXT_CORE}/for-each">For Each</a></li>
				<li><a href="${CONTEXT_CORE}/el">Expression Language</a></li>
				<li><a href="${CONTEXT_CORE}/if">If</a></li>
				<li><a href="${CONTEXT_CORE}/if-else">If - Else</a></li>
				<li><a href="${CONTEXT_PATH}/form/employee">Form submit</a></li>
				<li><a href="${CONTEXT_CORE}/cout">Cout</a></li>
				<li><a href="${CONTEXT_CORE}/set" class="red">Set</a></li>
				<li><a href="${CONTEXT_CORE}/remove">Remove</a></li>
				<li><a href="${CONTEXT_CORE}/catch">Catch</a></li>
				<li><a href="${CONTEXT_CORE}/for-tokens">ForTokens</a></li>
				<li><a href="${CONTEXT_CORE}/url">Url</a></li>
				<li><a href="${CONTEXT_CORE}/import" class="red">Import</a></li>
				<li><a href="${CONTEXT_CORE}/redirect">Redirect</a></li>
				<li><a href="${CONTEXT_CORE}/param">Param</a></li>
			</ul>
			<h3>2) Format</h3>
			<ul>
				<li><a href="${CONTEXT_FORMAT}/bundle" class="red">Bundle</a></li>
				<li><a href="${CONTEXT_FORMAT}/param">Param</a></li>
				<li><a href="${CONTEXT_FORMAT}/format-number">Format Number</a></li>
				<li><a href="${CONTEXT_FORMAT}/format-date">Format Date</a></li>
			</ul>
			<h3>3) Functions</h3>
			<ul>
				<li><a href="${CONTEXT_PATH}/functions">Functions</a></li>
			</ul>
		</div>
	</div>
</body>

</html>
