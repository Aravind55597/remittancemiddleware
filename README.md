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

4. Run the frontend by running the command "npm serve" in the "G2_T6_OOP_Project/frontend" <br /><br />

5. The setup is now complete and you can now access the application. <br />

6. List of Input Validation Scenarios & Steps <br />
   Scenarios | Steps | Expected Output
   ------------- | ------------- | -------------
   Issuing country letter code must be within 3 letters | Upload invalid data using "SMU DUMMY DATA (Issuing Country VALIDATION - within 3 CHARACTERS).csv"  | Invalid Input
   Id Number must be numeric | Upload invalid data using "SMU DUMMY DATA (IdNumber VALIDATION - only NUMERIC).csv" to the DRP  | Invalid Input
   Account number must be numeric | Upload invalid data using "SMU DUMMY DATA (Account Number VALIDATION - only NUMERIC).csv" to the DRP | Invalid Input
   Amount must be double or integer string | Upload invalid data using "SMU DUMMY DATA (Amount VALIDATION - only DOUBLE or INTEGER).csv" to the DRP | Invalid Input
   First name and last name must be in english | Upload invalid data using "SMU DUMMY DATA (FirstName LastName VALIDATION - only ENGLISH).csv" to the DRP | Invalid Input

	
