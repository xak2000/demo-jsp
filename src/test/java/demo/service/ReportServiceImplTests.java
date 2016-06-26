package demo.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import com.mysema.query.types.Predicate;

import demo.repository.ReportRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTests {

	private List<String> mockPerformers = Arrays.asList(new String[] {
			"John", "Lilith", "Pikachu"
	});

	@Mock
	private ReportRepository reportRepository;

	// class under test
	private ReportServiceImpl reportServiceImpl;

	@Before
	public void init() {
		reportServiceImpl = new ReportServiceImpl(reportRepository);
	}

	@Test
	public void getAllPerformers_ReturnsPerformersFromReportRepository() {
		given(reportRepository.findAllPerformers()).willReturn(mockPerformers);

		List<String> allPerformers = reportServiceImpl.getAllPerformers();

		then(reportRepository).should().findAllPerformers();
		assertThat(allPerformers, is(mockPerformers));
	}

	@Test
	public void getReportsWithoutSort_callsGetReportsWithDefaultSort() {
		given(reportRepository.findAll((Predicate) anyObject(), (Sort) anyObject()))
				.willReturn(new ArrayList<>());
		reportServiceImpl.getReports(null, null, null);
		then(reportRepository).should().findAll(any(),
				eq(new Sort("startDate", "endDate", "performer", "activity")));
	}

	@Test
	public void getReportsWithSort_usesGivenSort() {
		Sort customSort = new Sort(new Sort.Order(Sort.Direction.DESC, "performer"));
		given(reportRepository.findAll((Predicate) anyObject(), (Sort) anyObject()))
				.willReturn(new ArrayList<>());
		reportServiceImpl.getReports(null, null, null, customSort);
		then(reportRepository).should().findAll(any(), eq(customSort));
	}

}
