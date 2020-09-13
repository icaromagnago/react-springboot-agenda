package br.com.icaro.agenda.service.strategy.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.service.strategy.CriaAgendaStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaCartorioStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaClienteStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaEscritorioStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaForumDaApiStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaForumStrategy;

public class CriaAgendaStrategyFactory {
	
	private Map<TipoAgenda, CriaAgendaStrategy> mapStrategy = new HashMap<>();
	
	private CriaAgendaForumDaApiStrategy criaAgendaForumDaApiStrategy;
	
	public CriaAgendaStrategyFactory(CriaAgendaForumStrategy agendaForumStrategy,
			CriaAgendaForumDaApiStrategy criaAgendaForumDaApiStrategy,
			CriaAgendaClienteStrategy agendaClienteStrategy,
			CriaAgendaEscritorioStrategy agendaEscritorioStrategy,
			CriaAgendaCartorioStrategy agendaCartorioStrategy) {
		
		mapStrategy.put(TipoAgenda.CLIENTE, agendaClienteStrategy);
		mapStrategy.put(TipoAgenda.ESCRITORIO, agendaEscritorioStrategy);
		mapStrategy.put(TipoAgenda.CARTORIO, agendaCartorioStrategy);
		mapStrategy.put(TipoAgenda.FORUM, agendaForumStrategy);
		
		this.criaAgendaForumDaApiStrategy = criaAgendaForumDaApiStrategy;
	}
	
	public CriaAgendaStrategy getStrategyPorTipoAgenda(TipoAgenda tipoAgenda) {
		return mapStrategy.getOrDefault(tipoAgenda, mapStrategy.get(TipoAgenda.ESCRITORIO));
	}
	
	public CriaAgendaStrategy getAgendaForumDaApiStrategy() {
		return criaAgendaForumDaApiStrategy;
	}
}
