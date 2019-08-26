package kr.co.nexsys.mcp.homemanager.exception;

import lombok.Getter;

@Getter
public class NullResultException extends RuntimeException {
    private String code;

    public NullResultException(String code){
        this.code = code;
    }

}
