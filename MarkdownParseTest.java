import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    
    @Test
    public void checkLinks() throws IOException {
        String filename = "test-file.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }
    @Test
    public void checkIncorrect() throws IOException {
        String filename = "incorrect.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(new ArrayList<>(), links);
    }
    @Test
    public void checkImage() throws IOException {
        String filename = "image.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(List.of("google.com"), links);
    }
    @Test
    public void checkNew() throws IOException {
        String filename = "new-file.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(List.of("google.com", "some-()()([][][][])()()page().html"), links);
    }
    /*
    @Test
    public void checkImage2() throws IOException {
        String filename = "test-file-image.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(List.of("something.com"), links);
    }
    @Test
    public void checkInfinite() throws IOException {
        String filename = "test-file-infinite.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(List.of("boo.com()"), links);
    }
    @Test
    public void checkMissingParen() throws IOException {
        String filename = "test-file-missing-paren.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)););
        assertEquals(List.of("itsalink.com"), links);
    }
    */
}