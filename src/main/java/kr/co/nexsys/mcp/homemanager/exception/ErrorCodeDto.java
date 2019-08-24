package kr.co.nexsys.mcp.homemanager.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorCodeDto {
    private String code;
    private String message;
}
