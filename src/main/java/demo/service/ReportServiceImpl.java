package demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;

import demo.model.QReport;
import demo.model.Report;
import demo.repository.ReportRepository;

/**
 * Default implementation of {@link ReportService}.
 * @author xakep
 *
 */
@Service
public class ReportServiceImpl implements ReportService {

	private static final Sort DEFAULT_SORT = new Sort(
			"startDate", "endDate", "performer", "activity");

	private final ReportRepository reportRepository;

	@Autowired
	public ReportServiceImpl(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> getAllPerformers() {
		return reportRepository.findAllPerformers();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Report> getReports(String performer, LocalDate startDate,
			LocalDate endDate, Sort sort) {

		BooleanBuilder builder = new BooleanBuilder();

		if (performer != null && !performer.trim().isEmpty()) {
			builder.and(QReport.report.performer.equalsIgnoreCase(performer));
		}
		if (startDate != null) {
			builder.and(QReport.report.startDate.goe(startDate));
		}
		if (endDate != null) {
			builder.and(QReport.report.endDate.loe(endDate));
		}

		return Lists.newArrayList(reportRepository.findAll(builder, sort));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Report> getReports(String performer, LocalDate startDate,
			LocalDate endDate) {
		return getReports(performer, startDate, endDate, DEFAULT_SORT);
	}
}
