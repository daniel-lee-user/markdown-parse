import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }
    @Test
    public void checkIncorrect() throws IOException {
        String filename = "incorrect.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(new ArrayList<>(), links);
    }
    @Test
    public void checkImage() throws IOException {
        String filename = "image.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("google.com"), links);
    }
    @Test
    public void checkNew() throws IOException {
        String filename = "new-file.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("google.com", "some-()()([][][][])()()page().html"), links);
    }
    @Test
    public void checkImage2() throws IOException {
        String filename = "test-file-image.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("something.com"), links);
    }
    @Test
    public void checkInfinite() throws IOException {
        String filename = "test-file-infinite.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("boo.com()"), links);
    }
    @Test
    public void checkMissingParen() throws IOException {
        String filename = "test-file-missing-paren.md";
	    String contents = Files.readString(Path.of(filename));
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("itsalink.com"), links);
    }
}