package br.com.icaro.agenda.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.icaro.agenda.gateway.ForumApi;
import br.com.icaro.agenda.repository.AgendaRepository;
import br.com.icaro.agenda.service.strategy.factory.CriaAgendaStrategyFactory;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaCartorioStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaClienteStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaEscritorioStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaForumDaApiStrategy;
import br.com.icaro.agenda.service.strategy.impl.CriaAgendaForumStrategy;

@Configuration
public class AgendaStrategyFactoryConfiguration {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private ForumApi forumApi;
	
	@Bean
	public CriaAgendaStrategyFactory configureAgendaStrategy() {
		return new CriaAgendaStrategyFactory(
					new CriaAgendaForumStrategy(agendaRepository, forumApi), 
					new CriaAgendaForumDaApiStrategy(agendaRepository),
					new CriaAgendaClienteStrategy(agendaRepository), 
					new CriaAgendaEscritorioStrategy(agendaRepository), 
					new CriaAgendaCartorioStrategy(agendaRepository));
	}

}
