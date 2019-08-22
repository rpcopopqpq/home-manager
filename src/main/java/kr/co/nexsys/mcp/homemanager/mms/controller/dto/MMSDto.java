package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
