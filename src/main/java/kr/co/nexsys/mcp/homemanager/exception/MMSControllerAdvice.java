package kr.co.nexsys.mcp.homemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MMSControllerAdvice {

    @ExceptionHandler(NullResultException.class)
    public ResponseEntity<ErrorCodeDto> handleNullResultException(NullResultException e){
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        switch (e.getCode()){
            case "HM03001N":
                errorCodeDto.setCode(ErrorCode.NO_RESULT_DATA.getCode());
                errorCodeDto.setMessage(ErrorCode.NO_RESULT_DATA.getMessage());
                break;
            case "HM03002N":
                errorCodeDto.setCode(ErrorCode.NO_MRN_DATA.getCode());
                errorCodeDto.setMessage(ErrorCode.NO_MRN_DATA.getMessage());
                break;
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCodeDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCodeDto> handleMethodArgumentnotValidException(MethodArgumentNotValidException e){
            ErrorCodeDto errorCodeDto = new ErrorCodeDto();
            String code ="";
            BindingResult bindingResult = e.getBindingResult();

            //valid 에러 발생시키는 어노테이션 찾기, 여러개 일 경우 마지막 에러코드로 보냄
           //1.필수값 에러 2.패턴 에러 순으로 발생 시 패턴 에러로 메세지 출력
            for(FieldError fieldError : bindingResult.getFieldErrors()){
                code =fieldError.getCode();
            }

            switch (code){
                case "Patern" :
                    errorCodeDto.setCode(ErrorCode.INVALID_PARAMETER.getCode());
                    errorCodeDto.setMessage(ErrorCode.INVALID_PARAMETER.getMessage());
                    break;
                case "NotEmpty" :
                    errorCodeDto.setCode(ErrorCode.NOT_FOUND_PARAMETER.getCode());
                    errorCodeDto.setMessage(ErrorCode.NOT_FOUND_PARAMETER.getMessage());
                    break;
                case "Max" :
                    errorCodeDto.setCode(ErrorCode.INVALID_PARAMETER.getCode());
                    errorCodeDto.setMessage(ErrorCode.INVALID_PARAMETER.getMessage());
                    break;
                case "Min" :
                    errorCodeDto.setCode(ErrorCode.INVALID_PARAMETER.getCode());
                    errorCodeDto.setMessage(ErrorCode.INVALID_PARAMETER.getMessage());
                    break;
                default:
                    errorCodeDto.setCode(ErrorCode.INVALID_PARAMETER.getCode());
                    errorCodeDto.setMessage(ErrorCode.INVALID_PARAMETER.getMessage());
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCodeDto);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorCodeDto> handleAlreadyExistException(AlreadyExistException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorCodeDto.builder()
                        .code(ErrorCode.ALREADY_EXIST_DATA.getCode())
                        .message(ErrorCode.ALREADY_EXIST_DATA.getMessage())
                        .build());
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorCodeDto> handleSystemException(SystemException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorCodeDto.builder()
                                .code(ErrorCode.ERROR_SYSTEM.getCode())
                                .message(ErrorCode.ERROR_SYSTEM.getMessage())
                                .build());
    }

}
