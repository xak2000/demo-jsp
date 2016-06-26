package demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import demo.converter.UsMediumDateAnnotationFormatterFactory;

/**
 * <p><b>DO NOT USE @EnableWebMvc</b> as it disables spring-boot's MVC autoconfiguration.</p>
 * For instance it disables spring-boot's jackson ObjectMapper which understands
 * application.properties config and creates new ObjectMapper instead. This leads to
 * LocalDateTime fields serializing as arrays instead of ISO-8601 strings.
 *
 * @author xakep
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// allow to format dates in US MEDIUM format (Mon D, YYYY) using @UsMediumDate annotation
		registry.addFormatterForFieldAnnotation(new UsMediumDateAnnotationFormatterFactory());
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// allows to serve static resources like reports.js
		configurer.setUseSuffixPatternMatch(false);
	}

}
