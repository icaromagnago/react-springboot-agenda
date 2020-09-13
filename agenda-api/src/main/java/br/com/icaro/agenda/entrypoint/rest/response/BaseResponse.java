package br.com.icaro.agenda.entrypoint.rest.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse<T> {

	private List<String> messages = new ArrayList<String>();
	
	private T data;
	
	public BaseResponse(String message, T data) {
		this.messages.add(message);
		this.data = data;
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
}
