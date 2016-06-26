package demo.model;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import demo.controller.ReportsController;
import demo.converter.UsMediumDate;

/**
 * Form object to support
 * {@link ReportsController#reports(org.springframework.ui.Model, ReportSearchForm, org.springframework.validation.BindingResult, org.springframework.data.domain.Sort)}
 * search functionality.
 *
 * @author xakep
 *
 */
public class ReportSearchForm {

	public enum TimePeriod {
		LAST_MONTH, LAST_QTR, LAST_YEAR,
		CURRENT_MONTH_TO_DATE, CURRENT_QTR_TO_DATE, CURRENT_YEAR_TO_DATE;
	}

	/**
	 * This {@link Converter} is for convinient displaying of {@link TimePeriod} enum
	 * values from Spring's {@code <form:options />} tag as translated text according to
	 * current locale using Spring's i18n support.
	 *
	 * <pre>
	 *	&lt;form:select path="timePeriod"&gt;
	 *		&lt;form:options /&gt;
	 *	&lt;/form:select&gt;
	 * </pre>
	 *
	 * <p>This converter also used when {@code <spring:eval>} tag renders the value
	 * of enum variable.
	 *
	 * <p>To declare messages corresponding to each enum value, use enum value prefixed by
	 * {@code report.TimePeriod.} text in your {@code messages.properties} file.
	 *
	 * <p>For example:
	 * <pre>
	 * report.TimePeriod.LAST_MONTH=Last Month
	 * report.TimePeriod.LAST_QTR=Last Qtr
	 * report.TimePeriod.LAST_YEAR=Last Calendar Year
	 * report.TimePeriod.CURRENT_MONTH_TO_DATE=Current Month to Date
	 * report.TimePeriod.CURRENT_QTR_TO_DATE=Current Qtr to Date
	 * report.TimePeriod.CURRENT_YEAR_TO_DATE=Current Year to Date
	 * </pre>
	 *
	 * @author xakep
	 *
	 */
	@Component
	public static class TimePeriodConverter implements Converter<TimePeriod, String> {

		@Resource
		MessageSource messageSource;

		@Override
		public String convert(TimePeriod source) {
			return messageSource.getMessage(getCode(source), null, source.toString(),
					LocaleContextHolder.getLocale());
		}

		private String getCode(TimePeriod timePeriod) {
			return "report." + timePeriod.getClass().getSimpleName() + "." + timePeriod.name();
		}

	}

	@UsMediumDate
	private LocalDate startDate;

	@UsMediumDate
	private LocalDate endDate;

	private String performer;

	/**
	 * This field is not actually part of this form binding.
	 * This field is managed from JS code. It is here only for convinience of
	 * using {@code spring:form} JSP tag library and for the sake of flexibility.
	 *
	 * <p>For example we can very easily remove JS-behaviour and manage this field
	 * from the JAVA code.
	 */
	private TimePeriod timePeriod;

	public ReportSearchForm() {
	}

	/**
	 * @return the minimal start date of report to search
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the minimal start date of report to search
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the maximum end date of report to search
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the maximum end date of report to search
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the performer to search reports of (can be null or empty string to not
	 * restrict search by performer)
	 */
	public String getPerformer() {
		return performer;
	}

	/**
	 * @param performer the performer to search reports of (can be null or empty string
	 * to not restrict search by performer)
	 */
	public void setPerformer(String performer) {
		this.performer = performer;
	}

	/**
	 * The {@code timePeriod} property is not actually part of this form binding.
	 * This field is managed from JS code. It is here only for convinience of
	 * using {@code spring:form} JSP tag library and for the sake of flexibility.
	 *
	 * <p>For example we can very easily remove JS-behaviour and manage this field
	 * from the JAVA code.
	 */
	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	/**
	 * The {@code timePeriod} property is not actually part of this form binding.
	 * This field is managed from JS code. It is here only for convinience of
	 * using {@code spring:form} JSP tag library and for the sake of flexibility.
	 *
	 * <p>For example we can very easily remove JS-behaviour and manage this field
	 * from the JAVA code.
	 */
	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	@Override
	public String toString() {
		return String.format(
				"ReportSearchForm [startDate=%s, endDate=%s, performer=%s]",
				startDate, endDate, performer);
	}

}
