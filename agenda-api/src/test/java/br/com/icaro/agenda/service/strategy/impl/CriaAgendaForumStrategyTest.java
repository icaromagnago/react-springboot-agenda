package br.com.icaro.agenda.service.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.StatusAgenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.gateway.ForumApi;
import br.com.icaro.agenda.repository.AgendaRepository;

class CriaAgendaForumStrategyTest {
	
	private AgendaRepository agendaRepository = Mockito.mock(AgendaRepository.class);
	private ForumApi forumApi = Mockito.mock(ForumApi.class);

	@Test
	void testCriaAgenda() {
		var  strategy = new CriaAgendaForumStrategy(agendaRepository, forumApi);

		ArgumentCaptor<Agenda> agendaCaptor = ArgumentCaptor.forClass(Agenda.class);
	
		LocalDate dataAgenda = LocalDate.of(2020, 10, 12);
		LocalDate dataInicio = dataAgenda.minusDays(3);
		strategy.criar(TipoAgenda.FORUM, "Nova Agenda", dataAgenda);
		
		verify(forumApi).criaAgenda(any());
		verify(agendaRepository).save(agendaCaptor.capture());
		var agenda = agendaCaptor.getValue();
		
		assertEquals(TipoAgenda.FORUM, agenda.getTipoAgenda());
		assertEquals(StatusAgenda.ENVIADO_FORUM, agenda.getStatus());
		assertEquals("Nova Agenda", agenda.getDescricao());
		assertEquals(dataAgenda, agenda.getDataAgenda());
		assertEquals(dataInicio, agenda.getDataInicio());
	}
}
