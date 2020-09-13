package br.com.icaro.agenda.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.repository.AgendaRepository;
import br.com.icaro.agenda.service.AgendaService;
import br.com.icaro.agenda.service.strategy.factory.CriaAgendaStrategyFactory;

@Service
public class AgendaServiceimpl implements AgendaService {
	
	@Autowired
	private CriaAgendaStrategyFactory criaAgendaStrategyFactory;
	
	@Autowired
	private AgendaRepository agendaRepository;

	@Override
	public Agenda criar(TipoAgenda tipoAgenda, String descricao, LocalDate dataAgenda) {
		var criaAgendaStrategy = criaAgendaStrategyFactory.getStrategyPorTipoAgenda(tipoAgenda);
		
		return criaAgendaStrategy.criar(tipoAgenda, descricao, dataAgenda);
	}

	@Override
	public List<Agenda> listaAgendas() {
		return agendaRepository.findAll(Sort.by(Order.asc("dataInicio")));
	}
}
