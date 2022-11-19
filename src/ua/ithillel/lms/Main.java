package ua.ithillel.lms;

import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.TestRunner;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.test.CalculatorTest;

public class Main {

  public static void main(String[] args) {
    TestRunner tr = new TestRunner();
    TestRunner.start(CalculatorTest.class);
    System.out.println("Detailed information about working with testing object:");
    System.out.println(tr);
  }
}