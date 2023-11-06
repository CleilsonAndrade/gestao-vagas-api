package br.com.cleilsonandrade.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleilsonandrade.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.cleilsonandrade.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  private void execute(CompanyEntity companyEntity) {
    this.companyRepository.save(companyEntity);
  }
}
