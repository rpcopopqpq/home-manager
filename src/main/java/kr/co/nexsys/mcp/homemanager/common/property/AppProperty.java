package kr.co.nexsys.mcp.homemanager.common.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class AppProperty {

    private String organization;


}
