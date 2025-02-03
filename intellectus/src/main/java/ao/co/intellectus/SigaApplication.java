
package ao.co.intellectus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*
 * API VERSÃO SEM SEGURANÇA PARA DESENVOLVIMENTO.
 * 19-12-2018
 *   
 *      
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class SigaApplication extends SpringBootServletInitializer{   
	
	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SigaApplication.class);
    }
	
	public static void main(String[] args) { 
		SpringApplication.run(SigaApplication.class, args);
	}
	
	
}   