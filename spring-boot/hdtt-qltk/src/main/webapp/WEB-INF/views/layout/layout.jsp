<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
    <title>HDDT</title>
	<!-- css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- script -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
    <!-- <link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"> -->
    <link type="text/css" rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" />
    <script>
        /* $(function () {
            handleRequiredMessage('');
        });
        function handleRequiredMessage(msg) {
            if (StringUtils.isEmpty(msg)) {
                msg = 'Đây là thông tin bắt buộc';
            }
            $("input[required], select[required]").attr("oninvalid", "this.setCustomValidity('" + msg + "')");
            $("input[required], select[required]").attr("oninput", "setCustomValidity('')");
        } */
    </script>
    <c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
</head>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container">
		<tiles:insertAttribute name="body" />
    </div>
    <%-- <tiles:insertAttribute name="footer" /> --%>
</body>
</html>
