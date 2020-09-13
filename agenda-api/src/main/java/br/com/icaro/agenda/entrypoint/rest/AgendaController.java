package br.com.icaro.agenda.entrypoint.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.entrypoint.rest.dto.AgendaInput;
import br.com.icaro.agenda.entrypoint.rest.dto.ListaAgendaDto;
import br.com.icaro.agenda.entrypoint.rest.dto.TipoAgendaDto;
import br.com.icaro.agenda.entrypoint.rest.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Rest API para Agenda")
public interface AgendaController {
	
	@ApiOperation("Cria uma nova agenda")
	@ApiResponses({
		@ApiResponse(code = 200, response = BaseResponse.class, message = "Created"),
		@ApiResponse(code = 400, response = BaseResponse.class, message = "Bad Request")
	})
	ResponseEntity<BaseResponse<Agenda>> novaAgenda(
			@ApiParam(value = "Agenda a ser criada") @RequestBody @Valid AgendaInput agendaInput);
	
	@ApiOperation("Lista todas as agendas")
	@ApiResponse(code = 200, response = BaseResponse.class, message = "Ok")
	ResponseEntity<BaseResponse<List<ListaAgendaDto>>> listaAgendas(); 
	
	@ApiOperation("Lista os tipos de agenda existentes")
	@ApiResponse(code = 200, response = BaseResponse.class, message = "Ok")
	ResponseEntity<BaseResponse<List<TipoAgendaDto>>> listaTiposAgenda();
}
