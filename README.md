# Getting Started

Hello! Welcome to my approach on the subscription system, in order to explain all of the steps required to get this application working, I'll divide in major points how to set everything up.

### Prior requirements

In order to get this application to work you'll need:

- Gradle: the one used for the creation of this app was gradle-7.3.2
- Java: jdk-1.8.0_202
- Docker cli: the one used for the creation of this app was Docker version 20.10.11, build dea9396 (recommended Docker desktop to keep track of your current containers, images and volumes)

*OPTIONAL*: Postman to test every endpoint, be aware that the service is secure, which will cause you to need to add the _csrf attribute in the body of the request by making a get request to /login, grabbing this value at the bottom which will be something like this:

> < input name="_csrf" type="hidden" value="abcdef-123a-1abc-b456-c1234567890" />

## Database

In order to compile the project we'll need to first create the database, for this we'll navigate to [project_folder]/docker/db and execute the following command

> docker-compose up

This will create the mysql image for our database, create a container and run it accordingly, in order to stop it and start it again we'll have to execute

> docker container ls

Then grab our mysql container's id and execute:

> docker stop containerID
> 
> docker start containerID

## Front, subscription and email microservice container

### Build the app

Once we have our database running we need to run the gradle build task to make our app jar, to do this we'll need to adjust the application properties in order to set the spring.datasource.url to our local IPv4, to do this we'll need to execute depending on our OS:

> ipconfig / ifconfig 

in our command line and find the IPv4 value, it'll be something like this:

> IPv4. . . . . . . . . . . . . . : 192.168.0.123

once we have this IP we'll go to [project_folder]/main/resources/application.properties and edit the spring.datasource.url to our IP value:

> spring.datasource.url=jdbc:mysql://192.168.0.123:9800/TECHCHALLENGE

When this is set, we can execute the gradle build task and we'll see our jar file in [project_folder]/build/libs

### Build the front container

When our jar is built, we need to build the dockerfile inside [project_folder]/dockerfile to create our app's image:

> docker build -f dockerfile -t app .

This will grab the jar inside [project_folder]/build/libs and create an image of our spring boot application, once this is done we can execute the docker command to run our container:

> docker run -p 9900:8080 app

### Start the mail service

Execute the fakeSMTP.jar app so the mail server can be started to be consumed by our application by executing the following command:

> java -jar [project_folder]/utilities/fakeSMTP-2.0.jar

Then click the start server button to start the smtp server.

## And voila!

We will see our app in any web browser when navigating to http://localhost:9900

# Swagger API reference

https://app.swaggerhub.com/apis/osga96/subscription-api/1.0.0