package com.bt.marketplace.partnercredentials.controller.common;

import com.bt.marketplace.partnercredentials.controller.common.error.ApiErrors;
import com.bt.marketplace.partnercredentials.controller.common.error.ErrorMessagesImpl;
import com.bt.marketplace.partnercredentials.controller.common.error.ErrorRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ApplicationErrorConfigServiceImpl applicationErrorConfigService;

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErrorRepresentation> apiErrors = fieldErrors.stream()
                .map((f) -> {
                   ErrorRepresentation errorRepresentation = applicationErrorService.getErrorRepresentationByCode(ErrorMessagesImpl.INVALID_BODY_FIELD);
                   return ErrorRepresentation.builder()
                            .code(errorRepresentation.getCode())
                            .message(errorRepresentation.getMessage())
                            .description(f.getField() + ": " + f.getDefaultMessage())
                            .build(); })
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, ApiErrors.builder().errors(errorRepresentations).build(), headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiErrors> handleConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        List<ApiError> apiErrors = constraintViolations.stream()
                .map(f -> new ApiError(f.getPropertyPath().toString().split("\\.")[1], f.getMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<ApiErrors>(ApiErrors.builder().errors(apiErrors).build(), HttpStatus.BAD_REQUEST);
    }
}
