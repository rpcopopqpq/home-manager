package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MMSResponseDto {
    private List<MMSDto> MMSInfo;
    private String message;
}
