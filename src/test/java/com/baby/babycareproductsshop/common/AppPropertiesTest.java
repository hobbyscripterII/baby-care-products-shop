package com.baby.babycareproductsshop.common;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Getter
@ExtendWith(SpringExtension.class)
@Import({AppPropertiesTest.class})
@TestPropertySource(properties = {  //yaml은 객체화 안 돼서 값 설정해주는 작업 필요
        "app.jwt.secret = PowerOverwhelmingOperationCwalBlackSheepWall",
        "app.jwt.header-scheme-name=: authorization",
        "app.jwt.token-type= Bearer",
        "app.jwt.access-token-expiry= 7200000",
        "app.jwt.refresh-token-expiry= 1296000000"
})
public class AppPropertiesTest {

    @Autowired
    private AppPropertiesTest appPropertiesTest;

    private final Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Jwt {
        private String secret;
        private String headerSchemeName;
        private String tokenType;
        private long accessTokenExpiry;
        private long refreshTokenExpiry;
        private int refreshCookieMaxAge;

        public void setRefreshTokenExpiry(long refreshTokenExpiry) {
            this.refreshTokenExpiry = refreshTokenExpiry;
            refreshCookieMaxAge = (int) refreshTokenExpiry / 1000;
        }
    }
}
