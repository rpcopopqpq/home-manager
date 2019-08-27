package kr.co.nexsys.mcp.homemanager.homemms.controller.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeMMSDto {
    @NotEmpty @Pattern(regexp = "^(urn:mrn:smart:)[a-z]\\w{0,5}:[a-z]\\w{0,5}:[a-z]\\w{0,5}:[a-zA-Z]\\w{0,10}")
    private String mrn;
    @NotEmpty
    private String mms_mrn;
}
