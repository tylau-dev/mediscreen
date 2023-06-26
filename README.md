# mediscreen

Mediscreen is a multiple containers app for managing patient's note and alerting.

# Architecture Diagram
![SP09](https://github.com/tylau-dev/mediscreen/assets/62340191/ca9c48d6-c561-49a2-a6ae-e83d019c4a31)

# Requirements
- JDK 17.0.1
- Apache Maven 4.0.0

# Installation
1) Use git command to clone the project or download the project as a Zip file.
```bash
git clone https://github.com/tylau-dev/mediscreen.git
```

2) In each directory (patient, note, alert, front), build each microservice :
```bash
mvn package
```

3) In the root directory, create and run the containers 
```bash
docker compose up -d
```

4) Initialize the SQL database by running the queries in the "query.sql" file in the root directory.
The default credentials are 'root' for both the username and the password


5) To access the microservices locally, connect to the localhost with the following port :
| Microservice | Port        |
|---------|------------------|
| 8081    | Patient REST API |
| 8082    |  Note REST API  |
| 8083    |  Alert REST API    |
| 8084    |  Front-end |
