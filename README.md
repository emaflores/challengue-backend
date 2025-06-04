# Challenge Backend - API REST con Spring Boot

Este proyecto es una solución al challenge técnico propuesto por CodersLab - Tenpo. Implementa una API REST con funcionalidades de cálculo dinámico, almacenamiento en caché, registro asincrónico de llamadas y persistencia en base de datos PostgreSQL.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.6
- Spring Web, JPA, Cache (Caffeine)
- PostgreSQL (en Docker)
- Springdoc OpenAPI 2.3.0 (Swagger)
- Maven
- JUnit 5 + Mockito
- Docker + Docker Compose

---

## 📦 Requisitos previos

- Java 21
- Docker y Docker Compose
- Maven 3.9+

---

## ⚙️ Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/emaflores/challengue-backend.git
cd challenge-backend
```

### 2. Levantar la aplicación con Docker

```bash
docker compose up --build
```

Esto levantará:

- challenge-api en localhost:8080
- challenge-postgres en localhost:5432

### Ejecutar tests

```bash
mvn test
```

Incluye tests para:

- Cálculo normal
- Fallback a caché
- Excepción si no hay valor en caché

### Documentacion Swagger

Levantar el proyecto y acceder a la swagger (similar postman):

```bash
http://localhost:8080/swagger-ui/index.html
```
### Endpoints
POST /api/calculate <br>
Calcula la suma de dos números y aplica un porcentaje (obtenido de un servicio externo simulado)
Request: 
```bash
{
  "num1": 100,
  "num2": 50
}
```
Response:
```bash
{
  "originalSum": 150.0,
  "appliedPercentage": 10.0,
  "finalResult": 165.0
}
```
GET /api/logs?page=0&size=10 <br>
Devuelve el historial de llamadas con paginación

### Comportamiento del servicio

- Si el servicio externo de porcentaje falla, se usa el valor cacheado (valido por 30 minutos)
- Si no hay valor cacheado, se lanza un error controlado
- Todas las llamadas (exitosas o con error) se registran en base de datos PostgreSQL de forma asincronica.

### Variables de entorno
```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/challenge_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

### Estructura del proyecto
```bash
src
 └── main
     └── java
         └── com.emaflores.challenge
             ├── controller
             ├── service
             ├── model
             ├── repository
             └── config
```

### Autor

Emanuel Flores - [Linkedin](https://www.linkedin.com/in/emaflores/)

