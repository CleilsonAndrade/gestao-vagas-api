package br.com.cleilsonandrade.gestaovagasapi.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

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

@Entity(name = "company")
@Table(name = "tb_company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "The field (username) must not contain blanks")
  private String username;

  @Email(message = "The field (email) must contain a valid email address")
  private String email;

  @Length(min = 10, max = 100, message = "The password must contain between (10) and (100) characters")
  private String password;

  private String website;

  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
