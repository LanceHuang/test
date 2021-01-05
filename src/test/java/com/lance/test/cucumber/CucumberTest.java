package com.lance.test.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Lance
 * @since 2021/1/5
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/features/com/lance/test/cucumber/calculate.feature")
public class CucumberTest {
}
