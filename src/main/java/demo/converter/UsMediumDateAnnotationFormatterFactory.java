package demo.converter;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableSet;

/**
 * {@link AnnotationFormatterFactory} for {@link UsMediumLocalDateFormatter} formatter
 * and {@link UsMediumDate} annotation.
 *
 * @author xakep
 */
public class UsMediumDateAnnotationFormatterFactory implements AnnotationFormatterFactory<UsMediumDate> {

	private static final Set<Class<?>> FIELD_TYPES = ImmutableSet.of(LocalDate.class);
	private static final UsMediumLocalDateFormatter US_MEDIUM_LOCAL_DATE_FORMATTER = new UsMediumLocalDateFormatter();

	@Override
	public Set<Class<?>> getFieldTypes() {
		return FIELD_TYPES;
	}

	@Override
	public Printer<?> getPrinter(UsMediumDate annotation, Class<?> fieldType) {
		return US_MEDIUM_LOCAL_DATE_FORMATTER;
	}

	@Override
	public Parser<?> getParser(UsMediumDate annotation, Class<?> fieldType) {
		return US_MEDIUM_LOCAL_DATE_FORMATTER;
	}

}
