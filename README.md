# UP 42 coding challenge
This application is a personal implementation for the UP42 code challenge.

## Running the application
### Prerequisites
- java 11+
- docker (mongodb)

### Running the application from command line
The below procedures assumes you have the `bash` interpreter in your machine.

To run the application from the command line you can use the file `start.sh` located in the application's root directory.

In order to use it, change to the application's folder in the command line with below command:

`cd <application_foder>`

In order to use it, you first need to grant execution permission in the script. You can do that with the following command:

`chmod +x ./start.sh`

As last step, just execute the script:

`./start.sh`

The above script will take care of building, testing, creating the mongodb container and launching the application using the port `8080`.

### Running the application from IDE
Before starting the application from the IDE, you first need to run the mongodb container.
From a terminal windows you can accomplish that with the command below:

`docker run -d -p 27017:27017 --name up42-mongo-instance mongo:4.4.2`

Check if the container is up and running with the command below:

`docker ps | grep up42-mongo-instance`

### Swagger-UI
After properly starting the application, you can see the Api Documentation opening the below URL in a web browser:

http://localhost:8080/swagger-ui/