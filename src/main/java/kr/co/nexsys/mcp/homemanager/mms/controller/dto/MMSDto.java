package kr.co.nexsys.mcp.homemanager.mms.controller.dto;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MMSDto {
    @NotEmpty @Pattern(regexp = "(urn:mrn:kr:)[a-z]\\w{0,6}:[a-z]\\w{0,10}:.*")
    private String mrn;
    @NotEmpty @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ip;
    @NotNull @Min(0) @Max(65535)
    private Integer port;
}
