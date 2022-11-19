package ua.ithillel.lms.javapro.pavlopashchevskyi.task16.annotation.exception;

public class MoreThanOneTestsFinalizingMethodException extends Exception {

  public MoreThanOneTestsFinalizingMethodException() {
    super("There are no more than one finalizing method must be present in test");
  }
}
