package br.com.icaro.agenda.entrypoint.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.gateway.ForumApi;
import br.com.icaro.agenda.service.strategy.factory.CriaAgendaStrategyFactory;

@Component
public class CriaAgendaForumScheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CriaAgendaForumScheduler.class);
	
	@Autowired
	private ForumApi forumApi;
	
	@Autowired
	private CriaAgendaStrategyFactory criaAgendaStrategyFactory;
	
	@Scheduled(fixedRateString = "${cria.agenda.forum.scheduler.frequencia}")
	public void iniciaScheduler() {

		LOGGER.info("Job Cria Agenda Forum iniciado...");
		
		var agendas = forumApi.obterAgendas();
		var strategy = criaAgendaStrategyFactory.getAgendaForumDaApiStrategy();
		
		agendas.forEach(agendaDto -> {
			var agenda = strategy.criar(TipoAgenda.FORUM, agendaDto.getDescricao(), agendaDto.getData());
			
			LOGGER.info("Nova agenda criada: " + agenda);
		});
	}
	
	@ExceptionHandler({ Exception.class })
	public void handleException(Exception e) {
		LOGGER.error(e.getMessage(), e);
	}
}
