# JavaCodingTest
## UML Diagram
![ApplicationOverview](https://user-images.githubusercontent.com/45225697/128135905-272fcd4c-6d26-46ff-8d38-78b46d8a90d1.png)

## Assumptions
### Some Assumptions during the API development were made including:
1. No database is needed - a query is sent from another service/microservice and the payslips will be returned to them as JSON
2. Data validation not required - another microservice will already have an existing database and knowledge of the format to query this microservice with.

## AWS URL
1. The application is currently hosted on AWS' Fargate through an ECR docker image at http://3.106.54.184:8080/generate
2. The application requires a body to return anything, this can be done through an API tool such as Postman - https://www.postman.com/
3. Use a get request and hitting the above mentioned endpoint with a body formatted like below in the raw format

![image](https://user-images.githubusercontent.com/45225697/128113269-1db41053-5387-4eec-8dc0-be22886e1eac.png)

4 . The API should send a response body such as the one below

![image](https://user-images.githubusercontent.com/45225697/128113363-0e26efa6-0f9e-4953-a7e8-fc7fb2591176.png)

## Docker Deployment.
### Note: requires docker (hosted at https://hub.docker.com/repository/docker/adamcoppens/coding-test-app)
1. docker pull adamcoppens/coding-test-app
2. docker run -p 8080:8080 adamcoppens/coding-test-app
3. send json bodies as shown in other examples to http://localhost:8080/generate

## Local IDE Deployment
1. Run Program from CodingTestApplication.java Start Point (Backend currently runs on localhost:8080)
2. Using an API testing tool such as Postman send JSON objects as a GET request formatted like below through the body to the endpoint: http://localhost:8080/generate

 ![image](https://user-images.githubusercontent.com/45225697/127952515-809ad75c-b4c6-4c3e-8d52-ab6bc1966767.png)
 
3. Verify Results <br />
![image](https://user-images.githubusercontent.com/45225697/127952367-ed0b18aa-2b39-4ad1-8e8a-388ec14c4544.png)
