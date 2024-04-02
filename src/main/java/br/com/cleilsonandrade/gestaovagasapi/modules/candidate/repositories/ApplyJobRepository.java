package br.com.cleilsonandrade.gestaovagasapi.modules.candidate.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleilsonandrade.gestaovagasapi.modules.candidate.entities.ApplyJobEntity;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
