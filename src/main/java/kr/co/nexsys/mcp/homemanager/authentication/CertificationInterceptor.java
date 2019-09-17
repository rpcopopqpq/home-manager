package kr.co.nexsys.mcp.homemanager.authentication;

import kr.co.nexsys.mcp.homemanager.exception.AuthenticationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Component
public class CertificationInterceptor implements HandlerInterceptor {

    @Autowired
    private ClientVerifier clientVerifier = new ClientVerifier();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("GET")){
            return true;
        }else {
            if (request.getHeader("srcMRN") != null && request.getHeader("hexSignedData") !=null) {
                if (clientVerifier.verifyClient(request.getHeader("srcMRN"),request.getHeader("hexSignedData"))) {
                    return true;
                } else {
                    throw new AuthenticationException();
                }
            } else {
                throw new AuthenticationException();
            }
        }
    }

    /*
    private String getBody(HttpServletRequest request){
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        Object obj = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            throw new AuthenticationException();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new AuthenticationException();
                }
            }
        }
        try{
            obj = parser.parse(stringBuilder.toString());
        }catch(ParseException e){
            throw new AuthenticationException();
        }
        jsonObject = (JSONObject)obj;

        return (String)jsonObject.get("mrn");
    }
*/
}
