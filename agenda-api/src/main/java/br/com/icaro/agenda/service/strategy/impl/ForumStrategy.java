package br.com.icaro.agenda.service.strategy.impl;

import java.time.LocalDate;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.StatusAgenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.repository.AgendaRepository;
import br.com.icaro.agenda.service.strategy.CriaAgendaStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ForumStrategy implements CriaAgendaStrategy {

	private static final int TRES_DIAS = 3;
	
	private final AgendaRepository agendaRepository;

	@Override
	public Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda) {
		var dataInicio = dataAgenda.minusDays(TRES_DIAS);
		
		var agenda = Agenda.builder()
				.tipoAgenda(tipoAgenda)
				.descricao(descricao)
				.status(StatusAgenda.ENVIADO_FORUM)
				.dataAgenda(dataAgenda)
				.dataInicio(dataInicio)
				.build();
			
		return agendaRepository.save(agenda);
	}
}
