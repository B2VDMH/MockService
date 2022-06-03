package hu.gdf.thesis.MockService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@RestController
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The server has not found anything matching the request")
@Slf4j
public class ResponseHandler {

    @Autowired
    PathConfiguration pathConfiguration;

    private static final String SERVER_LIFE_CYCLE_RUNTIMES = "/domainLevel/domainRuntime";
    private static final String JDBC_SYSTEM_RESOURCES = "/domainLevel/JDBCSystemResources";
    private static final String PARTITION_1 = "/partitionLevel/partition1/Partition1ResourceGroup";
    private static final String PARTITION_2 = "/partitionLevel/partition2/Partition2ResourceGroup";


    @GetMapping(SERVER_LIFE_CYCLE_RUNTIMES + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String healthResponse(@PathVariable String id) {
        String filePath = filePath(SERVER_LIFE_CYCLE_RUNTIMES, id).toString();
        try {
            log.info("Server Life Cycle Runtimes request received.");
            log.info(filePath);
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(JDBC_SYSTEM_RESOURCES + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String jdbcSysResourceResponse(@PathVariable String id) {
        String filePath = filePath(JDBC_SYSTEM_RESOURCES, id).toString();
        try {
            log.info("JDBC system resource request received.");
            log.info(filePath);
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(PARTITION_1 + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String partition1ResourceGroup(@PathVariable String id) {
        String filePath = filePath(JDBC_SYSTEM_RESOURCES, id).toString();
        try {
            log.info("Request for partition 1 received.");
            log.info(filePath);
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(PARTITION_2 + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String partition2ResourceGroup(@PathVariable String id) {
        String filePath = filePath(PARTITION_2, id).toString();
        try {
            log.info("Request for partition 2 received.");
            log.info(filePath);
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/httpErrorTest")
    public String httpErrorTest() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String filePath(String restPath, String id) {
        StringBuilder builder = new StringBuilder();
        builder.append(pathConfiguration.getPath());
        builder.append(restPath);
        builder.append(File.separator);
        builder.append(id);
        builder.append(File.separator);
        builder.append(randomInt());
        String path = builder.toString();

        if(System.getProperty("os.name").toLowerCase().startsWith("win")) {
            return path.replaceAll("/", "\\\\");
        }
        return path;
    }

    private int randomInt() {
        Random random = new Random();
        return 1 + random.nextInt(5);
    }
}
