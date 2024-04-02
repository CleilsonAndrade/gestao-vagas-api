package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleilsonandrade.gestaovagasapi.exceptions.JobNotFoundException;
import br.com.cleilsonandrade.gestaovagasapi.exceptions.UserNotFoundException;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.entities.ApplyJobEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.entities.CandidateEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.repositories.ApplyJobRepository;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.repositories.CandidateRepository;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.entities.JobEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
    candidateRepository.findById(idCandidate).orElseThrow(() -> {
      throw new UserNotFoundException();
    });

    jobRepository.findById(idJob).orElseThrow(() -> {
      throw new JobNotFoundException();
    });

    CandidateEntity candidateEntity = new CandidateEntity();
    candidateEntity.setId(idCandidate);

    JobEntity jobEntity = new JobEntity();
    jobEntity.setId(idJob);

    var applyJob = ApplyJobEntity.builder()
        .candidate(candidateEntity)
        .job(jobEntity)
        .build();

    applyJobRepository.save(applyJob);

    return applyJob;
  }
}
