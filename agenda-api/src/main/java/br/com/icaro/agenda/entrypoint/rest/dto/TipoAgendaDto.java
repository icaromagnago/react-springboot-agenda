package br.com.icaro.agenda.entrypoint.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel
@Value
public class TipoAgendaDto {
	
	@ApiModelProperty(position = 0, value = "Valor da enum", example = "FORUM")
	private final String valor;
	
	@ApiModelProperty(position = 0, value = "Descricao da enum", example = "Fórum")
	private final String descricao;
}
