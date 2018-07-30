package com.github.bjconlan.pccwglobaldemo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller advice handler for mapping exceptions (beyond those already
 * handled by the spring boot auto configuration).
 *
 * @since 0.0.1
 */
@RestControllerAdvice
class ExceptionAdvice {
	private final ErrorAttributes errorAttributes;

	@Autowired
	public ExceptionAdvice(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	/**
	 * Handles any 'EntityNotFoundException's in the rest controllers and
	 * return a 404 error (instead of the default 500 used for general
	 * exceptions)
	 *
	 * @param request the http request that triggered the exception
	 * @return a serializable payload which provides details regarding the
	 *         exception (following in the style of spring boot)
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> notFound(HttpServletRequest request) {
		Map<String, Object> body = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), false);
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
		return body;
	}
}
