package br.com.cleilsonandrade.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonandrade.gestao_vagas.exceptions.UserFoundException;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

  @Autowired
  private CandidateRepository candidateRepository;

  @PostMapping
  public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    return this.candidateRepository.save(candidateEntity);
  }
}
