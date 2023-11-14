package pt.bmo.list4u.api.shoppinglist.config.r2dbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.r2dbc")
public class PostgresProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String database;
}
