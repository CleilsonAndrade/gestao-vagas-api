package br.com.cleilsonandrade.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.cleilsonandrade.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "apply_job")
@Table(name = "tb_apply_job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "id_candidate", insertable = false, nullable = false)
  private CandidateEntity candidate;

  @ManyToOne
  @JoinColumn(name = "id_job", insertable = false, nullable = false)
  private JobEntity job;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
