package br.com.icaro.agenda.entrypoint.rest.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.entrypoint.rest.AgendaController;
import br.com.icaro.agenda.entrypoint.rest.dto.AgendaInput;
import br.com.icaro.agenda.entrypoint.rest.dto.ListaAgendaDto;
import br.com.icaro.agenda.entrypoint.rest.dto.TipoAgendaDto;
import br.com.icaro.agenda.entrypoint.rest.response.BaseResponse;
import br.com.icaro.agenda.service.AgendaService;

@RestController
@RequestMapping("/agendas")
public class AgendaControllerImpl implements AgendaController {
	
	@Autowired
	private AgendaService agendaService; 

	@PostMapping
	public ResponseEntity<BaseResponse<Agenda>> novaAgenda(@RequestBody @Valid AgendaInput agendaInput) {
		var agenda = agendaService.criar(agendaInput.getTipoAgenda(), agendaInput.getDescricao(), agendaInput.getDataAgenda());
		
		var response = new BaseResponse<Agenda>(HttpStatus.CREATED.getReasonPhrase(), agenda);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse<List<ListaAgendaDto>>> listaAgendas() {
		var agendas = agendaService.listaAgendas();
		
		var agendasDto = agendas.stream()
				.map(agenda -> new ListaAgendaDto(
						agenda.getId(), 
						agenda.getDataInicio(), 
						agenda.getStatus() != null ? agenda.getStatus().getDescricao() : "-"))
				.collect(Collectors.toList());
		
		var responseBase = new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), agendasDto);
		
		return ResponseEntity.ok(responseBase);
	}
	
	@GetMapping("/tipos")
	public ResponseEntity<BaseResponse<List<TipoAgendaDto>>> listaTiposAgenda() {
		var tipos = List.of(TipoAgenda.values())
				.stream()
				.map(tipoAgenda -> new TipoAgendaDto(tipoAgenda.name(), tipoAgenda.getDescricao()))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new BaseResponse<List<TipoAgendaDto>>(HttpStatus.OK.getReasonPhrase(), tipos));
	}
}
