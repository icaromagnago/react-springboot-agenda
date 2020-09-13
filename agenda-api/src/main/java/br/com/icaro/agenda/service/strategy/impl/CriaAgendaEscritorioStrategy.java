package br.com.icaro.agenda.service.strategy.impl;

import java.time.LocalDate;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.repository.AgendaRepository;
import br.com.icaro.agenda.service.strategy.CriaAgendaStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaAgendaEscritorioStrategy implements CriaAgendaStrategy {
	
	private final AgendaRepository agendaRepository;

	@Override
	public Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda) {
		
		var agenda = Agenda.builder()
				.tipoAgenda(tipoAgenda)
				.descricao(descricao)
				.dataAgenda(dataAgenda)
				.dataInicio(dataAgenda)
				.build();
			
		return agendaRepository.save(agenda);
	}

}
