Feature: calculator

  Scenario Outline: sum
    Given number1 is <num1>
    Given number2 is <num2>
    When I ask sum
    Then I should be told <answer>

    Examples:
      | num1     | num2        | answer |
      | 10       | 20          | 30     |
      | 25       | 58          | 83     |
