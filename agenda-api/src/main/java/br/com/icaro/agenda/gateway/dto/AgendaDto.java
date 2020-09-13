package br.com.icaro.agenda.gateway.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDto {
	
	private Long id;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	
}
