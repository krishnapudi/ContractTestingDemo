Pact Contract Testing Demo
Technology Stack
Java 17
Spring Boot 3.3.5
Maven
Pact 4.1.43
Docker
Pact Broker
JUnit 5
Rest Assured
Flow
ConsumerPactTest ↓ Generate Contract ↓ Publish Contract To Pact Broker ↓ ProviderPactTest ↓ Download Contract From Broker ↓ Verify Provider

Start Pact Broker
docker compose up -d
Start Provider
mvn spring-boot:run
Generate Contract
mvn test -Dtest=ConsumerPactTest
Publish Contract
mvn pact:publish
Verify Provider
mvn test -Dtest=ProviderPactTest