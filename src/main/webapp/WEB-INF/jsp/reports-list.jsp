<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Report serach results (if any found) -->
<table class="table">
	<tr>
		<th><spring:message code="report.startDate" /></th>
		<th><spring:message code="report.endDate" /></th>
		<th><spring:message code="report.performer" /></th>
		<th><spring:message code="report.activity" /></th>
	</tr>
	<c:forEach items="${reports}" var="report">
		<tr>
			<td><spring:eval expression="report.startDate"></spring:eval></td>
			<td><spring:eval expression="report.endDate"></spring:eval></td>
			<td>${report.performer}</td>
			<td>${report.activity}</td>
		</tr>
	</c:forEach>
</table>