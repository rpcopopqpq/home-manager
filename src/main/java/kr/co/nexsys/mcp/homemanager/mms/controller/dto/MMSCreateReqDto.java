package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSCreateReqDto {
    @NotEmpty @Pattern(regexp = "^(urn:mrn:smart:)[a-z]\\w{0,5}:[a-z]\\w{0,5}:[a-z]\\w{0,5}:[a-zA-Z]\\w{0,10}")
    private String mrn;
    @NotEmpty @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ip;
    @NotNull @Min(0) @Max(65535)
    private Integer port;
}
