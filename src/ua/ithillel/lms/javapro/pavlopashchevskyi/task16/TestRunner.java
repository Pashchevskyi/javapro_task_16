package ua.ithillel.lms.javapro.pavlopashchevskyi.task16;

import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception.MoreThanOneTestsFinalizingMethodException;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception.MoreThanOneTestsInitializingMethodsException;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor.AfterSuiteAnnotationProcessor;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor.BeforeSuiteAnnotationProcessor;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor.TestAnnotationProcessor;

public class TestRunner {

  private static BeforeSuiteAnnotationProcessor beforeSuiteAnnotationProcessor;

  private static TestAnnotationProcessor testAnnotationProcessor;
  private static AfterSuiteAnnotationProcessor afterSuiteAnnotationProcessor;

  /**
   * Starts annotation processors
   *
   * @param clazz Object with metadata about class, which methods we are going to test
   */
  public static void start(Class<?> clazz) {
    try {
      if (clazz != null) {
        beforeSuiteAnnotationProcessor = new BeforeSuiteAnnotationProcessor();
        Object o = beforeSuiteAnnotationProcessor.handleBeforeSuite(clazz, 2, 8);

        testAnnotationProcessor = new TestAnnotationProcessor();
        testAnnotationProcessor.handleTest(clazz, o);

        afterSuiteAnnotationProcessor = new AfterSuiteAnnotationProcessor();
        afterSuiteAnnotationProcessor.handleAfterSuite(clazz, o);
      }
    } catch (MoreThanOneTestsInitializingMethodsException |
             MoreThanOneTestsFinalizingMethodException |
             ReflectiveOperationException e) {
      System.out.println(e.getMessage());
    }
  }

  public String toString() {
    return (beforeSuiteAnnotationProcessor != null ? beforeSuiteAnnotationProcessor.toString() : "")
        + (testAnnotationProcessor != null ? testAnnotationProcessor : "") +
        (afterSuiteAnnotationProcessor != null ? afterSuiteAnnotationProcessor : "");
  }
}
