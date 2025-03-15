/*
package org.khanhdunk.web_dat_ve_xem_phim.Config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Trả về mã lỗi 401 Unauthorized khi không có quyền truy cập
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Token is missing or invalid");
    }
}
*/
