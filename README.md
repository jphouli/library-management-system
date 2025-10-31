# Library Management System
This repository contains a dockerized library management system using Java Spring Boot RESTful APIs, Docker with Docker Compose, Postgres databases, and a react frontend. 
The requirements for this project are based off of a monolith structure project in my Advanced Java course at SVSU. This project will probably look like a situation of overengineering, 
when in reality I know that this project would be better suited with a monolith structure but I chose to take requirements from a project that I was already familiar 
with instead of building out requirements for one that would better utilize the environment.

## Purpose 
I chose to break this project into microservices to practice my skills of making services and docker containers that can efficiently talk to the frontend and to 
themselves (Planning to use gRPC for the intercommunications between services).

## Technologies
Java
Spring Boot
RESTful APIs
Docker
React
HTML
Tailwind

## Local environment setup
If you'd like to try out this project, clone the repository and run the docker-compose file. Make sure to create a .env file in the parent directory and copy in the
.default.env file contents in for the local development environment variables.
