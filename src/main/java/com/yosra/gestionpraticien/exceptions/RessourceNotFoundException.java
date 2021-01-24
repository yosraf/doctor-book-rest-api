package com.yosra.gestionpraticien.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.AbstractThrowableProblem;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends AbstractThrowableProblem {

    private String errorMessage;

    public RessourceNotFoundException(String message) {
        super();
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
