package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourcesUtil {

    public static String getResourceContent(String path) throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(Paths.get(ResourcesUtil.class.getResource(path).toURI())));
    }
}
