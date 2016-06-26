package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import demo.model.Report;

public interface ReportRepository
		extends JpaRepository<Report, Long>, QueryDslPredicateExecutor<Report> {

	/**
	 * Returns all unique performers of all reports.
	 *
	 * @return all unique performers
	 */
	@Query("SELECT performer from #{#entityName} GROUP BY performer ORDER BY performer ASC")
	List<String> findAllPerformers();

}
