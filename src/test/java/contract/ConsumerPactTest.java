package contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

import io.restassured.RestAssured;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserProvider")
public class ConsumerPactTest {

    @Pact(
            provider = "UserProvider",
            consumer = "OrderConsumer")
    public RequestResponsePact createPact(
            PactDslWithProvider builder) {

        return builder
                .uponReceiving("Get User")
                .path("/users/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(
                        new PactDslJsonBody()
                                .integerType("id", 1)
                                .stringType("name", "Krishna")
                                .stringType("email",
                                        "krishna@test.com")
                )
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    void verifyContract(MockServer mockServer) {

        int statusCode =
                RestAssured
                        .get(mockServer.getUrl() + "/users/1")
                        .statusCode();

        assertEquals(200, statusCode);
    }
}