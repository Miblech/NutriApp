# NutriAppSpringBoot

NutriAppSpringBoot is a Spring Boot application designed to manage nutritional data with a MySQL database. This project includes Docker setup for easy deployment and management of the application and its dependencies.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [License](#license)

## Prerequisites
Before you begin, ensure you have the following installed:
- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)

## Installation

### Clone the Repository

```diff bash git clone https://github.com/Miblech/NutriApp.git ```
```cd NutriAppSpringBoot```

### Build the Project
Ensure you have Maven installed. Run the following command to build the project:

```diff bash mvn clean install```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Running the Application

Using Docker Compose
The easiest way to run the application is by using Docker Compose. This will set up both the MySQL database and the Spring Boot application.

Navigate to the docker directory:

```cd docker```

Run Docker Compose:

```diff bash docker-compose up -d```

Run App:

```java -cp . SpringBootDbApplication```

Accessing the Application
Once the containers are up and running, the application should be accessible at:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Project Structure

- `src/main/java`: Contains the source code of the application.
- `src/main/resources`: Contains application properties and other resource files.
- `docker`: Contains Docker-related configuration files.
- `target`: The directory where the built JAR file is located after running `mvn clean install`.

 <p align="right">(<a href="#readme-top">back to top</a>)</p>

## Endpoints
Here are some of the main endpoints provided by the application:

- GET `/api/foods`: Retrieves a list of all food items.
- POST `/api/foods`: Adds a new food item.
- GET `/api/foods/{id}`: Retrieves a specific food item by ID.
- PUT `/api/foods/{id}`: Updates a specific food item by ID.
- DELETE `/api/foods/{id}`: Deletes a specific food item by ID.

Check [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/) to see all Points

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## License
This project is licensed under the MIT License. See the LICENSE file for details.


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact

Your Name - @twitter_handle - nombre2727@gmail.com

Project Link: [https://github.com/Miblech/NutriAppJava](https://github.com/Miblech/NutriAppJava)
