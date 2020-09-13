package br.com.icaro.agenda.gateway.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.icaro.agenda.domain.Agenda;
import br.com.icaro.agenda.gateway.ForumApi;
import br.com.icaro.agenda.gateway.dto.AgendaDto;
import br.com.icaro.agenda.gateway.dto.ListaAgendasDto;

@Service
public class ForumApiImpl implements ForumApi {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.forum.url}")
	private String apiForumUrl;

	@Override
	public void criaAgenda(Agenda agenda) {
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		var agendaOutput = new AgendaDto(agenda.getId(), agenda.getDescricao(), agenda.getDataInicio());
		var request = new HttpEntity<>(agendaOutput, headers);
		
		restTemplate.postForEntity(apiForumUrl, request, String.class);
	}

	@Override
	public List<AgendaDto> obterAgendas() {
		
		ResponseEntity<ListaAgendasDto> response = restTemplate.getForEntity(apiForumUrl, ListaAgendasDto.class);
		
		return response.getBody().getContent();
	}
}
