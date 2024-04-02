package br.com.cleilsonandrade.gestaovagasapi.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found");
  }
}
