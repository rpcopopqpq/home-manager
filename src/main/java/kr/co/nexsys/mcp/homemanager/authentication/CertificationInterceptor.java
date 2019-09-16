package kr.co.nexsys.mcp.homemanager.authentication;

import kr.co.nexsys.mcp.homemanager.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CertificationInterceptor implements HandlerInterceptor {

    @Autowired
    private ClientVerifier clientVerifier = new ClientVerifier();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("GET")){
            return true;
        }else {
            if (request.getHeader("srcMRN") != null) {
                if (clientVerifier.verifyClient(request.getParameter("mrn"), request.getHeader("srcMRN"))) {
                    return true;
                } else {
                    throw new AuthenticationException();
                }
            } else {
                throw new AuthenticationException();
            }
        }
    }
}
