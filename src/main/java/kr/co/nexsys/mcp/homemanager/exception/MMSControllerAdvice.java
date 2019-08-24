package kr.co.nexsys.mcp.homemanager.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MMSControllerAdvice {

    @ExceptionHandler(NullResultException.class)
    public ErrorCodeDto handleNullResultException(NullResultException e){
        return ErrorCodeDto.builder()
                .code(ErrorCode.NO_RESULT_DATA.getCode())
                .message(ErrorCode.NO_RESULT_DATA.getMessage())
                .build();
    }

}
