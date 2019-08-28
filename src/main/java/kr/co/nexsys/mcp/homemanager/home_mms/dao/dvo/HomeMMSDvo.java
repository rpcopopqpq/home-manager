package kr.co.nexsys.mcp.homemanager.home_mms.dao.dvo;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name="ENTITY_HOME_MMS")
@Builder
@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeMMSDvo {
    @Id
    private String mrn;
    private String mrn_mms;
    @Column(name = "create_date",updatable = false)
    @CreationTimestamp
    private LocalDateTime create_date;
    @Column(name="update_date",insertable = false)
    @UpdateTimestamp
    private LocalDateTime update_date;
}
