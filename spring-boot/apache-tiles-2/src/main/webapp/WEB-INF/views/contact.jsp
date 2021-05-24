<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h1>Navbar example</h1>
	<p class="lead">This example is a quick exercise to illustrate how
		the top-aligned navbar works. As you scroll, this navbar remains in
		its original position and moves with the rest of the page.</p>
	<a class="btn btn-lg btn-primary" href="../components/navbar/"
		role="button">View navbar docs &raquo;</a>
		
		<c:if test="${not empty ID}">ID: ${ID}</c:if>
</div>
