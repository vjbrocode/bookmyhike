# bookmyhike

This is REST-based Backend application written in Java using Springboot that will allow hikers to perform the following tasks:  
1.View all the trails available for hiking  
2.View a selected trail  
3.Book a selected trail for hiking  
4.View a booking  
5.Cancel a booking 

Tech Stack:
- Springboot (v 2.5.4)  
- Java (v 11.0.12)  
- Postgres DB (v 9.5)  
- Cucumber (v 6.10.4)  
- Maven (v 3.8.2)  
- Docker (v 20.10.8) and Docker Compose (v 3.8) 

## Run as docker containers in three simple steps
### Step 1: 
Goto project root directory (where pom.xml, docker-compose.yml, and Dockerfile are present).    
```sh
cd {path}   
```
### Step 2:  
[Note: target directory already have bookmyhike.jar file to be used in docker image]
Run in foreground.  
```sh
docker-compose up
```  
OR Run in Background  
```sh
docker-compose up -d
```
### Step 3:  
Access Swagger-ui   
http://localhost:8080/swagger-ui/index.html#/    
NOTE: Refer ApiDocumentation.txt for payload references. Few sample bookings are preloaded in DB for testing (Refer LoadDatabase.java)

### Stop and remove containers
```sh
docker-compose down  
```

### Run BDD tests:  
(features/scenarios are listed in src/test/resources/features directory)  
```sh
mvn test  
```

### Production Readiness
App is already written with production readiness in mind. It covers following aspects well.  
- Code is feature ready  
- Code is readable/maintainable/modular  
- Code is testable  
- Code is handling errors and doing data validations  
- BDD testing is included which is super helpful in reproducing production bugs. (refer cucumber-report.html in target directory) 
- Logging is included  
- Containerised  
- New data source(like MongoDB etc) can be plugged in with minimal changes.  
- REST API with hypermedia-driven outputs (Not just RPC endpoints)   
- Retries implemented for DB Connections (Refer DataSourceConfig.java).  

Scope of improvement:  
- More testcase coverage (Currently BDD testing is written for all positive scenarios).  
- More testing for corner cases etc.  

# What else?

### Rebuild the source code to generate new bookmyhike jar
Pre-requisite:
- Java (v 11.0.12)  
- Maven (v 3.8.2)
```sh
mvn clean install  
```

### Build single docker image  
If you want to run Api in container and use remote/local DB.
Edit application.properties file and modify "spring.datasource.url" value  
```sh
mvn clean install  
docker build -t bookmyhike:1.0 .  
docker images (to verify)
```

### Run as container
```sh
docker run -d --name hikebookapp -p 8080:8080 bookmyhike:1.0   
docker ps -a (to verify)
```

### Build and save docker image using pre-included shell script
```sh
chmod +x build_docker_image.sh   
./build_docker_image.sh   
docker images (to verify)
```

### Export docker image as tar file to remote use
```sh
docker save -o bookmyhike-1.0.tar bookmyhike:1.0
```

### Run on remote docker server
copy hikebookapp.tar file to remote server  
```sh
docker load -i bookmyhike_1.0.tar   
docker images   
docker run -d --name hikebookapp -p 8080:8080 bookmyhike:1.0   
docker ps -a   
```

### Check app container logs
```sh
docker logs hikebookapp  
```

### Contact Author
Vijay Pratap Singh | vijayps@outlook.com
