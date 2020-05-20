package org.ajeet.learnings.system.design;

public final class Node {
    private final String ipAddress;

    public Node(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String ipAddress() {
        return ipAddress;
    }
}
