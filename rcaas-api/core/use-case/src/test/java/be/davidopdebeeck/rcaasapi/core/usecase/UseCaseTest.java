package be.davidopdebeeck.rcaasapi.core.usecase;

import app.dodb.smd.spring.test.EnableSMDStubs;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@Target(TYPE)
@Retention(RUNTIME)
@EnableAutoConfiguration
@SpringBootTest(classes = {UseCaseConfiguration.class, UseCaseTestConfiguration.class}, webEnvironment = NONE)
@ExtendWith({SpringExtension.class})
@EnableSMDStubs
public @interface UseCaseTest {
}
