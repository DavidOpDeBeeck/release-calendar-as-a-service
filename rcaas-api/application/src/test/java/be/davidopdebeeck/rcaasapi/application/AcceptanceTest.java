package be.davidopdebeeck.rcaasapi.application;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Target(TYPE)
@Retention(RUNTIME)
@EnableAutoConfiguration
@AutoConfigureWebTestClient
@SpringBootTest(classes = RcaasApiApplicationConfiguration.class, webEnvironment = RANDOM_PORT)
@ExtendWith({SpringExtension.class})
public @interface AcceptanceTest {
}
