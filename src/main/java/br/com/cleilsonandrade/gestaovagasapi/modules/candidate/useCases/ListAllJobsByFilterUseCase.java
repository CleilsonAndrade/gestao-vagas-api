package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.gestaovagasapi.modules.company.entities.JobEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Transactional(readOnly = true)
  public List<JobEntity> execute(String filter) {
    return jobRepository.findByDescriptionContainingIgnoreCase(filter);
  }
}
