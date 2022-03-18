package com.remittancemiddleware.remittancemiddleware;

import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.map.ReceiverMap;
import com.remittancemiddleware.remittancemiddleware.entity.map.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.entity.map.SenderMap;
import com.remittancemiddleware.remittancemiddleware.service.SandboxAPIService;
import okhttp3.OkHttpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RemittancemiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemittancemiddlewareApplication.class, args);
	}



	//injecting http client for API calls
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
	}

//	@Bean
//	public ObjectMapper okHttpClient() {
//		return new OkHttpClient();
//	}
//

//	@Bean
//	public CommandLineRunner demoData(SandboxAPIService sandboxAPIService) {
//		return args -> {
////
////			Company company = new Company();
////
////			User user = new User();
////
////			user.setFirstName("TestUser");
////
////			company.setCompanyName("TestCompany");
////
////			company.addUser(user);
////
////			companyDAO.save(company);
////
//
//			//test connection
//			System.out.println(sandboxAPIService.testConnection());
//
//			//test authntication
//			System.out.println(sandboxAPIService.authenticate());
//
//			//test
//
//
//		};
//	}

	//	@Bean
	//	public mapperService mapperService(){
	//		return new mapperServiceImpl();
	//	}

}
