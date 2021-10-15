package br.com.orange.contadigital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ContadigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContadigitalApplication.class, args);
	}

}
