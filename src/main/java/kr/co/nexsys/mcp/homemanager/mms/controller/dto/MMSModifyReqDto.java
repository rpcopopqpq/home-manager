package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSModifyReqDto {
    private String mrn;
    private String ip;
    private Integer port;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
