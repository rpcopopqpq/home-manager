package kr.co.nexsys.mcp.homemanager.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String userId;
    private String password;
    private String mrn;
    private String email;
    private String permissions;
    private String firstName;
    private String lastName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;


}
