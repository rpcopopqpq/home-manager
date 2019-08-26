package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSDto {
    @NotBlank @Pattern(regexp = "^urn:mrn:smart:\\[]{4,10}:\\[]{4,10}]:\\[]{4,10}:\\[]{4,20}")
    private String mrn;
    @NotBlank @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ip;
    @NotBlank @Min(0) @Max(65535)
    private Integer port;
}
