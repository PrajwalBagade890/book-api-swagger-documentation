# Book API - API Key Security + Swagger/OpenAPI

A complete Spring Boot REST API for CRUD operations on books. The project keeps API-key protection and adds Swagger/OpenAPI documentation.

## Requirements
- JDK 17 or later
- Maven 3.6+
- Internet connection for the first Maven dependency download

## Run in IntelliJ IDEA / VS Code
1. Extract the ZIP file.
2. Open the `book-api-key-swagger-documentation` folder (the folder containing `pom.xml`).
3. Set the project JDK to 17 or later.
4. Open a terminal in that folder and run:

```bash
mvn clean test
mvn spring-boot:run
```

Application URL: `http://localhost:8080`

## Swagger UI and OpenAPI JSON
- Swagger UI: http://localhost:8080/swagger-ui.html
- Live OpenAPI JSON: http://localhost:8080/v3/api-docs
- Exported repository copy: `docs/openapi.json`

The exported `docs/openapi.json` is a checked-in OpenAPI 3.0 specification. After changing endpoints or annotations, start the app and refresh the live JSON from `/v3/api-docs`, then replace the file in `docs/openapi.json`.

## API key
All `/api/books` endpoints require this HTTP header:

```http
X-API-KEY: secret123
```

For better security, set your own key before running:
- Windows PowerShell: `$env:BOOK_API_KEY="your-new-key"`
- macOS/Linux: `export BOOK_API_KEY="your-new-key"`

The default `secret123` is for local practice only. Do not use it in production.

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get a book by ID |
| POST | `/api/books` | Create a book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

Example request body:

```json
{
  "title": "The Pragmatic Programmer",
  "author": "Andrew Hunt",
  "price": 450.00
}
```

## Test with curl

```bash
curl -H "X-API-KEY: secret123" http://localhost:8080/api/books
curl -H "X-API-KEY: secret123" http://localhost:8080/api/books/1
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -H "X-API-KEY: secret123" -d "{\"title\":\"New Book\",\"author\":\"A. Writer\",\"price\":250}"
```

A request without the header or with a wrong key returns HTTP `401 Unauthorized`.

## Swagger UI usage
1. Run the application.
2. Open `http://localhost:8080/swagger-ui.html`.
3. Click **Authorize**.
4. Enter `secret123` as the API key value.
5. Execute any Book API endpoint.

## Task implementation checklist
- [x] `@Tag` at controller class level.
- [x] `@Operation` summary and description on every endpoint.
- [x] `@Parameter` descriptions for path parameters.
- [x] Request/response documentation and model schema examples.
- [x] Swagger UI and live OpenAPI JSON endpoint.
- [x] Exported OpenAPI JSON added under `docs/openapi.json`.
- [x] README includes Swagger UI URL and usage instructions.
