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

  /**
   * Initializes the object of class
   *
   * @param a left operand of operation executing by method
   * @param b right operand of operation executing by method
   * @return object of class
   */
  @BeforeSuite
  public CalculatorTest init(double a, double b) {
    leftOperand = a;
    rightOperand = b;
    System.out.println("Object has been initialized by " + a + " and " + b + " properties");
    return this;
  }

  /**
   * Returns the sum of left and right operands, which is the properties of class
   *
   * @return double
   */
  @Test(priority = 3)
  public double plus() {
    return leftOperand + rightOperand;
  }

  /**
   * Returns the difference between left and right operands, which is the properties of class
   *
   * @return double
   */
  @Test(priority = 3)
  public double minus() {
    return leftOperand - rightOperand;
  }

  /**
   * Returns the product of left and right operands, which is the properties of class
   *
   * @return double
   */
  @Test(priority = 2)
  public double multiply() {
    return leftOperand * rightOperand;
  }

  /**
   * Returns the quotient of left and right operands, which is the properties of class
   *
   * @return double
   */
  @Test(priority = 2)
  public double divide() {
    return (Math.abs(rightOperand) < accuracy) ? leftOperand / accuracy
        : leftOperand / rightOperand;
  }

  /**
   * Returns left operand, raised to the power of right operand
   *
   * @return double
   */
  @Test(priority = 1)
  public double pow() {
    return (Math.abs(leftOperand) < accuracy && Math.abs(rightOperand) <= 0) ? Math.pow(accuracy,
        accuracy) :
        Math.pow(leftOperand, rightOperand);
  }

  /**
   * Finalizes the object of class
   */
  @AfterSuite
  public void finish() {
    leftOperand = 0;
    rightOperand = 1;
    System.out.println("Working with testing object has been finished.");
  }
}
