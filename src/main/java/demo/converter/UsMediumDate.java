package demo.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Declares that a {@link LocalDate} field should be formatted as a date
 * in {@link FormatStyle#MEDIUM} format ('Jan 12, 1952') for {@link Locale#US}.
 *
 * <p>Example:
 * <pre>
 * class MyClass {
 * 	{@literal @}UsMediumDate
 * 	private LocalDate startDate;
 * 	// ...
 * }
 * </pre>
 *
 * <p>This formatter is required because according to the requirement of the test assignment,
 * the dates should be always in this format, no matter what user browser locale is.
 *
 * @author xakep
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface UsMediumDate {
}
