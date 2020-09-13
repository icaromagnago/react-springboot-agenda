package br.com.icaro.agenda.gateway;

import java.util.List;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.gateway.dto.AgendaDto;

public interface ForumApi {
	
	void criaAgenda(Agenda agenda);
	
	List<AgendaDto> obterAgendas();
}
