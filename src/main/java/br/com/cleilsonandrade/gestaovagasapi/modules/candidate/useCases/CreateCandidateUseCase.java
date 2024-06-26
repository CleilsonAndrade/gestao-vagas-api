package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.gestaovagasapi.exceptions.UserFoundException;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.entities.CandidateEntity;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional()
  public CandidateEntity execute(CandidateEntity candidateEntity) {
    candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    var password = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(password);

    return candidateRepository.save(candidateEntity);
  }
}
