package br.com.cleilsonandrade.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonandrade.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.cleilsonandrade.gestao_vagas.modules.company.entities.JobEntity;
import br.com.cleilsonandrade.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping
  public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    var jobEntity = JobEntity.builder()
        .companyId(UUID.fromString(companyId.toString()))
        .benefits(createJobDTO.getBenefits())
        .description(createJobDTO.getBenefits())
        .level(createJobDTO.getLevel())
        .build();

    return this.createJobUseCase.execute(jobEntity);
  }
}
