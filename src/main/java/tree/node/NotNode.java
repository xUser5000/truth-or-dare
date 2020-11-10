package tree.node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NotNode implements Node {

    private Node child;

    @Override
    public boolean evaluate(Map<Character, Boolean> values) {
        return !child.evaluate(values);
    }

    @Override
    public void addChild(Node childNode) {
        child = childNode;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.singletonList(child);
    }

    @Override
    public boolean isSaturated() {
        return child != null;
    }

    @Override
    public String toString() {
        return "~";
    }
}
