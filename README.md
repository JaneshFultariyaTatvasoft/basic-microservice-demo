Basic MicroService architecture in Spring boot

- Prerequisite
	- Java 17+
	- Eclipse/STS IDE
	- Apache Maven 3.9.6
	- MYSQL Version 8.3.0+

Start MYSQL server locally with username "root" and password "root"

Add MYSQL DB as "auth-db"

After installing required software run below projects in a sequence

- Start "serviceregistry" project in "serviceregistry" folder using below command
	mvn spring-boot:run
- Start "authservice" project in "authservice" folder using below command
	mvn spring-boot:run
- Start "userservice" project in "userservice" folder using below command	
	mvn spring-boot:run
- Start "gateway" project in "gateway" folder using below command
	mvn spring-boot:run
