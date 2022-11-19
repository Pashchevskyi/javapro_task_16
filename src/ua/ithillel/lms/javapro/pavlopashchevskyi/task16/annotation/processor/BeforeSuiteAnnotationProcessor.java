package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor;

import java.util.Optional;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.BeforeSuite;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception
    .MoreThanOneTestsInitializingMethodsException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class BeforeSuiteAnnotationProcessor extends OnceUsedAnnotationProcessor {

  public Object handleBeforeSuite(Class<?> clazz, Object... clazzProps)
      throws MoreThanOneTestsInitializingMethodsException, NoSuchMethodException,
      InvocationTargetException, InstantiationException, IllegalAccessException {
    Method[] methods = clazz.getDeclaredMethods();
    long bsc = Arrays.stream(methods)
        .filter((method -> method.isAnnotationPresent(BeforeSuite.class))).count();
    if (bsc > 1) {
      throw new MoreThanOneTestsInitializingMethodsException();
    }
    Object o = clazz.newInstance();
    Optional<Method> meth = Arrays.stream(methods)
        .filter(method -> method.isAnnotationPresent(BeforeSuite.class)).findFirst();
    if (meth.isPresent()) {
      Method callingMethod = meth.get();
      callResult = callingMethod.invoke(o, clazzProps);
      methodName = callingMethod.getName();
    }
    return o;
  }

  public String toString() {
    String strResult = "Before any testing method was called, method " + methodName;
    strResult += " had been called and result of its execution was the following.\n";
    strResult += callResult + "\n";
    return strResult;
  }
}
