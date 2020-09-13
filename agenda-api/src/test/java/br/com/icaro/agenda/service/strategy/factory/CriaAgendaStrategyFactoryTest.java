package br.com.icaro.agenda.service.strategy.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaCartorioStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaClienteStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaEscritorioStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaForumDaApiStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaForumStrategy;

class CriaAgendaStrategyFactoryTest {

	@Test
	void testGetStrategy() {
		var strategyFactory = new CriaAgendaStrategyFactory(
				new CriaAgendaForumStrategy(null, null), 
				new CriaAgendaForumDaApiStrategy(null), 
				new CriaAgendaClienteStrategy(null), 
				new CriaAgendaEscritorioStrategy(null), 
				new CriaAgendaCartorioStrategy(null));
		
		assertTrue(strategyFactory.getStrategyPorTipoAgenda(TipoAgenda.CARTORIO) 
				instanceof CriaAgendaCartorioStrategy);
		
		assertTrue(strategyFactory.getStrategyPorTipoAgenda(TipoAgenda.CLIENTE) 
				instanceof CriaAgendaClienteStrategy);
		
		assertTrue(strategyFactory.getStrategyPorTipoAgenda(TipoAgenda.ESCRITORIO) 
				instanceof CriaAgendaEscritorioStrategy);
		
		assertTrue(strategyFactory.getStrategyPorTipoAgenda(TipoAgenda.FORUM) 
				instanceof CriaAgendaForumStrategy);
		
		assertTrue(strategyFactory.getAgendaForumDaApiStrategy() 
				instanceof CriaAgendaForumDaApiStrategy);
	}

}
