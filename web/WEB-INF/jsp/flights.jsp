<%--
  Created by IntelliJ IDEA.
  User: vitali
  Date: 8/7/23
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Flights</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Список перелетов:</h1>
<ul>
    <c:forEach var="flight" items="${requestScope.flights}">
        <li>
            <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
