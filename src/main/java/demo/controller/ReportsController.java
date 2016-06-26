package demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.model.Report;
import demo.model.ReportSearchForm;
import demo.service.ReportService;

/**
 * Controller of {@link Report} domain object.
 *
 * @author xakep
 */
@Controller
@RequestMapping("/reports")
public class ReportsController {

	private static final Logger log = LoggerFactory.getLogger(ReportsController.class);
	private final ReportService reportService;

	@Autowired
	public ReportsController(ReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * Handles reports search form and shows the search form along with the list of found reports.
	 *
	 * <p>Shows all reports if no search criteria given.
	 *
	 * <p>This handler uses GET http method because it doesn't change any server state
	 * and we want to reflect the search criteria in the URL to allow users to share
	 * search result URL.
	 *
	 * <p>This handler is {@code Safe} and {@code Idempotent} according to
	 * <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">RFC 2616</a>.
	 *
	 * @author xakep
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String reports(Model model, @ModelAttribute("form") @Valid ReportSearchForm form,
			BindingResult result,
			@SortDefault({"startDate", "endDate", "performer", "activity"}) Sort sort) {

		log.debug("Reports request: {}. Sort: {}", model, sort);

		List<String> performers = reportService.getAllPerformers();
		model.addAttribute("allPerformers", performers);

		if (!result.hasErrors()) { // only search reports if form has no errors
			List<Report> reports = reportService.getReports(
					form.getPerformer(), form.getStartDate(), form.getEndDate(), sort);
			model.addAttribute("reports", reports);
			log.debug("Found reports: {}", reports);
		} else {
			log.warn("Errors in SearchForm: {}", result.getAllErrors());
		}

		return "reports";
	}

}
