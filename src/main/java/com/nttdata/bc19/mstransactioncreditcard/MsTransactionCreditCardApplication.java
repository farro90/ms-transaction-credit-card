package com.nttdata.bc19.mstransactioncreditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsTransactionCreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTransactionCreditCardApplication.class, args);
	}

}
