package br.com.icaro.agenda.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataAgenda;
	
	private LocalDate dataInicio;
	
	@Enumerated(EnumType.STRING)
	private TipoAgenda tipoAgenda;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private StatusAgenda status;
}
