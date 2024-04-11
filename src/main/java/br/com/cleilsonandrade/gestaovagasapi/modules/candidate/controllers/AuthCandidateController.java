package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/candidates")
@Tag(name = "Candidate", description = "Candidate information")
public class AuthCandidateController {
  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/auth")
  @Operation(summary = "Authentication of candidate", description = "This function is responsible authenticate a candidate", responses = {
      @ApiResponse(responseCode = "200", description = "Generated Bearer token and expiration", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthCandidateResponseDTO.class))),
      @ApiResponse(responseCode = "401", description = "Username or password incorrect")
  })
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var token = authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
