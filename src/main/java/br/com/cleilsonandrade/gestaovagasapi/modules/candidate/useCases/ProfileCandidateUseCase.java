package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.gestaovagasapi.exceptions.UserNotFoundException;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Transactional(readOnly = true)
  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = candidateRepository.findById(idCandidate).orElseThrow(() -> {
      throw new UserNotFoundException();
    });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .id(candidate.getId())
        .build();

    return candidateDTO;
  }
}
