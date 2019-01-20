package com.bidder.mx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.bidder.mx" })
public class ApplicationConfiguration implements WebMvcConfigurer {
}
