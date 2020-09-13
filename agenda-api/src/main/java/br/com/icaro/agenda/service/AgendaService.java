package br.com.icaro.agenda.service;

import java.time.LocalDate;
import java.util.List;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.TipoAgenda;

public interface AgendaService {
	
	Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda);
	
	List<Agenda> listaAgendas();
}
