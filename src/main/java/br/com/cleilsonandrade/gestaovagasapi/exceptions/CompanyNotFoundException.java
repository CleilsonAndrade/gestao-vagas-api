package br.com.cleilsonandrade.gestaovagasapi.exceptions;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException() {
    super("Company not found");
  }
}
