package br.com.icaro.agenda.service.strategy.impl;

import java.time.LocalDate;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.StatusAgenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.repository.AgendaRepository;
import br.com.icaro.agenda.service.strategy.CriaAgendaStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaAgendaCartorioStrategy implements CriaAgendaStrategy {
	
	private static final int DOIS_DIAS = 2;
	
	private final AgendaRepository agendaRepository;

	@Override
	public Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda) {
		var dataInicio = dataAgenda.minusDays(DOIS_DIAS);
		
		var agenda = Agenda.builder()
				.tipoAgenda(tipoAgenda)
				.descricao(descricao)
				.status(StatusAgenda.AGENDAR_CARTORIO)
				.dataAgenda(dataAgenda)
				.dataInicio(dataInicio)
				.build();
			
		return agendaRepository.save(agenda);
	}
}
