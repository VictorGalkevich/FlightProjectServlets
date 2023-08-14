<%--
  Created by IntelliJ IDEA.
  User: vitali
  Date: 8/7/23
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Tickets</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:if test="${not empty requestScope.tickets}">
  <h1>Купленные билеты:</h1>
  <ul>
    <c:forEach var="ticket" items="${requestScope.tickets}">
      <li>${fn:toLowerCase(ticket.seatNo)}</li>
    </c:forEach>
  </ul>
</c:if>
</body>
</html>
