# QuizAppSB

QuizAppSB is a Spring Boot-based RESTful API for creating, managing, and taking quizzes. It supports quiz and question management, quiz submission, and result calculation. The backend uses PostgreSQL for data storage and is ready for deployment with Docker or Procfile-based platforms.

## Features
- Create, update, delete, and list quiz questions
- Create and manage quizzes by category and number of questions
- Submit quiz answers and get results
- RESTful API endpoints
- CORS enabled for frontend integration (default: http://localhost:5173)

## Tech Stack
- Java 21
- Spring Boot 3.4.0
- Spring Data JPA
- PostgreSQL
- Docker support

## Getting Started

### Prerequisites
- Java 21+
- Maven
- PostgreSQL

### Database Setup
Update `src/main/resources/application.properties` if needed:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/{YOUR_DB}
spring.datasource.username={YOUR_USERNAME}
spring.datasource.password={YOUR_PASSWORD}
```
Create the database in PostgreSQL:
```sql
CREATE DATABASE questiondb;
```

### Build & Run
#### Using Maven
```bash
mvn clean install
mvn spring-boot:run
```

#### Using Docker
Build and run the Docker container:
```bash
docker build -t quizapp .
docker run -p 8080:8080 quizapp
```

#### Using Procfile (Heroku, etc.)
The Procfile is set to run:
```
web: java -jar target/quizapp-0.0.1-SNAPSHOT.jar
```

## API Endpoints

### Questions
- `GET /questions/allquestions` — List all questions
- `GET /questions/category/{category}` — List questions by category
- `POST /questions/add` — Add a new question
- `PUT /questions/update/{id}` — Update a question
- `DELETE /questions/delete/{id}` — Delete a question

### Quizzes
- `POST /quiz/create?category={category}&numQ={numQ}&title={title}` — Create a quiz
- `GET /quiz/get/{id}` — Get quiz questions by quiz ID
- `POST /quiz/submit/{id}` — Submit answers for a quiz
- `DELETE /quiz/{id}` — Delete a quiz
- `GET /quiz/get/allquiz` — List all quizzes

## Data Models

### Quiz
- `id` (Integer)
- `title` (String)
- `questions` (List<Question>)

### Question
- `id` (Integer)
- `questionTitle` (String)
- `option1`, `option2`, `option3`, `option4` (String)
- `rightAnswer` (String)
- `difficultylevel` (String)
- `category` (String)

## CORS
CORS is enabled for `http://localhost:5173` by default. Update in `QuizappApplication.java` if your frontend runs elsewhere.

## License
This project is for educational/demo purposes. 
