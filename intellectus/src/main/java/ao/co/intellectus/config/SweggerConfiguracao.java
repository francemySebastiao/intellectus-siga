package ao.co.intellectus.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SweggerConfiguracao {
 	
//todas as configurações do swegger ui	

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
				   .select()			  
				   .apis(RequestHandlerSelectors.basePackage("ao.co.intellectus"))
				   .paths(PathSelectors.ant("/**"))
				   .build();

    }
}
