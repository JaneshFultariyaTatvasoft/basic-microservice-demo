Basic MicroService architecture in Spring boot

- Prerequisite
	Java 17+
	Eclipse/STS IDE
	Apache Maven 3.9.6
	MYSQL Version 8.3.0+

After installing required software run below projects in a sequence

- Start "serviceregistry" project using below command
	mvn spring-boot:run
- Start "authservice" project using below command
	mvn spring-boot:run
- Start "userservice" project using below command	
	mvn spring-boot:run
- Start "gateway" project using below command
	mvn spring-boot:run