package com.bottleneck.performance.quarkus.demo.rest;

import com.bottleneck.performance.quarkus.demo.beans.GuestBook;
import com.bottleneck.performance.quarkus.demo.entities.GuestBookEntry;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class GuestBookApiTests {
    @Inject
    GuestBook guestBook;

    @BeforeEach
    @Transactional
    public void setup() {
        guestBook.deleteAll();
    }

    @Test
    @DisplayName("should get empty list")
    public void shouldGetEmptyList() {
        when()
                .get("guestbook/all")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    @DisplayName("should get empty list")
    public void shouldAddAnEntry() {
        given()
                .contentType(ContentType.JSON)
                .body(new GuestBookEntry("Moneyboy", "Orangensaft", LocalDateTime.now()))
                .post("guestbook/add")
                .then()
                .statusCode(204);

        when()
                .get("guestbook/all")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("get(0).by", equalTo("Moneyboy"));
    }
}
