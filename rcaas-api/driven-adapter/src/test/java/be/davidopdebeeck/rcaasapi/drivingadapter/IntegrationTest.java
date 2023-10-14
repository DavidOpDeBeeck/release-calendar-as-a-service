package be.davidopdebeeck.rcaasapi.drivingadapter;

import be.davidopdebeeck.rcaasapi.drivenadapter.DrivenAdapterConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@Target(TYPE)
@Retention(RUNTIME)
@Transactional
@Rollback
@EnableAutoConfiguration
@AutoConfigureTestEntityManager
@SpringBootTest(classes = DrivenAdapterConfiguration.class, webEnvironment = NONE)
@ExtendWith({SpringExtension.class})
public @interface IntegrationTest {
}
