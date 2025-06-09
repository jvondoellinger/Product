package jvondoellinger.ShopFree.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties("spring.application.cloud.aws")
public class AwsBaseConfig {
    private Endpoint endpoint;
    private Region region;
    private Credentials credentials;

    // Sub Classes
    public static class Endpoint {
        private String staticValue;

        public String getStatic() {
            return staticValue;
        }

        public void setStatic(String staticValue) {
            this.staticValue = staticValue;
        }
    }
    public static class Region {
        private String staticValue;

        public String getStatic() {
            return staticValue;
        }

        public void setStatic(String staticValue) {
            this.staticValue = staticValue;
        }
    }
    public static class Credentials {
        private String secretKey;
        private String accessKeyId;

        public String getAccessKey() {
            return accessKeyId;
        }

        public void setAccessKey(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }

    // Getter and Setters
    public Endpoint getEndpoint() {
        return endpoint;
    }
    public Credentials getCredentials() {
        return credentials;
    }
    public Region getRegion() {
        return region;
    }
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    public void setRegion(Region region) {
        this.region = region;
    }
    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }
}
