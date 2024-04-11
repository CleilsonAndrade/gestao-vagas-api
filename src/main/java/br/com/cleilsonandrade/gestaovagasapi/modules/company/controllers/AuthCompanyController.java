package br.com.cleilsonandrade.gestaovagasapi.modules.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonandrade.gestaovagasapi.modules.company.dto.AuthCompanyDTO;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.dto.AuthCompanyResponseDTO;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.useCases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/companies")
@Tag(name = "Companies", description = "Companies information")
public class AuthCompanyController {
  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  @Operation(summary = "Authentication of company", description = "This function is responsible authenticate a company", responses = {
      @ApiResponse(responseCode = "200", description = "Generated Bearer token and expiration", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthCompanyResponseDTO.class))),
      @ApiResponse(responseCode = "401", description = "Username or password incorrect")
  })
  public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    try {
      var result = authCompanyUseCase.execute(authCompanyDTO);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
