package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import spec.RequestSpec;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Posts API — REST Assured")
class PostsApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    @DisplayName("GET /posts")
    class GetPosts {

        @Test
        @DisplayName("should return 200 with 100 posts")
        void getAllPosts() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .body("size()", is(100));
        }

        @Test
        @DisplayName("should return paginated results with _start and _limit")
        void getPaginatedPosts() {
            given()
                    .spec(RequestSpec.get())
                    .queryParam("_start", 0)
                    .queryParam("_limit", 5)
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .body("size()", is(5));
        }

        @Test
        @DisplayName("should return a single post by ID")
        void getPostById() {
            var post = given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/posts/1")
                    .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("userId", notNullValue())
                    .body("title", not(emptyString()))
                    .extract()
                    .as(Post.class);

            assertEquals(1, post.getId());
            assertNotNull(post.getTitle());
            assertNotNull(post.getBody());
        }

        @Test
        @DisplayName("should match JSON schema")
        void validatePostSchema() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/posts/1")
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
        }

        @Test
        @DisplayName("should filter posts by userId")
        void getPostsByUserId() {
            var posts = given()
                    .spec(RequestSpec.get())
                    .queryParam("userId", 1)
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .body("every { it.userId == 1 }", is(true))
                    .extract()
                    .jsonPath()
                    .getList(".", Post.class);

            assertTrue(posts.size() > 0);
            assertTrue(posts.stream().allMatch(p -> p.getUserId() == 1));
        }

        @Test
        @DisplayName("should return 404 for non-existent post")
        void getNonExistentPost() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .get("/posts/99999")
                    .then()
                    .statusCode(404);
        }
    }

    @Nested
    @DisplayName("POST /posts")
    class CreatePost {

        @Test
        @DisplayName("should create a new post and return 201")
        void createPost() {
            var newPost = new Post(1, "Test Title", "Test body content");

            var created = given()
                    .spec(RequestSpec.get())
                    .body(newPost)
                    .when()
                    .post("/posts")
                    .then()
                    .statusCode(201)
                    .body("id", notNullValue())
                    .body("title", is("Test Title"))
                    .body("userId", is(1))
                    .extract()
                    .as(Post.class);

            assertNotNull(created.getId());
            assertEquals("Test Title", created.getTitle());
        }
    }

    @Nested
    @DisplayName("PUT /posts/{id}")
    class UpdatePost {

        @Test
        @DisplayName("should update an existing post")
        void updatePost() {
            var updated = new Post(1, "Updated Title", "Updated body");

            given()
                    .spec(RequestSpec.get())
                    .body(updated)
                    .when()
                    .put("/posts/1")
                    .then()
                    .statusCode(200)
                    .body("title", is("Updated Title"))
                    .body("userId", is(1));
        }
    }

    @Nested
    @DisplayName("PATCH /posts/{id}")
    class PartialUpdatePost {

        @Test
        @DisplayName("should partially update a post")
        void patchPost() {
            var patch = """
                    {"title": "Patched Title"}
                    """;

            given()
                    .spec(RequestSpec.get())
                    .body(patch)
                    .when()
                    .patch("/posts/1")
                    .then()
                    .statusCode(200)
                    .body("title", is("Patched Title"))
                    .body("id", is(1));
        }
    }

    @Nested
    @DisplayName("DELETE /posts/{id}")
    class DeletePost {

        @Test
        @DisplayName("should delete a post and return 200")
        void deletePost() {
            given()
                    .spec(RequestSpec.get())
                    .when()
                    .delete("/posts/1")
                    .then()
                    .statusCode(200);
        }
    }
}
