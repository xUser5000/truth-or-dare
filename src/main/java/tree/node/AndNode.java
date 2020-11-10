package tree.node;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AndNode implements Node {

    private Node left, right;

    @Override
    public boolean evaluate(Map<Character, Boolean> values) {
        return left.evaluate(values) && right.evaluate(values);
    }

    @Override
    public void addChild(Node childNode) {
        if (left == null) left = childNode;
        else right = childNode;
    }

    @Override
    public List<Node> getChildren() {
        return Arrays.asList(left, right);
    }

    @Override
    public boolean isSaturated() {
        return left != null && right != null;
    }

    @Override
    public String toString() {
        return "^";
    }
}
