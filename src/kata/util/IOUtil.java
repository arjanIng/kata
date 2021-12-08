package kata.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class IOUtil {
    
    public static List<String> getResourceBody(String filename) {
        try {
            return Files.lines(Paths.get("./src/kata/advent/" + filename)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
