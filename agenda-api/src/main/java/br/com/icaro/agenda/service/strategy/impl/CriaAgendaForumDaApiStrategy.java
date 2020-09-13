package br.com.icaro.agenda.service.strategy.impl;

import br.com.icaro.agenda.repository.AgendaRepository;

public class CriaAgendaForumDaApiStrategy extends ForumStrategy {
	
	public CriaAgendaForumDaApiStrategy(AgendaRepository agendaRepository) {
		super(agendaRepository);
	}
}
