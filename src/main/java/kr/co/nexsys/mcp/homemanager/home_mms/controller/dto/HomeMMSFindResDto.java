package kr.co.nexsys.mcp.homemanager.home_mms.controller.dto;

import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HomeMMSFindResDto {
    private String mrn;
    private MMSDto homeMmsDto;
}
