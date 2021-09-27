package com.booking.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {BookmyhikingApplication.class, RunBddTests.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "html:target/cucumber-report.html"})
public class RunBddTests {

}
