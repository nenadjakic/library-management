# library-management

**Development in progress**

## Description
This repository contains a collection of microservices for managing various aspects of a library system. Each microservice is responsible for a specific domain, such as user management, book management, and borrowing management. The goal of this project is to provide a modular and scalable solution for library management.

## Table of Contents
- [Services](#services)
- [Getting Started](#getting-started)

## Services
- [Shared](./shared/README.md): Common interfaces for services and REST controllers.
- [Country Service](./country-service/README.md): Manages country information and related operations.
- [Book Service](./book-service/README.md): Manages book inventory and related operations.
- [User Service](./user-service/README.md): In progress.
- [Borrowing Service](./borrowing-service/README.md): In progress.

## Getting Started
To get started with the library management system, clone the repository and follow the instructions in each service's `README.md` file.

`git clone https://github.com/nenadjakic/library-management.git`

`cd library-management`

## Dockerize Application

Follow these steps to dockerize and run your application using Docker and Docker Compose:

### Installing Docker and Docker Compose

Before dockerizing your application, ensure that Docker and Docker Compose are installed on your machine. If not already installed, you can follow these guides:

- [Docker Install Guide](https://docs.docker.com/get-docker/)
- [Docker Compose Install Guide](https://docs.docker.com/compose/install/)

### Running Application with Docker Compose

Once Docker and Docker Compose are installed, you can use Docker Compose to build and run your application in a container:

`docker compose up`

## License
This project is licensed under the MIT License - see the LICENSE file for details.