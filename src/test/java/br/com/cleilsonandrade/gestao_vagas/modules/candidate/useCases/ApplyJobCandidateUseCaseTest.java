package br.com.cleilsonandrade.gestao_vagas.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cleilsonandrade.gestao_vagas.exceptions.UserNotFoundException;
import br.com.cleilsonandrade.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.cleilsonandrade.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Test
  public void shouldNotBeAbleTo_ApplyJob_CandidateNotFound() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
  }
}
