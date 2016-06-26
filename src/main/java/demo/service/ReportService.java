package demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;

import demo.model.Report;

/**
 * Service to manipulate {@link Report}s.
 *
 * @author xakep
 */
public interface ReportService {

	/**
	 * Returns the list of all unique performers of all reports in alphabetical order.
	 *
	 * @return the list of all performers.
	 */
	List<String> getAllPerformers();

	/**
	 * Returns all reports between given dates (inclusive) for given performer.
	 *
	 * @param performer the performer for which to return reports. Can be {@code null}
	 * or empty string to not restrict the search by performer (ie. return reports for
	 * any performer).
	 *
	 * @param startDate the minimal {@code startDate} of report for which to find
	 * reports (inclusive). Can be {@code null} to not restrict the search by minimal
	 * {@code startDate}.
	 *
	 * @param endDate the maximum {@code endDate} of report for which to find
	 * reports (inclusive). Can be {@code null} to not restrict the search by maximum
	 * {@code endDate}.
	 *
	 * @param sort the sort criteria for returned reports.
	 *
	 * @return the list of reports which meets the specified criteria.
	 */
	List<Report> getReports(String performer, LocalDate startDate, LocalDate endDate, Sort sort);

	/**
	 * Returns all reports between given dates (inclusive) for given performer
	 * using default sort ("startDate", "endDate", "performer", "activity" in ascendant order).
	 *
	 * @param performer the performer for which to return reports. Can be {@code null}
	 * or empty string to not restrict the search by performer (ie. return reports for
	 * any performer).
	 *
	 * @param startDate the minimal {@code startDate} of report for which to find
	 * reports (inclusive). Can be {@code null} to not restrict the search by minimal
	 * {@code startDate}.
	 *
	 * @param endDate the maximum {@code endDate} of report for which to find
	 * reports (inclusive). Can be {@code null} to not restrict the search by maximum
	 * {@code endDate}.
	 *
	 * @param sort the sort criteria for returned reports.
	 *
	 * @return the list of reports which meets the specified criteria.
	 *
	 * @see {@link ReportService#getReports(String, LocalDate, LocalDate, Sort)}
	 */
	List<Report> getReports(String performer, LocalDate startDate, LocalDate endDate);

}
