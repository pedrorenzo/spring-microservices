
# Spring Microservices
A project that I created to learn and practice some concepts and technologies related to microservices architecture.

## Should you use microservices architecture for your project?
Challenges of working with microservices:
- Defining the boundaries of a microservice can be very challenging if we don't have a good business knowledge of that application.
- Working with microservices can be difficult due to the configuration factor, as we will have several microservices with several instances, this can be a problem.
- We need to dynamically take care of the microservices and ensure that whenever we need to, in order to control load balacing, raise new instances of a microservice and at the same time, decrease those instances if they are not being used.
- It is necessary to have visibility of our microservices and if, for example, one of them is presenting a problem of lack of disk, we need to know what it is, so we can adjust it.
- Having fault tolerance is very important, because otherwise, if a microservice goes down, all the others can be impacted by it.

Advantages of working with microservices:
- Adaptation of processes and technologies easily. Each microservice can be developed in a different language without any problems.
- Dynamic Scaling: If, for example, your website has a lot of access on Black Friday, you can have many instances working at the same time, as you can also on a common day, at dawn, have few instances working at the same time.
- Faster release cycles: As you are developing smaller things, it is easier to bring new things into production.

## About the microservices:
In this example we want to build three microservices that will talk to each other: CurrencyCalculationService, CurrencyExchangeService and LimitsService. All of them will talk to the Spring Cloud Config Server project, which provides a way to store all configurations of different microservices in a git repository. In this example, we will use this with a local repository.

**CurrencyCalculationService:**
It will calculate the conversion from one currency to another. Will call CurrencyExchangeService to get the exchange values. We use Feign to facilitate calls between one microservice and another. This tool helps us do this by developing much less code.
We used Ribbon to do the client side load balacing, with that, the requests will be divided between the microservices instances. We used it to have this load balancing when calling CurrencyExchangeService.

**CurrencyExchangeService:**
It will have a database that will store the exchange value from one currency to another. Will call LimitsService to get the limits.

**LimitsService:**
It will have configuration files for the conversion limits.

## Comments:
- In this example we used Spring Cloud Config Server, which provides a way to store all configurations of different microservices and different environments in a git repository. In this example, we will use this with a local repository.
- Having to manually configure in application.properties the URLs that my services are available is something very bad. Other than that, I also want to create/delete new instances according to the need I have at the moment. We managed to solve this with a naming server, where each server that goes up, will register on our naming server (this is called server registration) and then I don't need to have the URLs manually configured. When a service needs to use another, ask the naming server which instance to use (this is called server discovery). In this example, we will use Eureka Naming Server.
- By the name of the application, Ribbon, through Eureka is able to make use of the different instances of CurrencyExchangeService.
- We have also a API Gateway, that will be responsible for having all the features common among the microservices (for example, log). In this example, we will use Zuul API Gateway.
- It can be very difficult to understand what happened in a request that did not work as it should. Therefore, we have to have a centralized location (Distributed Tracing) where we can check all the logs and then track step by step what happened with a request. In this example, we will use Spring Cloud Sleuth (to add a unique id to each request) + Zipkin (Distributed Tracing Server) and send all the data of our requests to Zipkin using RabbitMQ (Message Queue).
- We also use Spring Cloud Bus and the main use is to transmit configuration (in this example, it is directly connected with the Spring Cloud Config Server!) changes or other management information in a simple way, where I do not need to make requests for each instance of my microservice, but use only one URL to apply to all. It uses a lightweight messaging broker to link nodes in the distributed system. 
- We have to prevent and guarantee Fault Tolerance in our microservices. If one of them stops working, it should not affect others that depend on it and should give a default response, making sure that the other micrservices are not impacted. In this example, we will use Hystrix.

## Ports:

Service  | Port
------------- | -------------
Limits Service  | 8080, 8081, 8082...
Currency Conversion Service  | 8100, 8101, 8102...
Currency Exchange Service  | 8000, 8001, 8002...
Spring Cloud Config Server  | 8888
Netflix Eureka Naming Server  | 8761
Netflix Zuul API Gateway Server  | 8765
Zipkin Distributed Tracing Server  | 9411