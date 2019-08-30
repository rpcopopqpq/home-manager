package kr.co.nexsys.mcp.homemanager.home.mms.controller.dto;

import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HomeMMSFindResDto {
    private String mrn;
    private MMSDto homeMmsDto;
}