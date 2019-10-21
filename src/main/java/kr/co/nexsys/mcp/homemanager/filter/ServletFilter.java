package kr.co.nexsys.mcp.homemanager.filter;


import kr.co.nexsys.mcp.homemanager.exception.BusinessException;
import kr.co.nexsys.mcp.homemanager.exception.ErrorCode;
import kr.co.nexsys.mcp.homemanager.exception.ErrorCodeDto;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@WebFilter(urlPatterns = {"/mms/**", "/entity/**"})
public class ServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletWrapper servletWrapper = new ServletWrapper((HttpServletRequest)request);
        HttpServletResponseWrapper httpServletResponse = new HttpServletResponseWrapper((HttpServletResponse)response);
        try {
            if (servletWrapper.authenticationMMS((HttpServletRequest) request)) {
                chain.doFilter(servletWrapper, response);
            } else {
                throw new BusinessException("HM10001R");
            }
        }catch(BusinessException e){
            response.setContentType("application/json");
            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

}
