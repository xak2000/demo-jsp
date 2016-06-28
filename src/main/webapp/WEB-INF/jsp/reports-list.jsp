<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Report serach results (if any found) -->
<table class="table reports-table">
	<tr>
		<th class="start-date-header"><spring:message code="report.startDate" /></th>
		<th class="end-date-header"><spring:message code="report.endDate" /></th>
		<th class="performer-header"><spring:message code="report.performer" /></th>
		<th class="activity-header"><spring:message code="report.activity" /></th>
	</tr>
	<c:forEach items="${reports}" var="report">
		<tr>
			<td class="start-date"><spring:eval expression="report.startDate"></spring:eval></td>
			<td class="end-date"><spring:eval expression="report.endDate"></spring:eval></td>
			<td class="performer">${report.performer}</td>
			<td class="activity">${report.activity}</td>
		</tr>
	</c:forEach>
</table>