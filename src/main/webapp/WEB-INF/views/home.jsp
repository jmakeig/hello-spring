<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page session="false" %><!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<link type="text/css" rel="stylesheet" href="/hello-spring/resources/app.css"/>
</head>
<body>
	<h1>Hello world!</h1>
	<ol>
	<c:forEach var="entry" items="${entries}">
		<li><c:out value="${entry.author}"/></li>
	</c:forEach>
	</ol>
</body>
</html>
