package br.com.icaro.agenda.entrypoint.rest.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.domain.StatusAgenda;
import br.com.icaro.agenda.domain.TipoAgenda;
import br.com.icaro.agenda.service.AgendaService;

@WebMvcTest(AgendaControllerImpl.class)
class AgendaControllerImplTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AgendaService agendaService;
	
	@Test
	void whenPostNullDataAgenda_thenReturnsBadResquest400() throws Exception {
		var agendaMissingDataAgenda = "{"
				+ "\"tipoAgenda\": \"FORUM\", "
				+ "\"descricao\": \"Nova Agenda\""
				+ "}";
		
		mockMvc
			.perform(post("/agendas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(agendaMissingDataAgenda))
			.andExpect(status().isBadRequest())
			.andDo(print())
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("dataAgenda: A data da agenda é obrigatório")));
	}
	
	@Test
	void whenPostNullTipoAgenda_thenReturnsBadResquest400() throws Exception {
		var agendaMissingTipoAgenda = "{"
				+ "\"dataAgenda\": \"2020-11-10\", "
				+ "\"descricao\": \"Nova Agenda\""
				+ "}";
		
		mockMvc
			.perform(post("/agendas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(agendaMissingTipoAgenda))
			.andExpect(status().isBadRequest())
			.andDo(print())
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("tipoAgenda: O tipo da agenda é obrigatório")));
	}
	
	@Test
	void whenPostAgenda_thenReturnsCreated201() throws Exception {
		var agendaInput = "{"
				+ "\"tipoAgenda\": \"FORUM\", "
				+ "\"dataAgenda\": \"2020-10-12\", "
				+ "\"descricao\": \"Nova Agenda\""
				+ "}";
		
		LocalDate dataAgenda = LocalDate.of(2020, 10, 12);
		LocalDate dataInicio = dataAgenda.minusDays(2);
		var agenda = Agenda.builder()
				.id(1L)
				.tipoAgenda(TipoAgenda.CARTORIO)
				.status(StatusAgenda.AGENDAR_CARTORIO)
				.dataAgenda(dataAgenda)
				.dataInicio(dataInicio)
				.descricao("Nova agenda")
				.build();
		
		when(agendaService.criar(any(), any(), any())).thenReturn(agenda);
		
		mockMvc
			.perform(post("/agendas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(agendaInput))
			.andExpect(status().isCreated())
			.andDo(print())
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("Created")))
			.andExpect(jsonPath("$.data.id", is(1)))
			.andExpect(jsonPath("$.data.status", is("AGENDAR_CARTORIO")))
			.andExpect(jsonPath("$.data.tipoAgenda", is("CARTORIO")))
			.andExpect(jsonPath("$.data.descricao", is("Nova agenda")))
			.andExpect(jsonPath("$.data.dataAgenda", is("2020-10-12")))
			.andExpect(jsonPath("$.data.dataInicio", is("2020-10-10")));
	}

	@Test
	void whenListaAgendas_thenReturnOk() throws Exception {
		var dataInicio = LocalDate.of(2020, 12, 10);
		
		var agenda1 = Agenda.builder()
				.id(1L)
				.status(StatusAgenda.AGENDAR_CARTORIO)
				.dataInicio(dataInicio)
				.build();
		
		var agenda2 = Agenda.builder()
				.id(2L)
				.dataInicio(dataInicio)
				.build();
		
		when(agendaService.listaAgendas()).thenReturn(List.of(agenda1, agenda2));
		
		mockMvc.perform(get("/agendas").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.messages", hasSize(1)))
		.andExpect(jsonPath("$.messages[0]", containsString("OK")))
		.andExpect(jsonPath("$.data", hasSize(2)))
		.andExpect(jsonPath("$.data[*].id", containsInAnyOrder(1, 2)))
		.andExpect(jsonPath("$.data[*].status", containsInAnyOrder("Agendar cartório", "-")))
		.andExpect(jsonPath("$.data[*].dataInicio", containsInAnyOrder("10/12/2020", "10/12/2020")));
	}

	@Test
	void whenListaTiposAgendas_thenReturnOk() throws Exception {
		mockMvc.perform(get("/agendas/tipos").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.messages", hasSize(1)))
		.andExpect(jsonPath("$.messages[0]", containsString("OK")))
		.andExpect(jsonPath("$.data", hasSize(4)))
		.andExpect(jsonPath("$.data[*].valor", containsInAnyOrder("FORUM", "ESCRITORIO", "CLIENTE", "CARTORIO")));
	}

}
