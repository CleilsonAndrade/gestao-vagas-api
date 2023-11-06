package br.com.cleilsonandrade.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cleilsonandrade.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.cleilsonandrade.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  public void execute(AuthCompanyDTO authCompanyDTO) {
    var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
        () -> {
          throw new UsernameNotFoundException("Company not found");
        });

  }
}
