package br.com.cleilsonandrade.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
  private UUID id;

  @Schema(example = "John Doe")
  private String name;

  @Schema(example = "john@gmail.com")
  private String email;

  @Schema(example = "john")
  private String username;

  @Schema(example = "Java Developer")
  private String description;
}
