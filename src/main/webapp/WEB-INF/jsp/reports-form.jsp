<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Reports search form -->
<form:form method="GET" modelAttribute="form" class="form-horizontal">
	
	<spring:bind path="startDate">
		<div class="form-group ${ status.error ? 'has-error' : '' }">
			<form:label path="startDate" class="col-sm-4 control-label">
				<spring:message code="report.startDate" />
			</form:label>
			<div class="col-sm-8">
				<spring:message code="report.dateFormat" var="dateFormat" />
				<form:input path="startDate" class="form-control"
					placeholder="${dateFormat}" autocomplete="off"
					data-provide="datepicker" data-date-format="M d, yyyy"
					data-date-autoclose="true" data-date-keyboard-navigation="false"
					data-date-clear-btn="true" data-date-today-highlight="true" />
				<form:errors path="startDate" class="help-block" />
			</div>
		</div><!-- .form-group -->
	</spring:bind><!-- startDate -->

	<spring:bind path="endDate">
		<div class="form-group ${ status.error ? 'has-error' : '' }">
			<form:label path="endDate" class="col-sm-4 control-label">
				<spring:message code="report.endDate" />
			</form:label>
			<div class="col-sm-8">
				<spring:message code="report.dateFormat" var="dateFormat" />
				<form:input path="endDate" class="form-control"
					placeholder="${dateFormat}" autocomplete="off"
					data-provide="datepicker" data-date-format="M d, yyyy"
					data-date-autoclose="true" data-date-keyboard-navigation="false"
					data-date-clear-btn="true" data-date-today-highlight="true" />
				<form:errors path="endDate" class="help-block" />
			</div>
		</div><!-- .form-group -->
	</spring:bind><!-- endDate -->

	<spring:bind path="performer">
		<div class="form-group ${ status.error ? 'has-error' : '' }">
			<form:label path="performer" class="col-sm-4 control-label">
				<spring:message code="report.performer" />
			</form:label>
			<div class="col-sm-8">
				<form:select path="performer" class="form-control">
					<spring:message code="report.allPerformers" var="allPerformersLabel" />
					<form:option value="" label="${allPerformersLabel}"></form:option>
					<form:options items="${allPerformers}" />
				</form:select>
				<form:errors path="performer" class="help-block" />
			</div>
		</div><!-- .form-group -->
	</spring:bind><!-- performer -->

	<spring:bind path="timePeriod">
		<div class="form-group ${ status.error ? 'has-error' : '' }">
			<form:label path="timePeriod" class="col-sm-4 control-label">
				<spring:message code="report.timePeriod" />
			</form:label>
			<div class="col-sm-8">
				<form:select path="timePeriod" class="form-control">
					<form:option value="" label=""></form:option>
					<form:options />
				</form:select>
				<form:errors path="timePeriod" class="help-block" />
			</div>
		</div><!-- .form-group -->
	</spring:bind><!-- timePeriod -->

	<input type="submit" value="<spring:message code="report.search" />"
		class="btn btn-lg btn-primary pull-right">

</form:form><!-- Reports search form -->
