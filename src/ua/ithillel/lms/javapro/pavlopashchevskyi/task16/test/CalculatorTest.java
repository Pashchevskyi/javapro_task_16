package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.test;

import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.AfterSuite;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.BeforeSuite;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.Test;

public class CalculatorTest {

  private double leftOperand;
  private double rightOperand;

  private static final double accuracy = 0.00000001;

  public CalculatorTest() {
    System.out.println("Working with testing object has been begun.");
    leftOperand = 0;
    rightOperand = 0;
  }

  @BeforeSuite
  public CalculatorTest init(double a, double b) {
    leftOperand = a;
    rightOperand = b;
    System.out.println("Object has been initialized by " + a + " and " + b + " properties");
    return this;
  }

  @Test(priority = 3)
  public double plus() {
    return leftOperand + rightOperand;
  }

  @Test(priority = 3)
  public double minus() {
    return leftOperand - rightOperand;
  }

  @Test(priority = 2)
  public double multiply() {
    return leftOperand * rightOperand;
  }

  @Test(priority = 2)
  public double divide() {
    return (Math.abs(rightOperand) < accuracy) ? leftOperand / accuracy
        : leftOperand / rightOperand;
  }

  @Test(priority = 1)
  public double pow() {
    return (Math.abs(leftOperand) < accuracy && Math.abs(rightOperand) <= 0) ? Math.pow(accuracy,
        accuracy) :
        Math.pow(leftOperand, rightOperand);
  }

  @AfterSuite
  public void finish() {
    leftOperand = 0;
    rightOperand = 1;
    System.out.println("Working with testing object has been finished.");
  }
}
