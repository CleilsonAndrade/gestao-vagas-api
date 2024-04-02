package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "candidate")
@Table(name = "tb_candidate")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "John Doe", requiredMode = RequiredMode.REQUIRED, description = "Candidate name")
  private String name;

  @Schema(example = "john_doe", requiredMode = RequiredMode.REQUIRED, description = "Candidate username")
  @NotBlank
  @Pattern(regexp = "\\S+", message = "The field (username) must not contain blanks")
  private String username;

  @Schema(example = "john@doe.com", requiredMode = RequiredMode.REQUIRED, description = "Candidate email")
  @Email(message = "The field (email) must contain a valid email address")
  private String email;

  @Schema(example = "admin_1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Candidate password")
  @Length(min = 10, max = 100, message = "The password must contain between (10) and (100) characters")
  private String password;

  @Schema(example = "Java Developer", requiredMode = RequiredMode.REQUIRED, description = "Brief description of the candidate")
  private String description;

  @Schema(example = "https://www.linkedin.com/in/john-doe-developer/", requiredMode = RequiredMode.REQUIRED, description = "Candidate's CV")
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
