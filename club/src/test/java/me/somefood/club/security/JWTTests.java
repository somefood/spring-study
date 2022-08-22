package me.somefood.club.security;

import me.somefood.club.security.util.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JWTTests {

    private JWTUtil jwtUtil;

    @BeforeEach
    void testBefore() {
        System.out.println("testBefore...............");
        jwtUtil = new JWTUtil();
    }

    @Test
    void testEncode() throws Exception {
        String email = "user95@zerock.org";

        String str = jwtUtil.generateToken(email);

        System.out.println(str);
    }

    @Test
    void testValidate() throws Exception {

        String email = "user95@zerock.org";

        String str = jwtUtil.generateToken(email);

        Thread.sleep(5000);

        String resultEmail = jwtUtil.validateAndExtract(str);

        System.out.println(resultEmail);
    }
}
