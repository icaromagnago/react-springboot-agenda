package br.com.icaro.agenda.entrypoint.rest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel
@Value
public class ListaAgendaDto {
	
	@ApiModelProperty(position = 0, value = "id da agenda", example = "23")
	private Long id;
	
	@ApiModelProperty(position = 1, value = "Data de início da agenda", example = "12/09/2020")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;
	
	@ApiModelProperty(position = 2, value = "status da agenda", example = "Enviado fórum")
	private String status;
}
