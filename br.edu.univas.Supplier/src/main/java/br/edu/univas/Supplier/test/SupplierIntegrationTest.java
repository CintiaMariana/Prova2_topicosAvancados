package br.edu.univas.Supplier.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.univas.Supplier.dtos.SupplierDTO;
import br.edu.univas.Supplier.dtos.SupplierNewDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SupplierIntegrationTest {

    private ObjectMapper mapper = new ObjectMapper();
    private final String supplierURL = "http://localhost:8080/api/supplier";

    @Test
    public void testGetSupplierById() {
        int supplierId = 1;
        try {
            Response resp = RestAssured.get(supplierURL + "/" + supplierId);
            assertEquals(HttpStatus.OK.value(), resp.getStatusCode());

            String jsonBody = resp.getBody().asString();
            SupplierDTO supplier = mapper.readValue(jsonBody, SupplierDTO.class);

            assertNotNull(supplier);
            assertEquals(1, supplier.getId());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail("Fail to get order with id: " + supplierId, e);
        }
    }

    @Test
    public void testPostSupplierSuccess() {
        SupplierNewDTO supplier = new SupplierNewDTO(1, 20415295003785L, "Dom Pedro", new Date(), 10f, 10f, true);
        Response resp = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(supplier)
                .post(supplierURL);

        assertEquals(HttpStatus.CREATED.value(), resp.getStatusCode());
    }

    @Test
    public void testPostSupplierAlreadyExists() {
        SupplierNewDTO supplier = new SupplierNewDTO(1, 20415295003785L, "Dom Pedro", new Date(), 10f, 10f, true);
        
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(supplier)
                .post(supplierURL);

        Response resp = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(supplier)
                .post(supplierURL);

        assertEquals(HttpStatus.CONFLICT.value(), resp.getStatusCode());
    }

    @Test
    public void testPostSupplierInvalidData() {
        SupplierNewDTO supplier = new SupplierNewDTO(1, 123L, "Dom Pedro", new Date(), 10f, 10f, true);
        Response resp = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(supplier)
                .post(supplierURL);

        assertEquals(HttpStatus.BAD_REQUEST.value(), resp.getStatusCode());
    }

    @Test
    public void testActivateSupplierSuccess() {
        int supplierId = 1;
        Response resp = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"active\": true}")
                .patch(supplierURL + "/" + supplierId);

        assertEquals(HttpStatus.OK.value(), resp.getStatusCode());
    }

    @Test
    public void testActivateSupplierNotFound() {
        int supplierId = 999; 
        Response resp = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"active\": true}")
                .patch(supplierURL + "/" + supplierId);

        assertEquals(HttpStatus.NOT_FOUND.value(), resp.getStatusCode());
    }
}



