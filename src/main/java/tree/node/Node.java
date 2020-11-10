package tree.node;

import java.util.List;
import java.util.Map;

public interface Node {
    /**
     * Calculates the value of the node in terms of its children
     * @param values contains the value of every proposition to be used in evaluation
     * @return boolean
     */
    boolean evaluate (Map<Character, Boolean> values);

    /**
     * Inserts a new children to the node
     */
    void addChild (Node childNode);

    /**
     * Get the node's children
     */
    List<Node> getChildren ();

    /**
     * returns true if the node has got the maximum number of children it can have
     * false otherwise
     */
    boolean isSaturated ();
}
