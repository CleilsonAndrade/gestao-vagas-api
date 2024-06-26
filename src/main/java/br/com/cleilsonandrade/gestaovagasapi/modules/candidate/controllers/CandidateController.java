package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.entities.CandidateEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
@Tag(name = "Candidate", description = "Candidate information")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @PostMapping
  @Operation(summary = "Candidate registration", description = "Its function is to register the candidate", responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateEntity.class)) }),
      @ApiResponse(responseCode = "400", description = "User already exists")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Candidate information", description = "This role is responsible for listing all information about the candidate", security = {
      @SecurityRequirement(name = "security")
  }, responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileCandidateResponseDTO.class)) }),
      @ApiResponse(responseCode = "400", description = "User not found")
  })
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var profile = profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "List of vacancies available for the candidate", description = "This role is responsible for listing all available vacancies for the candidate", security = {
      @SecurityRequirement(name = "security") }, parameters = {
          @Parameter(in = ParameterIn.QUERY, name = "filter", description = "Filter for job listings by language", required = true)
      }, responses = {
          @ApiResponse(responseCode = "200", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))) })
      })
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return listAllJobsByFilterUseCase.execute(filter);
  }

  @PostMapping("/jobs/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Candidate registration for a vacancy", description = "This role is responsible for registering the candidate for a vacancy", security = {
      @SecurityRequirement(name = "security")
  })
  public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var result = applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
