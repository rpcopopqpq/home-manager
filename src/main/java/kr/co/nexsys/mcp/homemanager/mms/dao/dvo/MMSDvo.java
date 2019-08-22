package kr.co.nexsys.mcp.homemanager.mms.dao.dvo;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "MMS")
@Builder
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MMSDvo {
    private String mrn;
    private String ip;
    private Integer port;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
