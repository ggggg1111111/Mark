import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFiles {

    public String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    public void writeFile(String filePath, String text) throws IOException {
        Files.writeString(Path.of(filePath), text);
    }
}