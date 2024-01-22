package com.baby.babycareproductsshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "app.jwt.secret = PowerOverwhelmingOperationCwalBlackSheepWall",
        "app.jwt.header-scheme-name=: authorization",
        "app.jwt.token-type= Bearer",
        "app.jwt.access-token-expiry= 7200000",
        "app.jwt.refresh-token-expiry= 1296000000"
})
class BabyCareProductsShopApplicationTests {

    @Test
    void contextLoads() {
    }

}
