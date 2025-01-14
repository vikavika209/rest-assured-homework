package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.example.utils.Constants.SUCCESS_CODE;
import static org.hamcrest.Matchers.equalTo;

public class SimpleTest {
    static Logger logger = LoggerFactory.getLogger(SimpleTest.class);
    static RequestSpecification requestSpecification;
    static int owner1Id;
    static int owner2Id;
    static int petId1;
    static int petId2;
    static int petId3;
    static int petId4;
    static int petId5;

    @BeforeAll
    static void setUp() {
        requestSpecification = RestAssured
                .given()
                .baseUri("http://localhost:9966")
                .accept(ContentType.JSON);

            String user = "{\n" +
                    "  \"username\": \"john.doe\",\n" +
                    "  \"password\": \"1234abc\",\n" +
                    "  \"enabled\": true,\n" +
                    "  \"roles\": [\n" +
                    "    {\n" +
                    "      \"name\": \"admin\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            requestSpecification.given()
                    .body(user)
                    .contentType("application/json")
                    .when()
                    .post("/petclinic/api/users")
                    .then()
                    .statusCode(201)
                    .body("username", equalTo("john.doe"));

            String owner1Json = "{\n" +
                    "  \"firstName\": \"George\",\n" +
                    "  \"lastName\": \"Franklin\",\n" +
                    "  \"address\": \"110 W. Liberty St.\",\n" +
                    "  \"city\": \"Madison\",\n" +
                    "  \"telephone\": \"6085551023\"\n" +
                    "}";

            String owner2Json = "{\n" +
                    "  \"firstName\": \"Jane\",\n" +
                    "  \"lastName\": \"Smith\",\n" +
                    "  \"address\": \"456 Oak St\",\n" +
                    "  \"city\": \"Shelbyville\",\n" +
                    "  \"telephone\": \"9876543210\"\n" +
                    "}";

            owner1Id = requestSpecification.given()
                    .body(owner1Json)
                    .contentType("application/json")
                    .log().all()
                    .when()
                    .post("/petclinic/api/owners")
                    .then()
                    .statusCode(201).extract().path("id");

            owner2Id = requestSpecification.given()
                    .body(owner2Json)
                    .contentType("application/json")
                    .log().all()
                    .when()
                    .post("/petclinic/api/owners")
                    .then()
                    .statusCode(201).extract().path("id");

        String petType1Json = "{\n" +
                "  \"name\": \"cat\"\n" +
                "}";

        String petType2Json = "{\n" +
                "  \"name\": \"dog\"\n" +
                "}";

        String petType3Json = "{\n" +
                "  \"name\": \"hamster\"\n" +
                "}";

        requestSpecification.given()
                .body(petType1Json)
                .contentType("application/json")
                .when()
                .post("/petclinic/api/pettypes")
                .then()
                .statusCode(201);

        requestSpecification.given()
                .body(petType2Json)
                .contentType("application/json")
                .when()
                .post("/petclinic/api/pettypes")
                .then()
                .statusCode(201);

        requestSpecification.given()
                .body(petType3Json)
                .contentType("application/json")
                .when()
                .post("/petclinic/api/pettypes")
                .then()
                .statusCode(201);

            String specialization1Json = "{\n" +
                    "  \"name\": \"radiology\"\n" +
                    "}";

            String specialization2Json = "{\n" +
                    "  \"name\": \"dentistry\"\n" +
                    "}";

            requestSpecification.given()
                    .body(specialization1Json)
                    .contentType("application/json")
                    .when()
                    .post("/petclinic/api/specialties")
                    .then()
                    .statusCode(201);

            requestSpecification.given()
                    .body(specialization2Json)
                    .contentType("application/json")
                    .when()
                    .post("/petclinic/api/specialties")
                    .then()
                    .statusCode(201);

            String vet1Json = "{\n" +
                    "  \"firstName\": \"James\",\n" +
                    "  \"lastName\": \"Carter\",\n" +
                    "  \"specialties\": [\n" +
                    "    {\n" +
                    "      \"name\": \"radiology\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            String vet2Json = "{\n" +
                    "  \"firstName\": \"Jane\",\n" +
                    "  \"lastName\": \"Carter\",\n" +
                    "  \"specialties\": [\n" +
                    "    {\n" +
                    "      \"name\": \"dentistry\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            String vet3Json = "{\n" +
                    "  \"firstName\": \"James\",\n" +
                    "  \"lastName\": \"Smith\",\n" +
                    "  \"specialties\": [\n" +
                    "    {\n" +
                    "      \"name\": \"radiology\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            requestSpecification.given()
                    .body(vet1Json)
                    .contentType("application/json")
                    .when()
                    .post("/petclinic/api/vets")
                    .then()
                    .statusCode(201);

            requestSpecification.given()
                    .body(vet2Json)
                    .contentType("application/json")
                    .when()
                    .post("/petclinic/api/vets")
                    .then()
                    .statusCode(201);

            requestSpecification.given()
                    .body(vet3Json)
                    .contentType("application/json")
                    .when()
                    .post("/petclinic/api/vets")
                    .then()
                    .statusCode(201);

        String pet1Json = "{\n" +
                "  \"name\": \"Leo\",\n" +
                "  \"birthDate\": \"2010-09-07\",\n" +
                "  \"type\": {\n" +
                "    \"name\": \"cat\",\n" +
                "    \"id\": 1\n" +
                "  }\n" +
                "}";

        String pet2Json = "{\n" +
                "  \"name\": \"Leonid\",\n" +
                "  \"birthDate\": \"2010-09-08\",\n" +
                "  \"type\": {\n" +
                "    \"name\": \"dog\",\n" +
                "    \"id\": 2\n" +
                "  }\n" +
                "}";

        String pet3Json = "{\n" +
                "  \"name\": \"Leonardo\",\n" +
                "  \"birthDate\": \"2012-09-07\",\n" +
                "  \"type\": {\n" +
                "    \"name\": \"hamster\",\n" +
                "    \"id\": 3\n" +
                "  }\n" +
                "}";

        String pet4Json = "{\n" +
                "  \"name\": \"Leon\",\n" +
                "  \"birthDate\": \"2010-10-07\",\n" +
                "  \"type\": {\n" +
                "    \"name\": \"cat\",\n" +
                "    \"id\": 4\n" +
                "  }\n" +
                "}";

        String pet5Json = "{\n" +
                "  \"name\": \"Leo\",\n" +
                "  \"birthDate\": \"2015-09-07\",\n" +
                "  \"type\": {\n" +
                "    \"name\": \"hamster\",\n" +
                "    \"id\": 5\n" +
                "  }\n" +
                "}";

        petId1 = requestSpecification.given()
                .body(pet1Json)
                .contentType("application/json")
                .pathParam("ownerId", owner1Id)
                .when()
                .post("/petclinic/api/owners/{ownerId}/pets")
                .then()
                .statusCode(201)
                .extract().path("id");

        petId2 = requestSpecification.given()
                    .body(pet2Json)
                    .contentType("application/json")
                    .pathParam("ownerId", owner1Id)
                    .when()
                    .post("/petclinic/api/owners/{ownerId}/pets")
                    .then()
                    .statusCode(201)
                    .extract().path("id");

        petId3 = requestSpecification.given()
                    .body(pet3Json)
                    .contentType("application/json")
                    .pathParam("ownerId", owner1Id)
                    .when()
                    .post("/petclinic/api/owners/{ownerId}/pets")
                    .then()
                    .statusCode(201)
                    .extract().path("id");

        petId4 = requestSpecification.given()
                    .body(pet4Json)
                    .contentType("application/json")
                    .pathParam("ownerId", owner2Id)
                    .when()
                    .post("/petclinic/api/owners/{ownerId}/pets")
                    .then()
                    .statusCode(201)
                    .extract().path("id");

        petId5 = requestSpecification.given()
                    .body(pet5Json)
                    .contentType("application/json")
                    .pathParam("ownerId", owner2Id)
                    .when()
                    .post("/petclinic/api/owners/{ownerId}/pets")
                    .then()
                    .statusCode(201)
                    .extract().path("id");

        logger.info("Owner with id: {} has been created", String.valueOf(owner1Id));
        logger.info("Owner with id: {} has been created", String.valueOf(owner2Id));
        logger.info("Pet with id: {} has been created", String.valueOf(petId1));
        logger.info("Pet with id: {} has been created", String.valueOf(petId2));
        logger.info("Pet with id: {} has been created", String.valueOf(petId3));
        logger.info("Pet with id: {} has been created", String.valueOf(petId4));
        logger.info("Pet with id: {} has been created", String.valueOf(petId5));
    }

    @Test
    void testStub() {
        Assertions.assertTrue(true);
    }

    @Test
    void positiveTests() {

        //выбор владельца питомца
        requestSpecification
                .given()
                .pathParam("ownerId", owner1Id)
                    .get("/petclinic/api/owners/{ownerId}")
                .then()
                    .statusCode(SUCCESS_CODE)
                .body("firstName", equalTo("George"));

        //выбор питомцев, которых нужно записать на прием
        requestSpecification
                .given()
                .pathParam("ownerId", owner1Id)
                .pathParam("petId", petId1)
                .when()
                .get("/petclinic/api/owners/{ownerId}/pets/{petId}")
                .then()
                .statusCode(SUCCESS_CODE)
                .body("birthDate", equalTo("2010-09-07"));

        String visit = "{\n" +
                "  \"date\": \"2013-01-01\",\n" +
                "  \"description\": \"rabies shot\"\n" +
                "}";

        requestSpecification
                .given()
                .body(visit)
                .pathParam("ownerId", owner1Id)
                .pathParam("petId", petId1)
                .contentType("application/json")
                .when()
                .post("/petclinic/api/owners/{ownerId}/pets/{petId}/visits")
                .then()
                .statusCode(201)
                .body("description", equalTo("rabies shot"))
                .body("date", equalTo("2013-01-01"));
    }

    @Test
    void negativeTests(){

        requestSpecification = RestAssured
                .given()
                .baseUri("http://localhost:9966")
                .accept(ContentType.JSON);

        requestSpecification
                .given()
                .pathParam("visitId", 5)
                .when()
                .get("/visits/{visitId}")
                .then()
                .statusCode(404 );
    }

}
