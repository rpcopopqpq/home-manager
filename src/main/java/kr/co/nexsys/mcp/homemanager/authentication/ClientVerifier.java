package kr.co.nexsys.mcp.homemanager.authentication;


import lombok.Data;
import net.etri.pkilib.server.ServerPKILibrary;
import net.etri.pkilib.tool.ByteConverter;
import org.springframework.stereotype.Component;


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

    public String getAutenticationUrl(String mrn, String hexSignedData){
        /*pkilib 에 메소드 추가 될 때 그 메소드 작성*/
        //String url = ServerPKILibrary.get ...

        //return url;
        return "testUrl";
    }

    private void authenticateUsingMIRAPI(String mrn, String hexSignedData) {

        serverPKILib = ServerPKILibrary.getInstance();

        byteConverter = ByteConverter.getInstance();
        signedData = byteConverter.hexToByteArray(hexSignedData);

        isVerified = serverPKILib.verifySignedData(signedData);
        isMatching = serverPKILib.getSubjectMRN(signedData).equals(mrn);
    }

}
