package br.com.cleilsonandrade.gestaovagasapi.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.gestaovagasapi.exceptions.UserFoundException;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.entities.CompanyEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public CompanyEntity execute(CompanyEntity companyEntity) {
    companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    var password = passwordEncoder.encode(companyEntity.getPassword());
    companyEntity.setPassword(password);

    return companyRepository.save(companyEntity);
  }
}
