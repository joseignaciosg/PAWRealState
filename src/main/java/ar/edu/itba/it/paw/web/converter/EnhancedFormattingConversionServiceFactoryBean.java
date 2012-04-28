package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class EnhancedFormattingConversionServiceFactoryBean extends
		FormattingConversionServiceFactoryBean {

	private Converter<?, ?>[] converters;

	@Autowired
	public EnhancedFormattingConversionServiceFactoryBean(
			final Converter<?, ?>[] converters) {
		this.converters = converters;
	}

	@Override
	protected void installFormatters(final FormatterRegistry registry) {
		super.installFormatters(registry);
		for (final Converter<?, ?> c : this.converters) {
			registry.addConverter(c);
		}
	}
}
