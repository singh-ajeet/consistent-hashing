package org.ajeet.learnings.system.design;

public final class VNode {
    private final Node physicalNode;

    public VNode(Node node) {
        this.physicalNode = node;
    }

    public Node node() {
        return physicalNode;
    }
}
