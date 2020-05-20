package org.ajeet.learnings.system.design;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public final class HashRing {
    private final int ringSize = 10;
    private final SortedMap<Integer, VNode> ring = new TreeMap<>();
    private final int replicationFactor;

    public HashRing(int replicationFactor, List<Node> nodes) {
        this.replicationFactor = replicationFactor;

        nodes.forEach(node -> addNode(node));
    }

    public void addNode(Node node) {
        for(int i=0; i<replicationFactor; i++){
            int hash = index(node.ipAddress() + i);
            this.ring.put(hash, new VNode(node));
        }
    }

    public void removeNode(Node node) {
        for(int i=0; i<replicationFactor; i++){
            int hash = index(node.ipAddress() + i);
            ring.remove(hash);
        }
    }

    public Node findNode(String request){
        int index = index(request);
        VNode vNode = ring.get(index);

        if(vNode == null) {
            SortedMap<Integer, VNode> tailMap = ring.tailMap(index);
            if(tailMap.isEmpty())
                index = ring.firstKey();
            else
                index = tailMap.firstKey();

            vNode = ring.get(index);
        }
        return vNode.node();
    }

    private long hash(String text){
        //TODO
        return 0;
    }

    private int index(String s) {
        return (int) (hash(s) % ringSize);
    }
}
