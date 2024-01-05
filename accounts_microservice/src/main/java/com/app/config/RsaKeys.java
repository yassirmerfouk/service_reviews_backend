package com.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Setter @Getter
public class RsaKeys {

    @Value("${rsa.private-key}")
    private RSAPrivateKey rsaPrivateKey;
    @Value("${rsa.public-key}")
    private RSAPublicKey rsaPublicKey;
}
