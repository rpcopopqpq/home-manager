package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSDto {
    private String mrn;
    private String ip;
    private Integer port;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
