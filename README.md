Basic MicroService architecture in Spring boot

- Prerequisite
	- Java 17+
	- Eclipse/STS IDE
	- Apache Maven 3.9.6
	- MYSQL Version 8.3.0+

Start MYSQL server locally with username "root" and password "root"

Add MYSQL DB as "auth-db"

After installing required softwares run below projects in a sequence

- Start "serviceregistry" project in "serviceregistry" folder using below command
	- _mvn spring-boot:run_
- Start "authservice" project in "authservice" folder using below command
	- _mvn spring-boot:run_
- Start "userservice" project in "userservice" folder using below command	
	- _mvn spring-boot:run_
- Start "gateway" project in "gateway" folder using below command
	- _mvn spring-boot:run_

References:
- https://www.youtube.com/watch?v=_PQd6aZ-ANk
- https://www.baeldung.com/spring-cloud-netflix-eureka
- https://www.codejava.net/frameworks/spring/understand-spring-data-jpa-with-simple-example
