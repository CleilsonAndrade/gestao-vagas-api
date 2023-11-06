package br.com.cleilsonandrade.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "job")
@Table(name = "tb_job")
@Data
public class JobEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String description;
  private String benefits;

  @NotBlank(message = "This field is mandatory")
  private String level;

  @ManyToOne()
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  private CompanyEntity companyEntity;
  @Column(name = "company_id")
  private UUID companyId;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
