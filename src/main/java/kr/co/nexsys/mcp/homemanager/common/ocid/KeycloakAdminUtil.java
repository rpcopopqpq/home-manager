package kr.co.nexsys.mcp.homemanager.common.ocid;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class KeycloakAdminUtil {

    private static final Keycloak kc = Keycloak.getInstance(
            "http://localhost:8080/auth",
            "ProjectTestUsers",
            "idreg-test-idp-admin",
            "smart2016",
            "security-admin-console");


    /**
     * Creates a user in keycloak.
     *
     * @param userMrn   MRN of the user
     * @param firstName first name of user
     * @param lastName  last name of user
     * @param password  password of the user
     * @param email     email of the user
     * @param orgMrn    MRN of the org
     * @throws IOException
     */
    public static void createUser(String userMrn, String password, String firstName, String lastName, String email, String orgMrn, String permissions, boolean enabled) throws IOException {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userMrn);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCredentials(Arrays.asList(credential));
        Response response = kc.realm("master").users().create(user);
    }
}
