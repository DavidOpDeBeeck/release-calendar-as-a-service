package be.davidopdebeeck.rcaasapi.application;

import app.dodb.smd.spring.EnableSMD;
import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseConfiguration;
import be.davidopdebeeck.rcaasapi.drivenadapter.DrivenAdapterConfiguration;
import be.davidopdebeeck.rcaasapi.drivingadapter.DrivingAdapterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    DrivingAdapterConfiguration.class,
    UseCaseConfiguration.class,
    DrivenAdapterConfiguration.class
})
@EnableSMD(packages = "be.davidopdebeeck")
public class RcaasApiApplicationConfiguration {
}
