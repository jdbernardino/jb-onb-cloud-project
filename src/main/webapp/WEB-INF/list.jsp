<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Balance Entries</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/add" method="post">
		Firstname: <input type="text" name="firstname"> <br />
		Lastname: <input type="text" name="lastname"> <br />
		Money:<input type="number" name="money"> <br />
		<input type="submit" value="Submit" />
	</form>
	<br />
	<c:choose>
		<c:when test="${entries == null || entries.size() < 1} ">
			<p>There are no entries to display.</p>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Money</th>
				</tr>
				<c:forEach var="entry" items="${entries}">
            		<tr>
                		<td>${entry.firstname }</td>
                		<td>${entry.lastname }</td>
                		<td>${entry.money }</td>
            		</tr>
        		</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>