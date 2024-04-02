package br.com.cleilsonandrade.gestaovagasapi.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("User already exists");
  }
}
