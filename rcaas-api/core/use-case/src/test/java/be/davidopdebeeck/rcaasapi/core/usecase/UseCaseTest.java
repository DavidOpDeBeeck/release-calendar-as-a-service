package be.davidopdebeeck.rcaasapi.core.usecase;

import app.dodb.smd.spring.EnableSMD;
import app.dodb.smd.spring.test.EnableSMDStubs;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@Target(TYPE)
@Retention(RUNTIME)
@EnableAutoConfiguration
@SpringBootTest(classes = {UseCaseConfiguration.class, UseCaseTestConfiguration.class}, webEnvironment = NONE)
@ExtendWith({SpringExtension.class})
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@EnableSMD(packages = "be.davidopdebeeck")
@EnableSMDStubs
public @interface UseCaseTest {
}
