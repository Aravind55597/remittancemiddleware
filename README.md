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
			 
3. Open the react app on port 3000 <br /><br />
4. List of scenarios & steps  <br />
	Scenarios  		| Steps
	------------- | -------------
	Content Cell  | Content Cell
	Content Cell  | Content Cell

	
