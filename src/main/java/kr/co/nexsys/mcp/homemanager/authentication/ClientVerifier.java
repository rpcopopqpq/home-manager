package kr.co.nexsys.mcp.homemanager.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.etri.pkilib.server.ServerPKILibrary;
import net.etri.pkilib.tool.ByteConverter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ClientVerifier {
    private ByteConverter byteConverter;
    private byte[] signedData;
    private ServerPKILibrary serverPKILib;
    private boolean isMatching;
    private boolean isVerified;

    public boolean verifyClient(String mrn, String hexSignedData){

        authenticateUsingMIRAPI(mrn, hexSignedData);

        if(isVerified && isMatching) {
            return true;
        } else {
            return false;
        }
    }

    private void authenticateUsingMIRAPI(String mrn, String hexSignedData) {

        serverPKILib = ServerPKILibrary.getInstance();

        byteConverter = ByteConverter.getInstance();
        signedData = byteConverter.hexToByteArray(hexSignedData);

        isVerified = serverPKILib.verifySignedData(signedData);
        isMatching = serverPKILib.getSubjectMRN(signedData).equals(mrn);
    }

}
