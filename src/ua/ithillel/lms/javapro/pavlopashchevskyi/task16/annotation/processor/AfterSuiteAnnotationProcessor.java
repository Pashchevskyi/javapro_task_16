package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor;

import java.util.Optional;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.AfterSuite;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception.MoreThanOneTestsFinalizingMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AfterSuiteAnnotationProcessor extends OnceUsedAnnotationProcessor {

  /**
   * Processes @AfterSuite annotation on method, runs that method
   *
   * @param clazz Object with metadata about class, which methods we are going to test
   * @param objectToRunTests object of testing class (returned by BeforeSuiteAnnotationProcessor)
   * @throws MoreThanOneTestsFinalizingMethodException when there are more than one @AfterSuite
   * @throws InvocationTargetException when we are going to call not method via reflection
   * @throws IllegalAccessException when we are going to call unavailable method via reflection
   */
  public void handleAfterSuite(Class<?> clazz, Object objectToRunTests)
      throws MoreThanOneTestsFinalizingMethodException, InvocationTargetException,
      IllegalAccessException {
    Method[] methods = clazz.getDeclaredMethods();
    long asc = Arrays.stream(methods)
        .filter((method -> method.isAnnotationPresent(AfterSuite.class))).count();
    if (asc > 1) {
      throw new MoreThanOneTestsFinalizingMethodException();
    }
    Optional<Method> meth = Arrays.stream(methods)
        .filter(method -> method.isAnnotationPresent(AfterSuite.class)).findFirst();
    if (meth.isPresent()) {
      Method callingMethod = meth.get();
      callResult = callingMethod.invoke(objectToRunTests);
      methodName = callingMethod.getName();
    }
  }

  public String toString() {
    String strResult = "After all testing methods were finished, method " + methodName;
    strResult += " had been called and result of its execution was the following.\n";
    strResult += callResult;
    return strResult;
  }
}
