package br.com.orange.contadigital.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ObjetoErroDTO> handleMethodNotValid(MethodArgumentNotValidException exception) {
        List<ObjetoErroDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.stream().forEach(e -> {
            ObjetoErroDTO erro = new ObjetoErroDTO(e.getField(), e.getDefaultMessage());
            dto.add(erro);
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({RecursoNaoEncontradoException.class})
    public ObjetoErroDTO handleRecursoNaoEncontrado(RecursoNaoEncontradoException exception) {
        return exception.getObjetoErroDTO();
    }

    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RegraNegocioException.class})
    public ObjetoErroDTO handleErroRegraNegocio(RegraNegocioException exception) {
        return exception.getObjetoErroDTO();
    }

    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public String handleErroRegraNegocio(Exception exception) {
        logger.warn(String.valueOf(exception.getStackTrace()));
        return "Tivemos um erro, tente novamente mais tarde.";
    }
}
