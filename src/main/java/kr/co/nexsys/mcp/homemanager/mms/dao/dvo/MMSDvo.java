package kr.co.nexsys.mcp.homemanager.mms.dao.dvo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MMS")
@Builder
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MMSDvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String mrn;
    private String ip;
    private Integer port;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
