package demo.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import demo.it.AbstractDbUnitIntegrationTests;
import demo.model.Report;

@DatabaseSetup(ReportServiceIT.DATASET_REPORTS)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = ReportServiceIT.DATASET_REPORTS)
@SuppressWarnings("unchecked")
public class ReportServiceIT extends AbstractDbUnitIntegrationTests {

	protected static final String DATASET_REPORTS = "/datasets/reports.xml";
	protected static final String DATASET_REPORTS_EMPTY = "/datasets/reports-empty.xml";

	private static final String PERFORMER = "Bart Simpson";
	private static final String PERFORMER_LOWER_CASE = "bart simpson";
	private static final LocalDate START_DATE = LocalDate.of(2016, 1, 1);
	private static final LocalDate END_DATE = LocalDate.of(2016, 1, 2);

	@Autowired
	ReportService reportService;

	@Test
	public void getReports_returnsAllReports_whenAllArgsAreNull() {
		List<Report> reports = reportService.getReports(null, null, null);
		assertThat(reports, hasSize(6));
	}

	@Test
	public void getReports_returnsAllReportsForGivenPerformer_whenNoDatesGiven() {
		List<Report> reports = reportService.getReports(PERFORMER, null, null);
		assertThat(reports, containsInAnyOrder(
				hasProperty("id", equalTo(3L)),
				hasProperty("id", equalTo(5L))
		));
	}

	@Test
	public void getReports_returnsAllReportsAfterGivenStartDate_whenNoPerformerAndEndDateGiven() {
		List<Report> reports = reportService.getReports(null, START_DATE, null);
		assertThat(reports, containsInAnyOrder(
				hasProperty("id", equalTo(3L)),
				hasProperty("id", equalTo(4L)),
				hasProperty("id", equalTo(5L)),
				hasProperty("id", equalTo(6L))
		));
	}

	@Test
	public void getReports_returnsAllReportsBeforeGivenEndDate_whenNoPerformerAndStartDateGiven() {
		List<Report> reports = reportService.getReports(null, null, END_DATE);
		assertThat(reports, containsInAnyOrder(
				hasProperty("id", equalTo(3L)),
				hasProperty("id", equalTo(4L))
		));
	}

	@Test
	public void getReports_returnsAllReportsBetweenGivenDates_whenNoPerformerGiven() {
		List<Report> reports = reportService.getReports(null, START_DATE, END_DATE);
		assertThat(reports, containsInAnyOrder(
				hasProperty("id", equalTo(3L)),
				hasProperty("id", equalTo(4L))
		));
	}

	@Test
	public void getReports_returnsAllReportsForGivenPerformerBetweenGivenDates_whenAllArgsGiven() {
		List<Report> reports = reportService.getReports(PERFORMER, START_DATE, END_DATE);
		assertThat(reports, contains(
				hasProperty("id", equalTo(3L))
		));
	}

	@Test
	public void getReports_ignoresPerformer_whenEmptyStringGiven() {
		List<Report> reports = reportService.getReports("", null, null);
		assertThat(reports, hasSize(6));
	}

	@Test
	public void getReports_ignoresPerformerCase() {
		List<Report> reports = reportService.getReports(PERFORMER_LOWER_CASE, null, null);
		assertThat(reports, containsInAnyOrder(
				hasProperty("id", equalTo(3L)),
				hasProperty("id", equalTo(5L))
		));
	}

	@Test
	public void getReports_returnsReportsAccordingToGivenSort() {
		Sort customSort = new Sort(new Sort.Order(Sort.Direction.DESC, "performer"));
		List<Report> reports = reportService.getReports(null, null, null, customSort);
		assertThat(reports, contains(
				hasProperty("id", equalTo(1L)),
				hasProperty("id", equalTo(2L)),
				hasProperty("id", anyOf(equalTo(4L), equalTo(6L))),
				hasProperty("id", anyOf(equalTo(4L), equalTo(6L))),
				hasProperty("id", anyOf(equalTo(3L), equalTo(5L))),
				hasProperty("id", anyOf(equalTo(3L), equalTo(5L)))
		));
	}

}
