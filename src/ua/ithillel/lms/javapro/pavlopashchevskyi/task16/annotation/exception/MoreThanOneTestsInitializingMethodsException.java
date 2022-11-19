package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception;

public class MoreThanOneTestsInitializingMethodsException extends Exception {

  public MoreThanOneTestsInitializingMethodsException() {
    super("There are no more than one initializing method must be present in test");
  }
}
