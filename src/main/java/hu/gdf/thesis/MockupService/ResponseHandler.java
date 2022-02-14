package hu.gdf.thesis.MockupService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ResponseHandler {
private String fileLocationPlaceholder = "C:\\ResponseFolder\\placeholder";
    @GetMapping("/config/{id}")
    public String JSONResponse(@PathVariable  String id) throws IOException {

        return new String (Files.readAllBytes(Paths.get(fileLocationPlaceholder.replace("placeholder", id + ".json"))));

    }

    @GetMapping("/config/jsonTest")
    public String FixResponse() throws IOException {
        return new String (Files.readAllBytes(Paths.get(fileLocationPlaceholder.replace("placeholder", "jsonTest" + ".json"))));
    }
}
