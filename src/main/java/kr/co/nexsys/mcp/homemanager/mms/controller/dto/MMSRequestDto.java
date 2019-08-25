package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSRequestDto {
    @NotNull
    private String mrn;
    @NotNull
    private String ip;
    @NotNull
    private Integer port;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
