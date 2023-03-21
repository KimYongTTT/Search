package com.example.search.api.config;

import com.example.search.api.constants.SearchConstants;
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

    class StringToSortConditionConverter implements Converter<String, SearchConstants.SortCondition> {
        @Override
        public SearchConstants.SortCondition convert(String source) {
            return SearchConstants.SortCondition.valueOf(source.toUpperCase());
        }
    }
}
