package hu.gdf.thesis.MockService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The request was invalid")
public class ResponseHandler {

    @Autowired
    PathConfiguration pathConfiguration;

    private static final String SERVER_LIFE_CYCLE_RUNTIMES = "/domainLevel/domainRuntime";
    private static final String JDBC_SYSTEM_RESOURCES = "/domainLevel/JDBCSystemResources";
    private static final String PARTITION_1 = "/partitionLevel/partition1/Partition1ResourceGroup";
    private static final String PARTITION_2 = "/partitionLevel/partition2/Partition2ResourceGroup";

    private Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);


    @GetMapping(SERVER_LIFE_CYCLE_RUNTIMES + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String healthResponse(@PathVariable String id) {
        String filePath = filePathBuilder(SERVER_LIFE_CYCLE_RUNTIMES, id).toString();
        try {
            LOGGER.info("Server Life Cycle Runtimes request received.");
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(JDBC_SYSTEM_RESOURCES + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String jdbcSysResourceResponse(@PathVariable String id) {
        String filePath = filePathBuilder(JDBC_SYSTEM_RESOURCES, id).toString();
        try {
            LOGGER.info("JDBC system resource request received.");
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(PARTITION_1 + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String partition1ResourceGroup(@PathVariable String id) {
        String filePath = filePathBuilder(JDBC_SYSTEM_RESOURCES, id).toString();
        try {
            LOGGER.info("Request for partition 1 received.");
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(PARTITION_2 + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String partition2ResourceGroup(@PathVariable String id) {
        String filePath = filePathBuilder(PARTITION_2, id).toString();
        try {
            LOGGER.info("Request for partition 2 received.");
            return new String(Files.readAllBytes(Paths.get(filePath + ".json")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/httpErrorTest")
    public String httpErrorTest() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private StringBuilder filePathBuilder(String restPath, String id) {
        StringBuilder builder = new StringBuilder();
        builder.append(pathConfiguration.getPath());
        String path = restPath;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")) {
            path = restPath.replaceAll("/", "\\\\");
        }
        builder.append(path);
        builder.append(File.separator);
        builder.append(restPath);
        builder.append(id);
        builder.append(File.separator);
        builder.append(randomInt());

        return builder;
    }

    private int randomInt() {
        Random random = new Random();
        return 1 + random.nextInt(5);
    }
}
