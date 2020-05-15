package com.leaf.file.ofileconvertPdf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leaf.file.ofileconvertPdf.factory.OpenOfficeConnectionFactory;
import com.leaf.file.ofileconvertPdf.service.ConvertOFile2PDFService;

@Configuration
public class OpenOfficeFileConvertAutoConfiguration {

	@Bean
	public OpenOfficeConnectionFactory connectionFactory() {
		return new OpenOfficeConnectionFactory();
	}

	@Bean
	public ConvertOFile2PDFService convertOFile2PDFService() {
		return new ConvertOFile2PDFService();
	}
}
