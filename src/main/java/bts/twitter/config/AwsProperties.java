package bts.twitter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {

    private String secret;
    private String access;
    private String bucket;
    private String url;
}
