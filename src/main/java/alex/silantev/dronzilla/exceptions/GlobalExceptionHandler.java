package alex.silantev.dronzilla.exceptions;

import alex.silantev.dronzilla.dto.ErrorResponse;
import alex.silantev.dronzilla.enums.ErrorCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class, ConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ErrorCode.VALIDATION_ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BizServiceException.class)
    public ResponseEntity<ErrorResponse> bizServiceException(BizServiceException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.BAD_REQUEST);
    }
}
