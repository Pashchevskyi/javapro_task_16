package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.processor;

import java.util.ArrayList;
import ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TestAnnotationProcessor {

  private static List<String> methodsNames;
  private static List<Object> testsResults;

  public void handleTest(Class<?> clazz, Object objectToRunTests)
      throws InvocationTargetException, IllegalAccessException {
    List<Method> testingMethods = Arrays.stream(clazz.getMethods())
        .filter(method -> method.isAnnotationPresent(Test.class))
        .sorted(Comparator.comparing(method -> method.getAnnotation(Test.class).priority()))
        .collect(Collectors.toList());
    methodsNames = new ArrayList<>();
    testsResults = new ArrayList<>();
    for (Method testMethod : testingMethods) {
      Object result = testMethod.invoke(objectToRunTests);
      methodsNames.add(testMethod.getName());
      testsResults.add(result);
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("Results of testing:\n");
    for (int i = 0; i < methodsNames.size(); i++) {
      sb.append(methodsNames.get(i));
      sb.append(": ");
      sb.append(testsResults.get(i));
      sb.append("\n");
    }
    return sb.toString();
  }
}
