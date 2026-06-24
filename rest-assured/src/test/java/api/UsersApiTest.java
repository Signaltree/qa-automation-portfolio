package api;

import io.restassured.RestAssured;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import spec.RequestSpec;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Users API — REST Assured")
class UsersApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    @DisplayName("GET /users")
    class GetUsers {

        @Test
        @DisplayName("should return 200 with 10 users")
        void getAllUsers() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body("size()", is(10));
        }

        @Test
        @DisplayName("should return a single user by ID")
        void getUserById() {
            var user = given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/users/1")
                    .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("name", not(emptyString()))
                    .body("email", containsString("@"))
                    .extract()
                    .as(User.class);

            assertEquals(1, user.getId());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());
            assertTrue(user.getEmail().contains("@"));
        }

        @Test
        @DisplayName("should match JSON schema")
        void validateUserSchema() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/users/1")
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
        }

        @Test
        @DisplayName("should contain nested address and company objects")
        void validateUserNestedObjects() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/users/1")
                    .then()
                    .statusCode(200)
                    .body("address", notNullValue())
                    .body("address.street", not(emptyString()))
                    .body("address.city", not(emptyString()))
                    .body("company", notNullValue())
                    .body("company.name", not(emptyString()));
        }

        @Test
        @DisplayName("should return 404 for non-existent user")
        void getNonExistentUser() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/users/99999")
                    .then()
                    .statusCode(404);
        }
    }

    @Nested
    @DisplayName("POST /users")
    class CreateUser {

        @Test
        @DisplayName("should create a new user")
        void createUser() {
            var body = """
                    {
                        "name": "John Doe",
                        "username": "johndoe",
                        "email": "john@example.com"
                    }
                    """;

            given()
                    .spec(RequestSpec.get())
                    .body(body)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .body("id", notNullValue())
                    .body("name", is("John Doe"))
                    .body("email", is("john@example.com"));
        }
    }

    @Nested
    @DisplayName("Query parameters")
    class QueryUsers {

        @Test
        @DisplayName("should filter users by name")
        void filterByName() {
            given()
                    .spec(RequestSpec.get())
                    .queryParam("name", "Leanne Graham")
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0))
                    .body("name", hasItem("Leanne Graham"));
        }

        @Test
        @DisplayName("should filter users by email")
        void filterByEmail() {
            given()
                    .spec(RequestSpec.get())
                    .queryParam("email", "Sincere@april.biz")
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0));
        }
    }
}
