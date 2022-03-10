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
        // testing if junit works
    }
    @Test
    public void checkIncorrect() throws IOException {
        String filename = "incorrect.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(new ArrayList<>(), links);
    }
    
    @Test
    public void checkLinks() throws IOException {
        String filename = "test-file.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }
    @Test
    public void checkImage() throws IOException {
        String filename = "image.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("google.com"), links);
    }
    @Test
    public void checkNew() throws IOException {
        String filename = "new-file.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("google.com", "some-()()([][][][])()()page().html"), links);
    }
    
    @Test
    public void checkImage2() throws IOException {
        String filename = "test-file-image.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("something.com"), links);
    }
    
    @Test
    public void checkInfinite() throws IOException {
        String filename = "test-file-infinite.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("boo.com()"), links);
    }
    @Test
    public void checkMissingParen() throws IOException {
        String filename = "test-file-missing-paren.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("itsalink.com"), links);
    }
    @Test
    public void testSpaceBetweenLink() throws IOException {
        String filename = "space-between-link.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("a-link.html"), links);
    }
    @Test
    public void testLinksWithExtra() throws IOException {
        String filename = "link-with-para.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("https://something.com","some-page.html"), links);
    }
    @Test
    public void testSpaceInLink() throws IOException {
        String filename = "space-in-url.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("space-in-url.com","linktest.com", "linktest2.com"), links);
    }
    
    @Test
    public void testMDSnippet1() throws IOException {
        String filename = "mdsnippet1.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("`google.com","google.com", "ucsd.edu"), links);
    }
    @Test
    public void testMDSnippet2() throws IOException {
        String filename = "mdsnippet2.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("a.com","a.com(())", "example.com"), links);
    }
    @Test
    public void testMDSnippet3() throws IOException {
        String filename = "mdsnippet3.md";
        ArrayList<String> links = MarkdownParse.getLinks(Files.readString(Path.of(filename)));
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"), links);
    }
    
}