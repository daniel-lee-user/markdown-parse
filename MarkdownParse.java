// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            
            if(closeParen == -1) {
                return toReturn;
            }
            
            if(nextOpenBracket > 0) {
                if(markdown.charAt(nextOpenBracket-1) == '!') {
                    currentIndex = closeParen+1;
                    continue;
                }
            }
            if(closeParen > markdown.indexOf("\n",openParen) && markdown.indexOf("\n", openParen) != -1) {
                currentIndex = markdown.indexOf("\n",openParen)+1;
                continue;
            }
            //in case of brackets or parenthesis in the link
            if(closeParen+1 != markdown.length()) {
                // looks for newline while making sure that
                // closing parenthesis index is not right before markdown length
                while(closeParen+1 < markdown.length() && markdown.indexOf("\n",closeParen) != closeParen+1) { 
                    // closeParen+1 is \n for unix systems but closeParen+2 is \n for windows systems.
                    System.out.println("nope");
                    //updates closeParen if new line is not right after closing parenthesis
                    closeParen = markdown.indexOf(")", closeParen+1); 
                }
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }

    /*
    Documenting errors:
        for incorrect.md: Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 18, end -1, length 28
        at java.base/java.lang.String.checkBoundsBeginEnd(String.java:4601)
        at java.base/java.lang.String.substring(String.java:2704)
        at MarkdownParse.getLinks(MarkdownParse.java:38)
        at MarkdownParse.main(MarkdownParse.java:52)

        for image.md: [google.com, fake.png]

        for new-file.md (Infinite loop): Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at java.base/java.util.Arrays.copyOf(Arrays.java:3512)
        at java.base/java.util.Arrays.copyOf(Arrays.java:3481)
        at java.base/java.util.ArrayList.grow(ArrayList.java:237)
        at java.base/java.util.ArrayList.grow(ArrayList.java:244)
        at java.base/java.util.ArrayList.add(ArrayList.java:454)
        at java.base/java.util.ArrayList.add(ArrayList.java:467)
        at MarkdownParse.getLinks(MarkdownParse.java:38)
        at MarkdownParse.main(MarkdownParse.java:52)
    */

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}