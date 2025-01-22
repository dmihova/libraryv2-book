package com.tinqin.libraryv2.book.core.aspects;


import com.tinqin.libraryv2.book.apiadapter.errors.BeError;
import com.tinqin.libraryv2.book.apiadapter.operations.base.ProcessorInput;
import io.vavr.control.Either;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(1)
public class ValidationAspect {
    private Validator validator;


    @PostConstruct
    public void init() {
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }


    @Around("execution(* com.tinqin.libraryv2.book.core.processors..*.process(..))")
    public Object validateInput(ProceedingJoinPoint joinPoint) throws Throwable {

        ProcessorInput operationInput = (ProcessorInput) joinPoint.getArgs()[0];

        Set<ConstraintViolation<ProcessorInput>> constraintViolations = validator.validate(operationInput);

        if (constraintViolations.isEmpty()) {
            return joinPoint.proceed();
        }

        String errorMessage = constraintViolations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(System.lineSeparator()));

        return Either.left(BeError
                .builder()
                .errorCode("INVALID_INPUT")
                .message(errorMessage)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }
}
