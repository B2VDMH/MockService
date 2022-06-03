package hu.gdf.thesis.MockService;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "response.directory")
@Configuration("path")
@Data
public class PathConfiguration {

    private String path;

}
