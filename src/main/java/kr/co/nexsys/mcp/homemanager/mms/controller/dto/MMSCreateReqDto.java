package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSCreateReqDto {
    List<MMSDto> MMSList;
}
