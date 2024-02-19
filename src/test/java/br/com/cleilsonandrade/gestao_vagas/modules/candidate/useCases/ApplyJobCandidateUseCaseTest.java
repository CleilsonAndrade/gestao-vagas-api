package br.com.cleilsonandrade.gestao_vagas.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cleilsonandrade.gestao_vagas.exceptions.JobNotFoundException;
import br.com.cleilsonandrade.gestao_vagas.exceptions.UserNotFoundException;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.cleilsonandrade.gestao_vagas.modules.company.entities.JobEntity;
import br.com.cleilsonandrade.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @Test
  public void shouldNotBeAbleTo_ApplyJob_CandidateNotFound() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
  }

  @Test
  public void shouldNotBeAbleTo_ApplyJob_WithJobNotFound() {
    var idCandidate = UUID.randomUUID();
    var candidate = new CandidateEntity();
    candidate.setId(idCandidate);

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    try {
      applyJobCandidateUseCase.execute(idCandidate, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JobNotFoundException.class);
    }
  }

  @Test
  public void shouldBeAbleTo_CreateANew_ApplyJob() {
    var idCandidate = UUID.randomUUID();
    var idJob = UUID.randomUUID();

    CandidateEntity candidateEntity = new CandidateEntity();
    candidateEntity.setId(idCandidate);

    JobEntity jobEntity = new JobEntity();
    jobEntity.setId(idJob);

    var applyJob = ApplyJobEntity.builder()
        .candidate(candidateEntity)
        .job(jobEntity)
        .build();

    var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
    when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

    when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

    var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

    assertThat(result).hasFieldOrProperty("id");
  }
}
