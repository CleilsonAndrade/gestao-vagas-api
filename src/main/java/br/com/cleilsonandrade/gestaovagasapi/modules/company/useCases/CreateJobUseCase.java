package br.com.cleilsonandrade.gestaovagasapi.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.gestaovagasapi.exceptions.CompanyNotFoundException;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.entities.JobEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.repositories.CompanyRepository;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Transactional
  public JobEntity execute(JobEntity jobEntity) {
    companyRepository.findById(jobEntity.getCompany().getId()).orElseThrow(() -> {
      throw new CompanyNotFoundException();
    });

    return jobRepository.save(jobEntity);
  }
}
