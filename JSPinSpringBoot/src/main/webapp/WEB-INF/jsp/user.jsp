<%@ page import="java.time.ZonedDateTime" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<h3> One line of java code </h3>
<p>
    Today is: <%=(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai")))%>
</p>

<h3> Multiple lines of java code </h3>
<p>
    Your ip address is:
    <%
        out.println("Your IP address is: " + request.getRemoteAddr() + "<br>");
        out.println("code code code code");
    %>
</p>

<h3> For loop </h3>
<%
    int count = (int) session.getAttribute("count");
    for (int i = 0; i < count; i++) {
%>
  squirrel
<br />
<%}%>

<h3> tag c:if </h3>
<c:if test="${username != null}">
    <p>username is ${username}<p>
</c:if>

<h3> tag c:choose </h3>
<c:choose>
    <c:when test = "${salary <= 0}">
        too poor
    </c:when>
    <c:when test = "${test > 1000}">
        just soso
    </c:when>
    <c:otherwise>
        nothing
    </c:otherwise>
</c:choose>

<h3> layout </h3>
<%@ include file="footer.jsp"%>