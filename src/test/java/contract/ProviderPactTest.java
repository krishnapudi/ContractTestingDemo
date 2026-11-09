package contract;

import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junit5.HttpTestTarget;

import au.com.dius.pact.provider.junitsupport.loader.PactBroker;

import au.com.dius.pact.provider.junit5.PactVerificationContext;

import au.com.dius.pact.provider.junit5
        .PactVerificationInvocationContextProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("UserProvider")

@PactBroker(
        host = "localhost",
        port = "9292"
)

public class ProviderPactTest {

    @TestTemplate

    @ExtendWith(
            PactVerificationInvocationContextProvider.class)
    void verifyPact(
            PactVerificationContext context) {

        context.verifyInteraction();
    }

    @BeforeEach
    void setup(
            PactVerificationContext context) {

        context.setTarget(
                new HttpTestTarget(
                        "localhost",
                        8081
                ));
    }
}