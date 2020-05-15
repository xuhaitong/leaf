package ofileconvert;

import ofileconvert.factory.OpenOfficeConnectionFactory;
import ofileconvert.service.ConvertOFile2PDFService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
