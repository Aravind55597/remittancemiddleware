# remittancemiddleware

This a Remittance Middleware Programme by G1 Group 6 


## Set-up Process 

1. Firstly, add the following environment variables into the system environment 
   values for cloud database & Sandbox API is present in a separate env variables file  <br />
	Name   				| 		Value
	------------- | -------------
	CODE_ENVIRONMENT  	| prod
	HIBERNATE_DDL_AUTO  | update
	SERVER_PORT | 5000
	RDS_HOSTNAME | localhost
	RDS_DB_NAME | remittance 
	RDS_USERNAME | root
	RDS_PASSWORD | 
	RDS_PORT | 3306
	SANDBOX_BASE_URL | CHECK env.txt
	SANDBOX_PASSWORD | CHECK env.txt
	SANDBOX_USERNAME | CHECK env.txt
	
2. If AWS RDS is used for evaluation , skip this step. If localhost , please read the following <br />
		a. Open MySQL workbench  <br />
		b. Create a new connection & create a new schema "remittance"  <br /> 
		c. Run compile.bat <br />
		e. Tables will be automatically created & data will be automatically seeded <br />
       a) Wait for "There is no data in remittance database -- seeding data now.<br />
       b) Please wait for "completed initialisation" message" , data has been seeded <br /><br />
			 
3. Run the backend by running the RemittancemiddlewareApplication which is located at "G2_T6_OOP_Project/backend/remittancemiddleware/src/main/java/RemittancemiddlewareApplication.java". <br />

4. Run the frontend by running the command "npm serve" in the "G2_T6_OOP_Project/frontend" <br />

5. The setup is now complete and you can now access the application. <br />

6. List of Input Validation Scenarios & Steps <br />
   Scenarios | Steps | Expected Result
   ------------- | ------------- | -------------
   Issuing country letter code must be within 3 letters | Upload invalid data using "SMU DUMMY DATA (Issuing Country VALIDATION - within 3 CHARACTERS).csv" to the DRP  | Invalid Input
   Id Number must be numeric | Upload invalid data using "SMU DUMMY DATA (IdNumber VALIDATION - only NUMERIC).csv" to the DRP  | Invalid Input
   Account number must be numeric | Upload invalid data using "SMU DUMMY DATA (Account Number VALIDATION - only NUMERIC).csv" to the DRP | Invalid Input
   Amount must be double or integer string | Upload invalid data using "SMU DUMMY DATA (Amount VALIDATION - only DOUBLE or INTEGER).csv" to the DRP | Invalid Input
   First name and last name must be in english | Upload invalid data using "SMU DUMMY DATA (FirstName LastName VALIDATION - only ENGLISH).csv" to the DRP | Invalid Input

7. Overview of Classes in UML Diagram
<br/>

Company Class
- Represents a SME Company Entity that uses the DRP
- Is composed of its own Remittance Transactions submitted and Remittance Mappings in the RemittanceMap Class
<br/>

User Class
- Represents the details relating to a DRP User that is related to a SME Company Entity
- User is able to access information (e.g. transactions, mapping) if user is associated with a SME Company Entity in the database system
<br/>

Remittance Map Class
- Represents the SME company's mapping based on SSOT
- Remittance Map Class is composed of its associated Map related classes such as ReceiverMap, SenderMap, etc. as displayed in the UML.
- Each attribute of the Map related class is mapped to its corresponding SSOT attribute, where the name of the attribute represents the SSOT attribute name, and the corresponding value represents the SME Company's attribute name, which was mapped previously during the Company's onboarding process.
<br/>

Remittance Transaction Class
- Represents the SME company's transactions associated
- Contains transaction information in addition to the details relating to the associated Sender and Receiver.
- Contains transaction status to display validity of transactions being sent 
<br/>

Sender/Receiver Class
- represents sender/receiver in a remittance transaction
- Inherits a common abstract class called "Party", which would have BankAccount, Identification and Address, as both Sender and Receiver have the same common attributes
<br/>

SupportedCountry Class
- help represent a Supported Remittance Country e.g. PHL, CHN
- helps fulfill functionality of getting a common SSOT Remittance Map based on the country being specified (e.g. PHL Map --> Mathematical Union all company mappings in PHL - Company A ∪ Company B U ...)
- Is associated with multiple remittance company entities
<br/>

RemittanceCompany Class
- represents a Remittance Company rendering remittance services through their API
- is associated with supported countries
- has Mapping stored in RemittanceMapApi (not RemittanceMap)
<br/>

RemittanceMapApi Class
- stores Mapping for each RemmittanceCompany
- performs same function as RemittanceMap, but only stores mapping associated for each RemittanceCompany
- prevent possible errors from having remittance mappings being mixed and grouped together
- Has nested classes similar to RemittanceMap in the form of SenderMapApi, ReceiverMapApi, etc.




	
