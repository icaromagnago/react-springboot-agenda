package br.com.icaro.agenda.service.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.StatusAgenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.repository.AgendaRepository;

class CriaAgendaClienteStrategyTest {
	
	private AgendaRepository agendaRepository = Mockito.mock(AgendaRepository.class);

	@Test
	void testCriaAgenda() {
		var  strategy = new CriaAgendaClienteStrategy(agendaRepository);

		ArgumentCaptor<Agenda> agendaCaptor = ArgumentCaptor.forClass(Agenda.class);
	
		LocalDate dataAgenda = LocalDate.of(2020, 10, 12);
		LocalDate dataInicio = dataAgenda.minusDays(1);
		strategy.criar(TipoAgenda.CLIENTE, "Nova Agenda", dataAgenda);
		
		verify(agendaRepository).save(agendaCaptor.capture());
		var agenda = agendaCaptor.getValue();
		
		assertEquals(TipoAgenda.CLIENTE, agenda.getTipoAgenda());
		assertEquals(StatusAgenda.ENVIAR_EMAIL, agenda.getStatus());
		assertEquals("Nova Agenda", agenda.getDescricao());
		assertEquals(dataAgenda, agenda.getDataAgenda());
		assertEquals(dataInicio, agenda.getDataInicio());
	}

}
