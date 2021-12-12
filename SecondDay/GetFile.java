import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class GetFile {
    List<String> getListFromFile(String fileName)
    {
        try {
            List<String> result = Files.readAllLines(Paths.get(fileName));
            return result;
        }
        catch (Exception e)
        {
            List<String> result = Collections.<String>emptyList();
            return result;
        }
    }
}
