# FilmCamp Backend

The robust, high-performance RESTful backend powering the FilmCamp movie tracking platform.

---

## Features

- Fetch movie data from TMDB API
- Secure user authentication using Spring Security + JWT
- Movie metadata fetching, caching, and serving
- Persistent relational data modeling using JPA/Hibernate
- User interactions:
    - Watched movies
    - Likes
    - Watchlist
- Public user libraries and activity tracking
- Movie review system
- Movie/news feed integration
- High-performance caching using Caffeine Cache

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- PostgreSQL
- Maven
- Caffeine Cache

---

## ⚡ Caching

FilmCamp uses **Caffeine Cache** to reduce external API calls and improve response times.

### Cached Endpoints

| Endpoint | Cache Duration |
|---|---|
| `/movie/trending` | 24 hours |
| `/movie/top_rated` | 24 hours |
| `/feed/slashfilm` | 1 hour |

---

## 🔐 Authentication

FilmCamp uses JWT-based authentication.

Protected endpoints require a valid JWT token in the request headers.

Example:

```http
Authorization: Bearer <your_token>
```

---

## 🌐 Public Features

Even without authentication, users can:

- Browse trending and top-rated movies
- Search movies
- View movie details and credits
- Read movie reviews
- Explore public user libraries
- View recent user activity
- Access curated movie/news feeds

---

## 📦 API Endpoints

### Movie APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/healthcheck` | Check if server is running |
| GET | `/movie/trending` | Get trending movies |
| GET | `/movie/top_rated` | Get top-rated movies |
| GET | `/movie/popular_on_film_camp` | Get popular movies on FilmCamp |
| GET | `/movie/{id}` | Get movie details |
| GET | `/movie/{id}/credits` | Get movie credits |
| GET | `/movie/search/{query}` | Search movies by name |
| GET | `/movie/{movieId}/reviews` | Get reviews for a movie |
| POST | `/movie/add_review` | Add a review to a movie |

---

### Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/user/register` | Register a new user |
| POST | `/user/login` | Login user |
| POST | `/user/isauthenticated` | Validate authentication |

---

### Interaction APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/interactions/update` | Update watched/liked/watchlist status |
| GET | `/interactions/movie/{id}` | Get interaction status for a movie |

---

### Public User Library APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/u/{username}/recent_activity` | Get user's recent movie activity |
| GET | `/u/{username}/watched` | Get watched movies |
| GET | `/u/{username}/liked` | Get liked movies |
| GET | `/u/{username}/watchlist` | Get user's watchlist |

---

### Feed APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/feed/slashfilm` | Get movie/news feed from SlashFilm |

---

## 🧩 Core Modules

- Authentication & Authorization
- Movie Metadata Service
- User Interaction System
- Review System
- Public User Libraries
- External Feed Aggregation
- Persistence Layer with Hibernate/JPA
- Caffeine-based Caching Layer