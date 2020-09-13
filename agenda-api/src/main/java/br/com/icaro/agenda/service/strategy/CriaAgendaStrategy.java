package br.com.icaro.agenda.service.strategy;

import java.time.LocalDate;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.TipoAgenda;

public interface CriaAgendaStrategy {
	Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda);
}
