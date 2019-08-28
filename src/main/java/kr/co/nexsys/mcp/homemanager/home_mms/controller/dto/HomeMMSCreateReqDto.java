package kr.co.nexsys.mcp.homemanager.home_mms.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeMMSCreateReqDto {
    @NotEmpty @Pattern(regexp = "^(urn:mrn:smart:)[a-z]\\w{0,5}:[a-z]\\w{0,5}:[a-z]\\w{0,5}:[a-zA-Z]\\w{0,10}")
    private String mrn;
    @NotEmpty
    private String mrn_mms;
}
