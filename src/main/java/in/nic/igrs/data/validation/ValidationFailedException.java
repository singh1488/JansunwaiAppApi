package in.nic.igrs.data.validation;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ValidationFailedException extends RuntimeException {
	
	public ValidationFailedException(String message) {
		super(message);
	}
}
