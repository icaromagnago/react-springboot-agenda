package br.com.icaro.agenda.gateway.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListaAgendasDto {
	
	private List<AgendaDto> content;  
}
