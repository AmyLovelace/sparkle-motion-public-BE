package com.sparklemotion.config.property;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Data
@Validated
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private Long duration;
    private String secret;
    private String testAllUpper;
}
