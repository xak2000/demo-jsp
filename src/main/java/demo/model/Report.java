package demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import demo.converter.UsMediumDate;

/**
 * Entity for storing reports.
 *
 * <p>Each report should have non-null {@code startDate}, {@code endDate}, {@code performer}
 * and {@code activity}.
 *
 * @author xakep
 */
@Entity(name = "reports")
public class Report extends AbstractPersistable<Long> {

	@UsMediumDate
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@UsMediumDate
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@Column(name = "performer", nullable = false)
	private String performer;

	@Column(name = "activity", nullable = false)
	private String activity;

	// Default constructor by JPA spec, protected to not allow to create empty Reports
	protected Report() {}

	public Report(LocalDate startDate, LocalDate endDate, String performer, String activity) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.performer = performer;
		this.activity = activity;
	}

	// Getters and setters

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}
