package tree.node;

import java.util.List;
import java.util.Map;

public class PropositionNode implements Node {

    private final char proposition; // The symbol of the proposition

    public PropositionNode(char proposition) {
        this.proposition = proposition;
    }

    @Override
    public boolean evaluate(Map<Character, Boolean> values) {
        return values.get(proposition);
    }

    @Override
    public void addChild(Node childNode) {}

    @Override
    public List<Node> getChildren() {
        return null;
    }

    @Override
    public boolean isSaturated() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(proposition);
    }
}
