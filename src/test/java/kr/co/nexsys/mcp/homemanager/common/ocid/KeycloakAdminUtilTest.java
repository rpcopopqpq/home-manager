package kr.co.nexsys.mcp.homemanager.common.ocid;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Slf4j
public class KeycloakAdminUtilTest {

    final String realm = "ProjectTestUsers";

    @Test
    public void test() {
        Keycloak kc = Keycloak.getInstance(
                "http://localhost:8080/auth",
                realm,
                "idreg-test-idp-admin",
                "smart2016",
                "security-admin-console");

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("test123");
        UserRepresentation user = new UserRepresentation();
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEnabled(true);
        user.setCredentials(Arrays.asList(credential));
        Response response = kc.realm(realm).users().create(user);

    }

    @Test
    public void testForMaster2() {
        Keycloak keycloak = Keycloak.getInstance(
                "http://localhost:8080/auth",
                "master",
                "admin",
                "smart2016",
                "admin-cli");

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("test123");
        UserRepresentation user = new UserRepresentation();
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEnabled(true);
        user.setCredentials(Arrays.asList(credential));
        Response response = keycloak.realm(realm).users().create(user);
        log.info(response.toString());
    }

    @Test
    public void testForMaster() {
        Keycloak keycloak = Keycloak.getInstance(
                "http://localhost:8080/auth",
                "master",
                "admin",
                "smart2016",
                "admin-cli");

        String accessTokenStr = keycloak.tokenManager().getAccessTokenString();
        log.info(accessTokenStr);
    }

}
