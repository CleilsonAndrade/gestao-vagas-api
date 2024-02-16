package br.com.cleilsonandrade.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found");
  }
}
