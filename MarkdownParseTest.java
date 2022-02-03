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
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, List.of("https://something.com", "some-page.html"));
    }
    @Test
    public void checkIncorrect() throws IOException {
        String filename = "incorrect.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, new ArrayList<>());
    }
    @Test
    public void checkImage() throws IOException {
        String filename = "image.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, List.of("google.com"));
    }
    @Test
    public void checkNew() throws IOException {
        String filename = "new-file.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, List.of("google.com", "some-()()([][][][])()()page().html"));
    }
    @Test
    public void checkImage2() throws IOException {
        String filename = "test-file-image.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, List.of("something.com"));
    }
    @Test
    public void checkInfinite() throws IOException {
        String filename = "test-file-infinite.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, List.of("boo.com()"));
    }
    @Test
    public void checkMissingParen() throws IOException {
        String filename = "test-file-missing-paren.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links, List.of("itsalink.com"));
    }
}