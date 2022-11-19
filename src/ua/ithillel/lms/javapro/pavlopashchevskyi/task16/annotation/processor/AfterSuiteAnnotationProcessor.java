package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor;

import java.util.Optional;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.AfterSuite;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception.MoreThanOneTestsFinalizingMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AfterSuiteAnnotationProcessor extends OnceUsedAnnotationProcessor {

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
