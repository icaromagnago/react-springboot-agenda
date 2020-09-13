package br.com.icaro.agenda.entrypoint.rest;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.icaro.agenda.entrypoint.rest.response.BaseResponse;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var message = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		
		return handleExceptionInternal(ex, new BaseResponse<>(message, null), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		
		ex.getBindingResult().getGlobalErrors().forEach(error -> baseResponse.addMessage(error.getDefaultMessage()));
		ex.getBindingResult().getFieldErrors().forEach(error -> baseResponse.addMessage(error.getField() + ": " + error.getDefaultMessage()));
		
		return handleExceptionInternal(ex, baseResponse, headers, HttpStatus.BAD_REQUEST, request);
	}
}
