package buddd.infrastructure.production;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@PropertySource("classpath:jdbc.properties")
@ConfigurationProperties(prefix = "jdbc")
public class Config {
  private String url;
  private String user;
  private String password;
}
