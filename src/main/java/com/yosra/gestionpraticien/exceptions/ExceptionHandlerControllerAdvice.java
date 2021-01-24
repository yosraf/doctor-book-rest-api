package com.yosra.gestionpraticien.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice implements ProblemHandling {


    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, @Nonnull NativeWebRequest request) {

        BindingResult bindingResult = exception.getBindingResult();
        List<ErrorDetails> fieldErrors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> fieldErrors.add(new ErrorDetails(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode())));

        Problem problem = Problem.builder()
                .withTitle("echec de validaton")
                .withStatus(defaultConstraintViolationStatus())
                .with("message", "argument invalide ou manquant")
                .with("fieldErrors", fieldErrors)
                .build();
        return this.create(exception, problem, request);
    }


    @ExceptionHandler(RessourceNotFoundException.class)
    public final ResponseEntity<Problem> handleRessourceNotFoundException(RessourceNotFoundException ex, NativeWebRequest request) {
        String errorMessage = "";
        Problem problem = null;
        if (ex.getErrorMessage() != null && StringUtils.equals(ex.getErrorMessage(), "aucune langue trouvée")) {
            errorMessage = "aucune langue recherchée trouvée";
            problem = Problem.builder()
                    .withTitle("Recherche langue")
                    .withStatus(Status.NOT_FOUND)
                    .with("message", errorMessage)
                    .build();
        } else {
            errorMessage = "aucune praticien recherché trouvé";
            problem = Problem.builder()
                    .withTitle("Recherche praticien")
                    .withStatus(Status.NOT_FOUND)
                    .with("message", errorMessage)
                    .build();
        }
        return create(ex, problem, request);
    }
}
