package br.com.icaro.agenda.service.strategy.impl;

import java.time.LocalDate;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.gateway.ForumApi;
import br.com.icaro.agenda.repository.AgendaRepository;

public class CriaAgendaForumStrategy extends ForumStrategy {
	
	private final ForumApi forumApi;
	
	public CriaAgendaForumStrategy(AgendaRepository agendaRepository, ForumApi forumApi) {
		super(agendaRepository);
		this.forumApi = forumApi;
	}

	@Override
	public Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda) {
		Agenda agenda = super.criar(tipoAgenda, descricao, dataAgenda);
		
		forumApi.criaAgenda(agenda);
		
		return agenda;
	}
}
