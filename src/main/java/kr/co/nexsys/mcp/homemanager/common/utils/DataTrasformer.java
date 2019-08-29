package kr.co.nexsys.mcp.homemanager.common.utils;

import kr.co.nexsys.mcp.homemanager.common.property.AppProperty;
import org.springframework.stereotype.Component;

@Component
public class DataTrasformer {

    private final AppProperty appProperty;
    private final String userMrnPrefix;

    public DataTrasformer(AppProperty appProperty) {
        this.appProperty = appProperty;
        this.userMrnPrefix = userMrnPrefix(appProperty.getOrganization());
    }

    private static String userMrnPrefix(String organization) {
        return organization.replaceFirst(":org:", ":user:");
    }

    public String getUserMrnPrefix() {
        return userMrnPrefix;
    }

    public String toUserMrn(String userId) {
        return userMrnPrefix + ":" + userId;
    }
}
