package com.lance.test.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

/**
 * @author Lance
 * @since 2021/1/5
 */
public class CalculatorSumSteps {

    private ICalculatorService calculatorService = new CalculatorService();

    private int number1;

    private int number2;

    private int sum;

    @Given("number1 is {int}") // 这里填数据类型 {int}
    public void number1_is(int number1) {
        this.number1 = number1;
    }

    @Given("number2 is {int}")
    public void number2_is(int number2) {
        this.number2 = number2;
    }

    @When("I ask sum")
    public void i_ask_sum() {
        this.sum = calculatorService.sum(this.number1, this.number2);
    }

    @Then("I should be told {int}")
    public void i_should_be_told(int sum) {
        Assert.assertEquals(this.sum, sum); // 这里也可以不做断言
    }
}
