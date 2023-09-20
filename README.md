<h1 align="center"> Micro Services for Ordering System  </h1> <br>

<p align="center">
  Micro Services for Ordering System for some (hardware) products.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [API Usage](#api-usage)
- [Microservice Architecture](#microservice-architecture)
- [Database Diagram](#database-diagram)
- [Remarks for improvement](#Remarks)




## Introduction
It is a RESTful API build on top of microservice architecture focused on ordering system.

## Features
Those are the features available:

* Manage Product catalog:
  * allow adding new products to catalog;
  * allow removing products from the catalog;
  * get information about stock for a specific product;
  * manage product stock _(increase or decreasing the number of available items)_;
* Manage Ordering of Products from the catalog:
  * create new order
    * only known products are orderable;
    * check on stock is done;
    * orders have a state _(running, delivered, cancelled)_;
  * order can have its state updated:
    * the product stock is updated on delivery of the order;
    * show status and content of orders;


## Requirements
The application can run locally, the requirements for each setup are listed below:

### Local
* [Java 17 SDK](https://www.oracle.com/java/technologies/downloads/#java17)
* [Maven](https://maven.apache.org/download.cgi)
* [Postman](https://www.postman.com/downloads) 
  * _or any other API Client_;


### Docker _(needed for Zipkin)_
* [Docker](https://www.docker.com/get-docker)


## Quick Start
Make sure to have _Docker_ started on your local machine.

### Start Zipkin
Start [Zipkin](https://hub.docker.com/r/openzipkin/zipkin) application on its default port `9411`. In order to do that, run the following command:
```bash
$ docker run -d -p 9411:9411 openzipkin/zipkin
```

### Run Spring Boot applications
Start each Spring Boot application in the respective order:
<ol>
  <li>service-registry</li>
  <li>config-server</li>
  <li>product-service</li>
  <li>inventory-service</li>
  <li>order-service</li>
  <li>api-gateway</li>
</ol> 

For example, go to the respective application's folder, and run:
```bash
$ cd ./service-registry
$ mvn spring-boot:run
```

Each application will run on its pre-defined port. Another option is to run them on the IDE of your preference.

Knows more at the [Microservice Architecture](#microservice-architecture) section.


## API Usage
A [Postman Collection](/postman-collection/Assignment.postman_collection.json) can be found at this repository.

The collection has all the available endpoints within this project.


## Microservice Architecture
Below is the overview of the project's architecture:
![Micro service architecture](/assets/microservice-archtecture-diagram.drawio.png)


## Database Diagram
Below is the overview of the project's database diagram:
![Micro service architecture](/assets/database-schema.png)


## Remarks
Below are some remarks, stuff not yet done but seen as possible improvements:
* Use _spring-boot-starter-validation_ to provide validation for the requests;
* Reducing microservice duplicity of Java Objects with shared libraries;
* Implement some sort of Security;
* Write Unit tests (JUnit);
* Change @repository layers to have a real Database like _PostgresSQL_ instead of _List_ and _HashMap_;
