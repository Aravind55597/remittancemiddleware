package com.remittancemiddleware.remittancemiddleware;

import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.map.ReceiverMap;
import com.remittancemiddleware.remittancemiddleware.entity.map.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.entity.map.SenderMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RemittancemiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemittancemiddlewareApplication.class, args);
	}



	@Bean
	public CommandLineRunner demoData(CompanyDAO companyDAO , RemittanceMapDAO remittanceMapDAO, UserDAO userDAO) {
		return args -> {

			RemittanceMap remittanceMap = new RemittanceMap();
			ReceiverMap recevierMap = new ReceiverMap();
			SenderMap senderMap = new SenderMap();

			recevierMap.setReceiverType("ReceiverType");
			recevierMap.setReceiverMobileNumber("");
		};
	}

	//	@Bean
	//	public mapperService mapperService(){
	//		return new mapperServiceImpl();
	//	}

}
