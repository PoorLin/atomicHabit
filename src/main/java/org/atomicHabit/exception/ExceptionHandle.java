package org.atomicHabit.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.atomicHabit.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.atomicHabit.constance.habitConst.JWT_EXPIRED;

@ControllerAdvice
public class ExceptionHandle {
    private Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler
    public ResponseEntity<String> expiredJwtException(ExpiredJwtException expiredJwtException){

        log.info("ExpiredJwtException{}",expiredJwtException);
        log.info("ExpiredJwtException happen");
        return new ResponseEntity( new Result(JWT_EXPIRED, "登入逾期，請重新登入"), HttpStatus.BAD_REQUEST);
    }

}
