package kr.co.nexsys.mcp.homemanager.exception;


import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorCodeDto handleMethodArgumentnotValidException(MethodArgumentNotValidException e){
        return ErrorCodeDto.builder()
                .code(ErrorCode.NOT_FOUND_PARAMETER.getCode())
                .message(ErrorCode.NOT_FOUND_PARAMETER.getMessage())
                .build();
    }

    @ExceptionHandler(DatabaseException.class)
    public ErrorCodeDto handleDatabaseException(DatabaseException e){
        ErrorCodeDto errorCodeDto = null;
        switch (e.getMessage()){
            case "find":
                errorCodeDto= ErrorCodeDto.builder()
                        .code(ErrorCode.DB_READ_EXCEPTION.getCode())
                        .message(ErrorCode.DB_READ_EXCEPTION.getMessage())
                        .build();
                break;
            case "create":
                errorCodeDto= ErrorCodeDto.builder()
                        .code(ErrorCode.DB_CREATE_EXCEPTION.getCode())
                        .message(ErrorCode.DB_CREATE_EXCEPTION.getMessage())
                        .build();
                break;
            case "update":
                errorCodeDto= ErrorCodeDto.builder()
                        .code(ErrorCode.DB_UPDATE_EXCEPTION.getCode())
                        .message(ErrorCode.DB_UPDATE_EXCEPTION.getMessage())
                        .build();
                break;
            case "delete":
                errorCodeDto= ErrorCodeDto.builder()
                        .code(ErrorCode.DB_DELETE_EXCEPTION.getCode())
                        .message(ErrorCode.DB_DELETE_EXCEPTION.getMessage())
                        .build();
                break;
        }
        return errorCodeDto;
    }

    @ExceptionHandler(SystemException.class)
    public ErrorCodeDto handleSystemException(SystemException e){
        return ErrorCodeDto.builder()
                .code(ErrorCode.ERROR_SYSTEM.getCode())
                .message(ErrorCode.ERROR_SYSTEM.getMessage())
                .build();
    }

}
