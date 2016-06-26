package demo.repository;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import demo.it.AbstractDbUnitIntegrationTests;

@DatabaseSetup(ReportRepositoryIT.DATASET_REPORTS)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = ReportRepositoryIT.DATASET_REPORTS)
public class ReportRepositoryIT extends AbstractDbUnitIntegrationTests {

	protected static final String DATASET_REPORTS = "/datasets/reports.xml";
	protected static final String DATASET_REPORTS_EMPTY = "/datasets/reports-empty.xml";

	@Autowired
	ReportRepository reportRepository;

	@Test
	public void findAllPerformers_returnsAllUniquePerformers() {
		List<String> performers = reportRepository.findAllPerformers();
		assertThat("Should return all unique performers", performers, hasSize(4));
	}

	@Test
	public void findAllPerformers_returnsEachPerformerOnlyOnce() {
		List<String> performers = reportRepository.findAllPerformers();
		assertThat("Should return each performer only once",
				performers.stream().distinct().collect(toList()), hasSize(4));
	}

	@Test
	public void findAllPerformers_returnsPerformersInAlphabeticalOrder() {
		List<String> performers = reportRepository.findAllPerformers();
		assertThat("Should return performers in alphabetical order",
				performers, contains("Bart Simpson", "Homer Simpson",
						"Ned Flanders", "Xena Warrior Princess"));
	}

	@Test
	@DatabaseSetup(DATASET_REPORTS_EMPTY)
	public void findAllPerformers_returnsEmptyList_whenNoPerformersInRepository() {
		List<String> performers = reportRepository.findAllPerformers();
		assertThat("Should return empty list when no performers in repository",
				performers, is(empty()));
	}

}
