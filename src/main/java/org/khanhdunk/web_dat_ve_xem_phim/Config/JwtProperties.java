package org.khanhdunk.web_dat_ve_xem_phim.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")// đọc nhóm cấu hình có tiền tố jwt
//Trong Spring Boot được dùng để liên kết (binding) các giá trị trong file cấu hình (application.yml hoặc application.properties)
// vào một class Java.
public class JwtProperties {
    private String signerKey;

    public String getSignerKey() {
        return signerKey;
    }

    public void setSignerKey(String signerKey) {
        this.signerKey = signerKey;
    }
}
