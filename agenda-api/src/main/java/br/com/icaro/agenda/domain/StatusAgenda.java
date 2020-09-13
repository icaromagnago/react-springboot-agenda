package br.com.icaro.agenda.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusAgenda {
	ENVIAR_EMAIL("Enviar e-mail"),
	AGENDAR_CARTORIO("Agendar cartório"),
	ENVIADO_FORUM("Enviado fórum"),
	CRIAR_TESE("Criar tese");
	
	private String descricao;
}
