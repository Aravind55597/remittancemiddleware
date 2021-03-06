package com.remittancemiddleware.remittancemiddleware;

import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import com.github.pemistahl.lingua.api.Language;
import com.remittancemiddleware.remittancemiddleware.dao.*;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.*;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.*;
import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.*;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.*;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import com.github.pemistahl.lingua.api.Language.*;
//https://www.javaguides.net/2018/11/spring-data-jpa-query-creation-from-method-names.html
//spring data jpa reference
@SpringBootApplication
public class RemittancemiddlewareApplication {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(RemittancemiddlewareApplication.class, args);

    }

    //injecting http client for API calls
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    //injecting simpleDateFormat
    @Bean
    SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*");
            }
        };
    }
    @Bean
    public LanguageDetector languageDetector() {
        return  LanguageDetectorBuilder.fromLanguages(Language.ENGLISH,Language.CHINESE).build();
    }

    // Note: Take note of input parameters, before you test
    // Change input mapper object types to the one being tested e.g. EverywhereRemit, and get correct Id of the corresponding transaction before you test

    //ALLOWS YOU TO TEST SERVICES BY RUNNING THE APP -> REMEMBER TO COMMENT IT OUT BEFORE PUSHING IT
//	@Bean
//	@Transactional
//	public CommandLineRunner demoData(SSOTToFinanceNowMapper mapper , RemittanceTransactionDAO rtDAO, ObjectMapper objectMapper, SandboxAPIService sandService){
//		return args -> {
//
//			RemittanceTransaction rt = rtDAO.findById(4).get();
//
//			FinanceNowData result = mapper.MapSSOT(rt);
//
//			System.out.println(result.toString());
//
//			System.out.println(sandService.sendTransactionToSandbox(result,"financenow"));
//
//		};
//	}

