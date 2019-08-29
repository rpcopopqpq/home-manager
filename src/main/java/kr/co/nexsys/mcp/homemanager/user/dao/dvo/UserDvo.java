package kr.co.nexsys.mcp.homemanager.user.dao.dvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private String mrn;
    private String email;
    private String permissions;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
