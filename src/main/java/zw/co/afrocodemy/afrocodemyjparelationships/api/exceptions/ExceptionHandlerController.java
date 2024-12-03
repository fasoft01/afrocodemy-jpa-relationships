package zw.co.afrocodemy.afrocodemyjparelationships.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zw.co.afrocodemy.afrocodemyjparelationships.service.exceptions.Error;
import zw.co.afrocodemy.afrocodemyjparelationships.service.exceptions.RecordNotFoundException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionHandlerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    Error recordNotFoundException(RecordNotFoundException e) {
        LOGGER.info("Record not found : {}", e.getMessage());
        return Error.of(404, e.getMessage());
    }
}