//Drop remittance schema and add remittance schema. Before you run the app if you want to add new test data - SupportedCountry can't have duplicate phillipines.

    @Bean
    public CommandLineRunner demoData(RemittanceCompanyDAO remittanceCompanyDAO, RemittanceMapApiDAO remittanceMapApiDAO, SupportedCountryDAO supportedCountryDAO, CompanyDAO companyDAO, RemittanceMapDAO remittanceMapDAO, UserDAO userDAO, RemittanceTransactionDAO remittanceTransactionDAO) {
        return args -> {
            Connection connection = null;
            String url = jdbcUrl;
            Properties connectionProps = new Properties();
            connectionProps.put("user", this.username);
            connectionProps.put("password", this.password);

            connection = DriverManager.getConnection(url, connectionProps);
//			DatabaseMetaData meta = connection.getMetaData();

            try {
                //test if database "remittance" exist
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT *" + "FROM remittance.user");
                ResultSet resultSet = preparedStatement.executeQuery();
                //check if there is data in the user table
                // if there is no data in remittance database, seed test data into database
                if (!resultSet.next()) {
                    System.out.println("There is no data in remittance database -- seeding data now. Please wait for \"Completed initialisation\" message");

                    Company company1 = new Company();
                    User user1 = new User("user1@gmail.com", "abc12345", "Anne", "Tan");
                    User user2 = new User("user2@gmail.com", "abc12345", "Jimmy", "Lee");

                    Company company2 = new Company();
                    User user3 = new User("user3@gmail.com", "abc12345", "Bob", "Tan");
                    User user4 = new User("user4@gmail.com", "abc12345", "Amy", "Lee");

                    Company company3 = new Company();
                    User user5 = new User("user5@gmail.com", "abc12345", "John", "Cena");

                    RemittanceMap remittanceMap = new RemittanceMap();
                    ReceiverMap receiverMap = new ReceiverMap();
                    SenderMap senderMap = new SenderMap();
                    AddressMap addressMapR = new AddressMap();
                    AddressMap addressMapS = new AddressMap();
                    BankAccountMap bankAccountMapR = new BankAccountMap();
                    BankAccountMap bankAccountMapS = new BankAccountMap();
                    IdentificationMap identificationMapR = new IdentificationMap();
                    IdentificationMap identificationMapS = new IdentificationMap();


                    Sender sender = new Sender();
                    Identification identificationS = new Identification();
                    BankAccount bankAccountS = new BankAccount();
                    Address addressS = new Address();

                    Receiver receiver = new Receiver();
                    Identification identificationR = new Identification();
                    BankAccount bankAccountR = new BankAccount();
                    Address addressR = new Address();

                    RemittanceTransaction remittanceTransaction1 = new RemittanceTransaction();
                    RemittanceTransaction remittanceTransaction2 = new RemittanceTransaction();
                    RemittanceTransaction remittanceTransaction3 = new RemittanceTransaction();

                    RemittanceTransaction remittanceTransactionE1 = new RemittanceTransaction();
                    RemittanceTransaction remittanceTransactionE2 = new RemittanceTransaction();
                    RemittanceTransaction remittanceTransactionE3 = new RemittanceTransaction();

                    RemittanceTransaction remittanceTransactionF1 = new RemittanceTransaction();
                    RemittanceTransaction remittanceTransactionF2 = new RemittanceTransaction();
                    RemittanceTransaction remittanceTransactionF3 = new RemittanceTransaction();

                    //start of additional test data - right schema

                    RemittanceCompany remittanceCompanyER = new RemittanceCompany();
                    RemittanceCompany remittanceCompanyFN = new RemittanceCompany();
                    RemittanceCompany remittanceCompanyPG = new RemittanceCompany();

                    SupportedCountry supportedCountryPHL = new SupportedCountry();

                    RemittanceMapApi remittanceMapApiER = new RemittanceMapApi();
                    RemittanceMapApi remittanceMapApiFN = new RemittanceMapApi();
                    RemittanceMapApi remittanceMapApiPG = new RemittanceMapApi();

                    SenderMapApi senderMapApiER = new SenderMapApi();
                    ReceiverMapApi receiverMapApiER = new ReceiverMapApi();
                    SenderMapApi senderMapApiFN = new SenderMapApi();
                    ReceiverMapApi receiverMapApiFN = new ReceiverMapApi();

                    BankAccountMapApi bankAccountMapApiSenderER = new BankAccountMapApi();
                    BankAccountMapApi bankAccountMapApiReceiverER = new BankAccountMapApi();
                    BankAccountMapApi bankAccountMapApiSenderFN = new BankAccountMapApi();
                    BankAccountMapApi bankAccountMapApiReceiverFN = new BankAccountMapApi();

                    IdentificationMapApi identificationMapApiSenderER = new IdentificationMapApi();
                    IdentificationMapApi identificationMapApiReceiverER = new IdentificationMapApi();
                    IdentificationMapApi identificationMapApiSenderFN = new IdentificationMapApi();
                    IdentificationMapApi identificationMapApiReceiverFN = new IdentificationMapApi();

                    AddressMapApi addressMapApiSenderER = new AddressMapApi();
                    AddressMapApi addressMapApiReceiverER = new AddressMapApi();
                    AddressMapApi addressMapApiSenderFN = new AddressMapApi();
                    AddressMapApi addressMapApiReceiverFN = new AddressMapApi();


                    remittanceCompanyER.setRemittanceMapApi(remittanceMapApiER);
                    remittanceCompanyFN.setRemittanceMapApi(remittanceMapApiFN);
                    //			remittanceCompanyPG.setRemittanceMapApi(remittanceMapApiPG);


                    remittanceCompanyER.setRemittanceCompanyName(RemittanceCompanyName.EVERYWHERE_REMIT);
                    remittanceCompanyFN.setRemittanceCompanyName(RemittanceCompanyName.FINANCE_NOW);
                    //			remittanceCompanyPG.setRemittanceCompanyName(RemittanceCompanyName.PAYMENT_GO);

                    remittanceCompanyER.addSupportedCountries(supportedCountryPHL);
                    remittanceCompanyFN.addSupportedCountries(supportedCountryPHL);

                    //paymentGo is not under phillipines
                    //			remittanceCompanyPG.addSupportedCountries(supportedCountryPHL);

                    supportedCountryPHL.setIbanName("PHL");
                    supportedCountryPHL.setName("Philippines");
                    supportedCountryPHL.addRemittanceCompanies(remittanceCompanyER);
                    supportedCountryPHL.addRemittanceCompanies(remittanceCompanyFN);
                    //-------------------------------------------------------------------------------------------------------------
                    //Sender Test data - ER
                    senderMapApiER.setFirstName("sender_legal_name_first");
                    senderMapApiER.setLastName("sender_legal_name_last");
                    senderMapApiER.setNationality("sender_nationality");
                    senderMapApiER.setCurrency("sender_currency");
                    senderMapApiER.setDateOfBirth("sender_date_of_birth");
                    senderMapApiER.setMobileNumber(null);
                    senderMapApiER.setIdentificationMapApi(identificationMapApiSenderER);
                    senderMapApiER.setBankAccountMapApi(bankAccountMapApiSenderER);
                    senderMapApiER.setAddressMapApi(addressMapApiSenderER);
                    senderMapApiER.setSourceOfFunds("source_of_funds");
                    senderMapApiER.setBeneficiaryRelationship(null);
                    //			senderMapApiER.setRemittanceMapApi(remittanceMapApiER);
                    //
                    //			//Receiver Test data - ER
                    receiverMapApiER.setFirstName("recipient_legal_name_first");
                    receiverMapApiER.setLastName("recipient_legal_name_last");
                    receiverMapApiER.setNationality(null);
                    receiverMapApiER.setCurrency("recipient_currency");
                    receiverMapApiER.setDateOfBirth(null);
                    receiverMapApiER.setMobileNumber("recipient_mobile_number");
                    receiverMapApiER.setIdentificationMapApi(identificationMapApiReceiverER);
                    receiverMapApiER.setBankAccountMapApi(bankAccountMapApiReceiverER);
                    receiverMapApiER.setAddressMapApi(addressMapApiReceiverER);
                    receiverMapApiER.setType("recipient_type");
                    //			receiverMapApiER.setRemittanceMapApi(remittanceMapApiER);

                    addressMapApiSenderER.setAddressLine("sender_address_line");
                    addressMapApiSenderER.setCity("sender_address_city");
                    addressMapApiSenderER.setCountry("sender_address_country");
                    addressMapApiSenderER.setState(null);
                    addressMapApiSenderER.setZipCode(null);
                    //			addressMapApiSenderER.setPartyMapApi(senderMapApiER);

                    addressMapApiReceiverER.setAddressLine(null);
                    addressMapApiReceiverER.setCity(null);
                    addressMapApiReceiverER.setCountry("recipient_country");
                    addressMapApiReceiverER.setState(null);
                    addressMapApiReceiverER.setZipCode(null);
                    //			addressMapApiReceiverER.setPartyMapApi(receiverMapApiER);

                    bankAccountMapApiSenderER.setBankName(null);
                    bankAccountMapApiSenderER.setBranchName(null);
                    bankAccountMapApiSenderER.setAccountNumber(null);
                    //			bankAccountMapApiSenderER.setPartyMapApi(senderMapApiER);

                    bankAccountMapApiReceiverER.setBankName(null);
                    bankAccountMapApiReceiverER.setBranchName(null);
                    bankAccountMapApiReceiverER.setAccountNumber("recipient_account_number");
                    //			bankAccountMapApiReceiverER.setPartyMapApi(receiverMapApiER);

                    identificationMapApiSenderER.setIdType("sender_id_type");
                    identificationMapApiSenderER.setIdNumber("sender_id_number");
                    identificationMapApiSenderER.setIssuingCountry("sender_id_country");
                    //			identificationMapApiSenderER.setPartyMapApi(senderMapApiER);

                    identificationMapApiReceiverER.setIdType(null);
                    identificationMapApiReceiverER.setIdNumber(null);
                    identificationMapApiReceiverER.setIssuingCountry(null);
                    //			identificationMapApiReceiverER.setPartyMapApi(receiverMapApiER);

                    remittanceMapApiER.setPurpose("remittance_purpose");
                    remittanceMapApiER.setAmount("units");
                    remittanceMapApiER.setPaymentMode(null);
                    //			remittanceMapApiER.setRemittanceCompany(remittanceCompanyER);
                    remittanceMapApiER.setSourceType("source_type");
                    remittanceMapApiER.setSegment("segment");
                    remittanceMapApiER.setSenderMapApi(senderMapApiER);
                    remittanceMapApiER.setReceiverMapApi(receiverMapApiER);
                    //			remittanceMapApiER.setRemittanceCompany(remittanceCompanyER);

                    // ---------------------------------------------------------------------------------------------------------------
                    //			Sender Test data - FN
                    senderMapApiFN.setFirstName("SenderFirstName");
                    senderMapApiFN.setLastName("SenderLastName");
                    senderMapApiFN.setNationality("SenderNationality");
                    senderMapApiFN.setCurrency(null);
                    senderMapApiFN.setDateOfBirth("SenderDateOfBirth");
                    senderMapApiFN.setMobileNumber(null);
                    senderMapApiFN.setIdentificationMapApi(identificationMapApiSenderFN);
                    senderMapApiFN.setBankAccountMapApi(bankAccountMapApiSenderFN);
                    senderMapApiFN.setAddressMapApi(addressMapApiSenderFN);
                    senderMapApiFN.setSourceOfFunds("SenderSourceOfFund");
                    senderMapApiFN.setBeneficiaryRelationship("SenderBeneficiaryRelationship");
                    //			senderMapApiFN.setRemittanceMapApi(remittanceMapApiFN);
                    //
                    //			//Receiver Test data - FN
                    receiverMapApiFN.setFirstName("ReceiverFirstName");
                    receiverMapApiFN.setLastName("ReceiverLastName");
                    receiverMapApiFN.setNationality("ReceiverNationality");
                    receiverMapApiFN.setCurrency(null);
                    receiverMapApiFN.setDateOfBirth(null);
                    receiverMapApiFN.setMobileNumber(null);
                    receiverMapApiFN.setIdentificationMapApi(identificationMapApiReceiverFN);
                    receiverMapApiFN.setBankAccountMapApi(bankAccountMapApiReceiverFN);
                    receiverMapApiFN.setAddressMapApi(addressMapApiReceiverFN);
                    receiverMapApiFN.setType(null);
                    //			receiverMapApiFN.setRemittanceMapApi(remittanceMapApiFN);

                    addressMapApiSenderFN.setAddressLine("SenderAddress");
                    addressMapApiSenderFN.setCity("SenderCity");
                    addressMapApiSenderFN.setCountry("SenderCountry");
                    addressMapApiSenderFN.setState("SenderState");
                    addressMapApiSenderFN.setZipCode(null);
                    //			addressMapApiSenderFN.setPartyMapApi(senderMapApiFN);

                    addressMapApiReceiverFN.setAddressLine("ReceiverAddress");
                    addressMapApiReceiverFN.setCity("ReceiverCity");
                    addressMapApiReceiverFN.setCountry("ReceiverCountry");
                    addressMapApiReceiverFN.setState(null);
                    addressMapApiReceiverFN.setZipCode(null);
                    //			addressMapApiReceiverFN.setPartyMapApi(receiverMapApiFN);

                    bankAccountMapApiSenderFN.setBankName(null);
                    bankAccountMapApiSenderFN.setBranchName(null);
                    bankAccountMapApiSenderFN.setAccountNumber(null);
                    //			bankAccountMapApiSenderFN.setPartyMapApi(senderMapApiFN);

                    bankAccountMapApiReceiverFN.setBankName(null);
                    bankAccountMapApiReceiverFN.setBranchName(null);
                    bankAccountMapApiReceiverFN.setAccountNumber("BankAccountNumber ");
                    //			bankAccountMapApiReceiverFN.setPartyMapApi(receiverMapApiFN);

                    identificationMapApiSenderFN.setIdType("Identity Type");
                    identificationMapApiSenderFN.setIdNumber("Identity Number");
                    identificationMapApiSenderFN.setIssuingCountry("Country of Issue");
                    //			identificationMapApiSenderFN.setPartyMapApi(senderMapApiFN);

                    identificationMapApiReceiverFN.setIdType("SenderIdType");
                    identificationMapApiReceiverFN.setIdNumber("SenderIdNumber");
                    identificationMapApiReceiverFN.setIssuingCountry(null);
                    //			identificationMapApiReceiverFN.setPartyMapApi(receiverMapApiFN);

                    remittanceMapApiFN.setPurpose("PurposeOfRemittance");
                    remittanceMapApiFN.setAmount(null);
                    remittanceMapApiFN.setPaymentMode("PaymentMode");
                    //			remittanceMapApiFN.setRemittanceCompany(remittanceCompanyFN);
                    remittanceMapApiFN.setSourceType(null);
                    remittanceMapApiFN.setSegment(null);
                    remittanceMapApiFN.setSenderMapApi(senderMapApiFN);
                    remittanceMapApiFN.setReceiverMapApi(receiverMapApiFN);
                    //			remittanceMapApiFN.setRemittanceCompany(remittanceCompanyFN);


                    //
                    //			remittanceMapApiPG.setPurpose("Remittance Purpose");
                    //			remittanceMapApiPG.setAmount("merTransAmount");
                    //			remittanceMapApiPG.setPaymentMode("Mode of Payment");
                    //			remittanceMapApiPG.setRemittanceCompany(remittanceCompanyPG);
                    //			remittanceMapApiPG.setSourceType("Source of Funds");
                    //			remittanceMapApiPG.setSegment("");
                    //			remittanceMapApiPG.setSenderMapApi(senderMapApi);
                    //			remittanceMapApiPG.setReceiverMapApi(receiverMapApi);
                    //			remittanceMapApiPG.setRemittanceCompany(remittanceCompanyPG);

                    //


                    supportedCountryDAO.save(supportedCountryPHL);

                    remittanceCompanyDAO.save(remittanceCompanyER);
                    remittanceCompanyDAO.save(remittanceCompanyFN);
                    //			remittanceCompanyDAO.save(remittanceCompanyPG);


                    remittanceMapApiDAO.save(remittanceMapApiER);
                    remittanceMapApiDAO.save(remittanceMapApiFN);
                    //			remittanceMapApiDAO.save(remittanceMapApiPG);


                    //end of additional Test Data - right schema


                    //			user1.setEmail("user1@gmail.com");
                    //			user1.setPassword("abc12345");
                    //			user1.setFirstName("Anne");
                    //			user1.setLastName("Tan");
                    user1.setCompany(company1);

                    //			user2.setEmail("user2@gmail.com");
                    //			user2.setPassword("abc12345");
                    //			user2.setFirstName("Jimmy");
                    //			user2.setLastName("Lee");
                    user2.setCompany(company1);

                    company1.setCompanyName("Sunshine PTE LTD");
                    company1.addUser(user1);
                    company1.addUser(user2);
                    // remittance trans

                    user3.setCompany(company2);
                    user4.setCompany(company2);

                    company2.setCompanyName("NFT PTE LTD");
                    company2.addUser(user3);
                    company2.addUser(user4);

                    user5.setCompany(company3);

                    company3.setCompanyName("ToMoon PTE LTD");
                    company3.addUser(user5);

                    identificationS.setIdNumber("9578660");
                    //			identificationS.setParty(sender);
                    identificationS.setIdType(IdType.NATIONAL_ID);
                    identificationS.setIssuingCountry("Singapore");

                    bankAccountS.setBankName("OCBC");
                    bankAccountS.setBranchName("Bedok North");
                    bankAccountS.setAccountNumber("627819930");
                    //			bankAccountS.setParty(sender);

                    addressS.setAddressLine("Block 80 Bedok North");
                    addressS.setCity("Singapore");
                    addressS.setCountry("SGP");
                    addressS.setState("Singapore");
                    addressS.setZipCode(460080);
                    //			addressS.setParty(sender);

                    sender.setFirstName("Tammy");
                    sender.setLastName("Low");
                    sender.setNationality("SGP");
                    sender.setCurrency("SGD");
                    sender.setDateOfBirth(new GregorianCalendar(1995, Calendar.DECEMBER, 30).getTime());
                    sender.setMobileNumber("81234567");
                    sender.setIdentification(identificationS);
                    sender.setBankAccount(bankAccountS);
                    sender.setAddress(addressS);
                    sender.setSourceOfFunds(SourceOfFunds.SALARY);
                    sender.setBeneficiaryRelationship(BeneficiaryRelationship.FAMILY);

                    identificationR.setIdNumber("45890082");
                    //			identificationR.setParty(receiver);
                    identificationR.setIdType(IdType.NATIONAL_ID);
                    identificationR.setIssuingCountry("Philippines");

                    bankAccountR.setBankName("Bank of the Philippine Islands");
                    bankAccountR.setBranchName("Manila");
                    bankAccountR.setAccountNumber("78294291");
                    //			bankAccountR.setParty(receiver);

                    addressR.setAddressLine("No. 224 Pairaso St");
                    addressR.setCity("Makati");
                    addressR.setCountry("PHL");
                    addressR.setState("Manila");
                    addressR.setZipCode(1103);
                    //			addressR.setParty(receiver);

                    receiver.setFirstName("Mikayla");
                    receiver.setLastName("Lim");
                    receiver.setNationality("PHL");
                    receiver.setCurrency("PHP");
                    receiver.setDateOfBirth(new GregorianCalendar(1955, Calendar.JANUARY, 24).getTime());
                    receiver.setMobileNumber("822489290");
                    receiver.setIdentification(identificationR);
                    receiver.setBankAccount(bankAccountR);
                    receiver.setAddress(addressR);
                    receiver.setType("bank_account");

                    senderMap.setFirstName("SenderFirstName");
                    senderMap.setLastName("SenderLastName");
                    senderMap.setNationality("SenderNationality");
                    senderMap.setCurrency("Receiver Currency");
                    senderMap.setDateOfBirth("SenderDateofBirth");
                    //			senderMap.setMobileNumber("Phone Number");
                    senderMap.setIdentificationMap(identificationMapS);
                    senderMap.setBankAccountMap(bankAccountMapS);
                    senderMap.setAddressMap(addressMapS);
                    senderMap.setSourceOfFunds("SenderSourceofFund");
                    senderMap.setBeneficiaryRelationship("SenderRelationship");
                    //			senderMap.setRemittanceMap(remittanceMap);

                    receiverMap.setFirstName("ReceiverFirstName");
                    receiverMap.setLastName("ReceiverLastName");
                    receiverMap.setNationality("ReceiverNationality");
                    receiverMap.setCurrency("Receiver Currency");
                    receiverMap.setDateOfBirth("ReceiverDateofBirth");
                    //			receiverMap.setMobileNumber("Phone Number");
                    receiverMap.setIdentificationMap(identificationMapR);
                    receiverMap.setBankAccountMap(bankAccountMapR);
                    receiverMap.setAddressMap(addressMapR);
                    //			receiverMap.setType("");
                    //			receiverMap.setRemittanceMap(remittanceMap);

                    addressMapR.setAddressLine("ReceiverAddress");
                    addressMapR.setCity("ReceiverCity");
                    addressMapR.setCountry("ReceiverCountry");
                    //			addressMapR.setState("State");
                    //			addressMapR.setZipCode("Postal Code");
                    //			addressMapR.setPartyMap(receiverMap);

                    addressMapS.setAddressLine("SenderAddress");
                    addressMapS.setCity("SenderCity");
                    addressMapS.setCountry("SenderCountry");
                    addressMapS.setState("SenderState");
                    //			addressMapS.setZipCode("Postal Code");
                    //			addressMapS.setPartyMap(senderMap);

                    //			bankAccountMapR.setBankName("Bank");
                    //			bankAccountMapR.setBranchName("Branch");
                    bankAccountMapR.setAccountNumber("ReceiverAccountNumber");
                    //			bankAccountMapR.setPartyMap(receiverMap);

                    bankAccountMapS.setBankName("Bank");
                    bankAccountMapS.setBranchName("Branch");
                    bankAccountMapS.setAccountNumber("Account No");
                    //			bankAccountMapS.setPartyMap(senderMap);

                    identificationMapR.setIdType("ReceiverIDType");
                    identificationMapR.setIdNumber("ReceiverIDNumber");
                    //			identificationMapR.setIssuingCountry("Country of Issue");
                    //			identificationMapR.setPartyMap(receiverMap);

                    identificationMapS.setIdType("SenderIDType");
                    identificationMapS.setIdNumber("SenderIDNumber");
                    identificationMapS.setIssuingCountry("SenderIDIssuingCountry");
                    //			identificationMapS.setPartyMap(senderMap);

                    remittanceMap.setPurpose("PurposeofRemittance");
                    remittanceMap.setAmount("ReceivingAmount");
                    remittanceMap.setPaymentMode("PaymentMode");
                    remittanceMap.setSourceType("SourceType");
                    remittanceMap.setSegment("Segment");
                    remittanceMap.setSenderMap(senderMap);
                    remittanceMap.setReceiverMap(receiverMap);
                    remittanceMap.setCompany(company1);
                    remittanceMap.setDestinationCountry("PHL");


                    remittanceTransaction1.setPurpose(RemittancePurpose.EDUCATION);
                    remittanceTransaction1.setAmount(8250L);
                    remittanceTransaction1.setRemittanceCompany(RemittanceCompanyName.PAYMENT_GO);
                    remittanceTransaction1.setSourceType("Loan");
                    remittanceTransaction1.setSegment("");
                    remittanceTransaction1.setPaymentMode("Cheque");
                    remittanceTransaction1.setTransactionStatus(TransactionStatus.PENDING_AML);
                    remittanceTransaction1.setSender(sender);
                    remittanceTransaction1.setReceiver(receiver);
                    remittanceTransaction1.setCompany(company1);

                    remittanceTransaction2.setPurpose(RemittancePurpose.FAMILY_EXPENSES);
                    remittanceTransaction2.setAmount(6500L);
                    remittanceTransaction2.setRemittanceCompany(RemittanceCompanyName.PAYMENT_GO);
                    remittanceTransaction2.setSourceType("Allowance");
                    remittanceTransaction2.setSegment("");
                    remittanceTransaction2.setPaymentMode("Cash");
                    remittanceTransaction2.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    remittanceTransaction2.setSender(sender);
                    remittanceTransaction2.setReceiver(receiver);
                    remittanceTransaction2.setCompany(company1);

                    remittanceTransaction3.setPurpose(RemittancePurpose.TRAVEL_EXPENSES);
                    remittanceTransaction3.setAmount(6200L);
                    remittanceTransaction3.setRemittanceCompany(RemittanceCompanyName.PAYMENT_GO);
                    remittanceTransaction3.setSourceType("Savings");
                    remittanceTransaction3.setSegment("");
                    remittanceTransaction3.setPaymentMode("Bank Transfer");
                    remittanceTransaction3.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
                    remittanceTransaction3.setSender(sender);
                    remittanceTransaction3.setReceiver(receiver);
                    remittanceTransaction3.setCompany(company1);

                    remittanceTransactionE1.setPurpose(RemittancePurpose.INVESTMENT);
                    remittanceTransactionE1.setAmount(3870L);
                    remittanceTransactionE1.setRemittanceCompany(RemittanceCompanyName.EVERYWHERE_REMIT);
                    remittanceTransactionE1.setSourceType("Savings");
                    remittanceTransactionE1.setSegment("Segment");
                    remittanceTransactionE1.setPaymentMode("Bank Transfer");
                    remittanceTransactionE1.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
                    remittanceTransactionE1.setSender(sender);
                    remittanceTransactionE1.setReceiver(receiver);
                    remittanceTransactionE1.setCompany(company1);

                    remittanceTransactionE2.setPurpose(RemittancePurpose.PERSONAL_ASSET_ALLOCATION);
                    remittanceTransactionE2.setAmount(5545L);
                    remittanceTransactionE2.setRemittanceCompany(RemittanceCompanyName.EVERYWHERE_REMIT);
                    remittanceTransactionE2.setSourceType("Salary");
                    remittanceTransactionE2.setSegment("Segment");
                    remittanceTransactionE2.setPaymentMode("Bank Transfer");
                    remittanceTransactionE2.setTransactionStatus(TransactionStatus.PENDING_AML);
                    remittanceTransactionE2.setSender(sender);
                    remittanceTransactionE2.setReceiver(receiver);
                    remittanceTransactionE2.setCompany(company1);

                    remittanceTransactionE3.setPurpose(RemittancePurpose.FAMILY_EXPENSES);
                    remittanceTransactionE3.setAmount(3750L);
                    remittanceTransactionE3.setRemittanceCompany(RemittanceCompanyName.EVERYWHERE_REMIT);
                    remittanceTransactionE3.setSourceType("Salary");
                    remittanceTransactionE3.setSegment("Segment");
                    remittanceTransactionE3.setPaymentMode("Cash");
                    remittanceTransactionE3.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    remittanceTransactionE3.setSender(sender);
                    remittanceTransactionE3.setReceiver(receiver);
                    remittanceTransactionE3.setCompany(company1);

                    remittanceTransactionF1.setPurpose(RemittancePurpose.CHARITY_DONATION);
                    remittanceTransactionF1.setAmount(250L);
                    remittanceTransactionF1.setRemittanceCompany(RemittanceCompanyName.FINANCE_NOW);
                    remittanceTransactionF1.setSourceType("Savings");
                    remittanceTransactionF1.setSegment("");
                    remittanceTransactionF1.setPaymentMode("Cash");
                    remittanceTransactionF1.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    remittanceTransactionF1.setSender(sender);
                    remittanceTransactionF1.setReceiver(receiver);
                    remittanceTransactionF1.setCompany(company1);

                    remittanceTransactionF2.setPurpose(RemittancePurpose.PAYMENT_FOR_GOODS);
                    remittanceTransactionF2.setAmount(999L);
                    remittanceTransactionF2.setRemittanceCompany(RemittanceCompanyName.FINANCE_NOW);
                    remittanceTransactionF2.setSourceType("Salary");
                    remittanceTransactionF2.setSegment("");
                    remittanceTransactionF2.setPaymentMode("Bank Transfer");
                    remittanceTransactionF2.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
                    remittanceTransactionF2.setSender(sender);
                    remittanceTransactionF2.setReceiver(receiver);
                    remittanceTransactionF2.setCompany(company1);

                    remittanceTransactionF3.setPurpose(RemittancePurpose.PAYMENT_FOR_SERVICES);
                    remittanceTransactionF3.setAmount(3500L);
                    remittanceTransactionF3.setRemittanceCompany(RemittanceCompanyName.FINANCE_NOW);
                    remittanceTransactionF3.setSourceType("Salary");
                    remittanceTransactionF3.setSegment("");
                    remittanceTransactionF3.setPaymentMode("Bank Transfer");
                    remittanceTransactionF3.setTransactionStatus(TransactionStatus.REJECTED);
                    remittanceTransactionF3.setSender(sender);
                    remittanceTransactionF3.setReceiver(receiver);
                    remittanceTransactionF3.setCompany(company1);

                    company1.addRemittanceTransaction(remittanceTransaction1);
                    company1.addRemittanceTransaction(remittanceTransaction2);
                    company1.addRemittanceTransaction(remittanceTransaction3);

                    company1.addRemittanceTransaction(remittanceTransactionE1);
                    company1.addRemittanceTransaction(remittanceTransactionE2);
                    company1.addRemittanceTransaction(remittanceTransactionE3);

                    company1.addRemittanceTransaction(remittanceTransactionF1);
                    company1.addRemittanceTransaction(remittanceTransactionF2);
                    company1.addRemittanceTransaction(remittanceTransactionF3);

                    ////			company1.setRemittanceMap(remittanceMap);
                    company1.addRemittanceMaps(remittanceMap);


                    companyDAO.save(company1);
                    companyDAO.save(company2);
                    companyDAO.save(company3);

                    userDAO.save(user1);
                    userDAO.save(user2);
                    userDAO.save(user3);
                    userDAO.save(user4);
                    userDAO.save(user5);

                    //			remittanceMapDAO.save(remittanceMap);

                    remittanceTransactionDAO.save(remittanceTransaction1);
                    remittanceTransactionDAO.save(remittanceTransaction2);
                    remittanceTransactionDAO.save(remittanceTransaction3);

                    remittanceTransactionDAO.save(remittanceTransactionE1);
                    remittanceTransactionDAO.save(remittanceTransactionE2);
                    remittanceTransactionDAO.save(remittanceTransactionE3);

                    remittanceTransactionDAO.save(remittanceTransactionF1);
                    remittanceTransactionDAO.save(remittanceTransactionF2);
                    remittanceTransactionDAO.save(remittanceTransactionF3);


                } else {
                    System.out.println("Test data ready");
                }



            } catch (Exception e) {
                System.out.println(e.getMessage());
                // database "remittance" doesn't exist
//				if (e.getMessage().equals("Unknown database 'remittance'") ){

//				}
            }


        };
    }




}
