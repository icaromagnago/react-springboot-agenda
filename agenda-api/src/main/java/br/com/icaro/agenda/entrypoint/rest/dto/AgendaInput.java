package br.com.icaro.agenda.entrypoint.rest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.icaro.agenda.domain.TipoAgenda;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Dto para a criação de uma nova agenda")
@Data
@NoArgsConstructor
public class AgendaInput {
	
	@ApiModelProperty(position = 0, value = "Tipo da agenda a ser criada", example = "FORUM")
	@NotNull(message = "O tipo da agenda é obrigatório")
	private TipoAgenda tipoAgenda;
	
	@ApiModelProperty(position = 1, value = "Descricao da agenda", example = "Agenda para forum")
	@Size(max = 255, message = "A descrição deve ter no máximo {max} caractéres")
	private String descricao;
	
	@ApiModelProperty(position = 2, value = "Data da agenda", example = "2020-09-12")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "A data da agenda é obrigatório")
	private LocalDate dataAgenda;
}
