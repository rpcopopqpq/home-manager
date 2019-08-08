package kr.co.nexsys.mcp.homemanager.test.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private Integer id;
    private String name;
    private String department;
    private Integer age;
    private LocalDateTime createDate;
}
