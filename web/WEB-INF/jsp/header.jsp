<%--
  Created by IntelliJ IDEA.
  User: vitali
  Date: 8/8/23
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
  <c:if test="${not empty sessionScope.user}">
    <div id="logout">
      <form action="${pageContext.request.contextPath}/logout">
        <button type="submit">Logout</button>
      </form>
    </div>
  </c:if>
  <div id="locale">
    <form action="${pageContext.request.contextPath}/locale" method="post">
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_US">EN</button>
    </form>
  </div>
  <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>
  <fmt:setBundle basename="translations"/>
</div>