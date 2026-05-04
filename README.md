# FilmCamp Backend
The robust, high-performance RESTful backbone for the FilmCamp movie tracking system.

## Features
* Fetch movie data from external API (TMDB)
* User Management: Secure registration and authentication using Spring Security and JWT.
* Movie Metadata: Logic for fetching, caching, and serving movie data.
* Relational Persistence: Complex data mapping using JPA/Hibernate for users, ratings, and watchlists.

---


## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Hibernate / JPA
- PostgreSQL
- Maven

---


## 🔐 Authentication

- JWT-based authentication
- Secured endpoints require token in headers:

---

## API Endpoints
| Method | Endpoint                  | Description                                      |
|--------|--------------------------|--------------------------------------------------|
| GET    | /healthcheck             | Check if server is running                       |
| POST   | /movie/trending          | Get trending movies                              |
| GET    | /movie/top_rated         | Get top-rated movies                             |
| GET    | /movie/{id}              | Get movie details                                |
| GET    | /movie/{id}/credits      | Get movie credits                                |
| GET    | /movie/search/{query}    | Search movies by name                            |
| POST   | /user/register           | Register user                                    |
| POST   | /user/login              | Login user                                       |
| POST   | /user/isauthenticated    | Validate authentication                          |
| POST   | /interactions/update     | Update watched/liked/watchlist status            |
| GET    | /interactions/movie/{id} | Get interaction status for a movie               |