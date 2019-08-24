package kr.co.nexsys.mcp.homemanager.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DB_CREATE_EXCEPTION("HM01002R" , "데이터 입력 중 에러가 발생하였습니다."),
    DB_READ_EXCEPTION("HM01002R", "데이터 조회 중 에러가 발생하였습니다."),
    DB_UPDATE_EXCEPTION("HM1003U", "데이터 수정 중 에러가 발생하였습니다."),
    DB_DELETE_EXCEPTION("HM1004D", " 데이터 삭제 중 에러가 발생하였습니다."),

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
