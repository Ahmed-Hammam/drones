# drones
 
 Build steps:
 Go to the project directory and follow these steps
 1) mvn clean install
 2) docker build -t musala-drones .
 3) docker run -d -p8080:8080 musala-drones:latest
 4) for swagger ui : (ex) http://localhost:8080/swagger-ui/index.html#/drones-controller