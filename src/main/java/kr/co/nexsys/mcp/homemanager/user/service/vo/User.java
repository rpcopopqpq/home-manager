package kr.co.nexsys.mcp.homemanager.user.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String userId;
    private String mrn;
    private String email;
    private String permissions;
    private String firstName;
    private String lastName;
    private String password;
}
