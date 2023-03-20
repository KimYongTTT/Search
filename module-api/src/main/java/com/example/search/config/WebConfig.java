package com.example.search.config;

import com.example.search.constants.SearchConstants.SortCondition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSortConditionConverter());
    }

    class StringToSortConditionConverter implements Converter<String, SortCondition> {
        @Override
        public SortCondition convert(String source) {
            return SortCondition.valueOf(source.toUpperCase());
        }
    }
}
