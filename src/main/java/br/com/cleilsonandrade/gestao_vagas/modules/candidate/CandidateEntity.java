package br.com.cleilsonandrade.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

  private UUID id;
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "The field (username) must not contain blanks")
  private String username;

  @Email(message = "The field (email) must contain a valid email address")
  private String email;

  @Length(min = 10, max = 100, message = "The password must contain between (10) and (100) characters")
  private String password;
  private String description;
  private String curriculum;
}
