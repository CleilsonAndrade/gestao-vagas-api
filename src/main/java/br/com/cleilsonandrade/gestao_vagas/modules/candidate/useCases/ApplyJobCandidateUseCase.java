package br.com.cleilsonandrade.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleilsonandrade.gestao_vagas.exceptions.JobNotFoundException;
import br.com.cleilsonandrade.gestao_vagas.exceptions.UserNotFoundException;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.cleilsonandrade.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  // @Autowired
  // private ApplyJobRepository applyJobRepository;

  public void execute(UUID idCandidate, UUID idJob) {
    candidateRepository.findById(idCandidate).orElseThrow(() -> {
      throw new UserNotFoundException();
    });

    jobRepository.findById(idJob).orElseThrow(() -> {
      throw new JobNotFoundException();
    });
  }
}
