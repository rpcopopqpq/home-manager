package kr.co.nexsys.mcp.homemanager.exception;

import lombok.Getter;


@Getter
public enum ErrorCode {

    NOT_FOUND_PARAMETER("HM02001R", "필수 입력 값이 누락 되었습니다."),
    INVALID_PARAMETER("HM2002R", "데이터 형식이 틀립니다."),

    NO_RESULT_DATA("HM03001N", "조회된 데이터가 없습니다"),
    NO_MRN_DATA("HM03002N", "MRN이 존재하지 않습니다."),

    ERROR_SYSTEM("HM99001R", "시스템 오류가 발생하였습니다.");


    private final String code;
    private final String message;

    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
