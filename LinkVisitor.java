import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import java.util.ArrayList;

class LinkVisitor extends AbstractVisitor {
    ArrayList<String> listOfLinks = new ArrayList<String>();

    @Override
    public void visit(Link link) {
        // This is called for all Link nodes. Override other visit methods for other node types.

        listOfLinks.add(link.getDestination());
        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(link);
    }
}