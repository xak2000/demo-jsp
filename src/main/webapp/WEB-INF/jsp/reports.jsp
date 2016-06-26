<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">

<head>
	<title><spring:message code="report.reports" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="${contextPath}/webjars/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css">
</head>

<body>

	<div class="container">

		<h1><spring:message code="report.reports" /></h1>

		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<%@ include file="reports-form.jsp" %>
					</div><!-- .panel-body -->
				</div><!-- .panel -->
			</div><!-- .col -->
		</div><!-- .row -->

		<div class="row">
			<div class="col-xs-12">
				<c:if test="${reports != null}"><%-- Null means form error --%>
					<c:choose>
						<c:when test="${reports.isEmpty()}">
							<div class="panel panel-default">
								<div class="panel-body">
								  <spring:message code="report.notFound" />
								</div>
							</div>							
						</c:when>
						<c:otherwise>
							<%@ include file="reports-list.jsp" %>
						</c:otherwise>	
					</c:choose>
				</c:if>
			</div><!-- .col -->
		</div><!-- .row -->

	</div><!-- .container -->

	<script type="text/javascript" src="${contextPath}/webjars/momentjs/2.13.0/min/moment-with-locales.min.js"></script>
	<script type="text/javascript" src="${contextPath}/webjars/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${contextPath}/webjars/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="${contextPath}/reports.js"></script>

</body>
</html>