package demo.converter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * Formats {@link LocalDate} as a date in {@link FormatStyle#MEDIUM} format for {@link Locale#US}.
 * @author xakep
 */
public class UsMediumLocalDateFormatter implements Formatter<LocalDate> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
			.withLocale(Locale.US);

	@Override
	public String print(LocalDate object, Locale locale) {
		return object.format(FORMATTER);
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, FORMATTER);
	}
}
