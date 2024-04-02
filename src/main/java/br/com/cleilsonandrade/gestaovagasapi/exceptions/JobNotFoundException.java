package br.com.cleilsonandrade.gestaovagasapi.exceptions;

public class JobNotFoundException extends RuntimeException {
  public JobNotFoundException() {
    super("Job not found");
  }
}
