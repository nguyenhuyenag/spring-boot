<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" >
</head>
<body>
	<div class="container">
		<%@ include file="menu.jsp" %>
		<h1>Login</h1>
		<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
			<div class="alert alert-danger text-center">
	    	<p>Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
	    	</div>
		</c:if>
		<form name='f' action="${CONTEXT_PATH}/j_spring_security_check" method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' value='user1'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' value='123' /></td>
				</tr>
				<tr>
					<td>Remember Me?</td>
					<td><input type="checkbox" name="remember" /></td>
				</tr>
				<tr>
					<td><input name="submit" type="submit" value="submit" /></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<br> Username/password:
		<ul>
			<li>user/123</li>
			<li>admin/123</li>
		</ul>
	</div>
</body>

</html>