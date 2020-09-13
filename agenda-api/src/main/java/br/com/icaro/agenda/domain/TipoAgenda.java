package br.com.icaro.agenda.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoAgenda {
	ESCRITORIO("Escritório"),
	CLIENTE("Cliente"),
	CARTORIO("Cartório"),
	FORUM("Fórum");
	
	private String descricao;
}
