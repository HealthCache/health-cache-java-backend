## Getting Started (Back-End)

### Clone back-end repo
- git clone https://github.com/HealthCache/health-cache-java-backend 

### Implement database instance and provide connection parameters in project configuration
- http://www.h2database.com/html/tutorial.html#using_server

### Host Java application on a localhost enviroment or build and deploy to an AWS EC2
- 3 micro service apps + 1 gateway app + 1 discovery service app
- All 5 Jenkins pipelines point to individual "Jenkinsfile" script in repo
 
![](https://github.com/HealthCache/health-cache-java-backend/blob/main/Resource/JankPipes.png)

### Microservices! (how we did it)
- https://www.baeldung.com/spring-cloud-netflix-eureka
