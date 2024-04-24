package br.com.larazos.lazarosserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotValidException extends RuntimeException{

	private static final long serialVersionUID = 1L;


	public ResourceNotValidException(String message) {
		super(message);
	}
}