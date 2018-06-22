package com.ananda.aop;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @Autowired
//    LogService logService;

    public RestResponseEntityExceptionHandler() {
        super();
    }

    private Map<String, Object> buildErrorResponse(String exceptionName, String exceptionMessage, String message) {
        Map<String, Object> output = new HashMap<>();
        output.put("exception", exceptionName);
        output.put("exception-message", exceptionMessage);
        output.put("message", message);
        return output;
    }

    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    protected ResponseEntity<Object> programingExceptionError(final RuntimeException ex, final WebRequest request) throws Exception {
//        errorLogRepository.insertSapError("RunExp", "E", ex.getMessage());
//        logService.actionLog("RunExp\tE\t" + ex.getMessage());
        return new ResponseEntity(buildErrorResponse(ex.getClass().getName(),ex.getMessage(),""), HttpStatus.OK);
//        return new ResponseEntity(new ResultTemplate(ResponseMessage.EXCEPTION_DEV, ResponseCode.ERROR, ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {SQLException.class, java.net.SocketException.class, DataAccessException.class})
    protected ResponseEntity<Object> handleConnectFail(final SQLException ex, final WebRequest request) throws Exception {
        return new ResponseEntity(buildErrorResponse(ex.getClass().getName(),ex.getMessage(),""), HttpStatus.OK);
    }

    //    @ExceptionHandler(value = {SystemConditionException.class})
//    protected ResponseEntity<Object> handleSystemConditionError(final SystemConditionException ex, final WebRequest request) throws Exception {
//        logService.actionLog("ConditionExp\tE\t" + ex.getMessage());
//        return new ResponseEntity(new ResultTemplate(ResponseMessage.EXCEPTION_CONDITION, ResponseCode.ERROR, ex.getMessage()), HttpStatus.OK);
//
//    }

//    @ExceptionHandler(value = {LineException.class})
//    protected ResponseEntity<Object> handleIdpCognitoException(final LineException ex, final WebRequest request) throws Exception {
//        return new ResponseEntity(buildErrorResponse(ex.getClass().getName(),ex.getMessage(),""), HttpStatus.OK);
//
//    }

    @ExceptionHandler(value = {NumberFormatException.class})
    protected ResponseEntity<Object> handleNumberFormatException(final NumberFormatException ex, final WebRequest request) throws Exception {
        return new ResponseEntity(buildErrorResponse(ex.getClass().getName(),ex.getMessage(),""), HttpStatus.OK);

    }

}
