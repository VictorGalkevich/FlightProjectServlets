package model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.validator.Error;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{
    private final List<Error> errors;
}
