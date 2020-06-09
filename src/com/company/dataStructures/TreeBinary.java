package com.company.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class TreeBinary<X extends Comparable<X>> {

    private Node root;
    private int size;

    public TreeBinary() {
        root = null;
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private X item;

        public Node(final X item) {
            this.item = item;
            left = null;
            right = null;
            parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(final Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(final Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(final Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(final X item) {
            this.item = item;
        }
    }

    public int size() {
        return size;
    }

    public void add(final X item) {
        final Node node = new Node(item);

        if (root == null) {
            root = node;
            size++;
        } else {
            insert(root, node);
        }
    }

    private void insert(final Node parent, final Node child) {

        if (child.getItem().compareTo(parent.getItem()) < 0) {
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                size++;
            } else {
                insert(parent.getLeft(), child);
            }
        } else if (child.getItem().compareTo(parent.getItem()) > 0) {
            if (parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                size++;
            } else {
                insert(parent.getRight(), child);
            }
        }

    }

    public boolean contains(final X item) {
        return getNode(item) != null;
    }

    private Node getNode(final X item) {
        Node currentNode = root;

        while (currentNode != null) {
            final int val = item.compareTo(currentNode.item);
            if (val == 0) {
                return currentNode;
            } else if (val < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return null;
    }

    public boolean delete(final X item) {

        final boolean deleted = false;

        if (root == null) {
            return false;
        }

        final Node currentNode = getNode(item);

        if (currentNode != null) {

            //Ha nincsenek gyerekei
            if (currentNode.getRight() == null && currentNode.getLeft() == null) {
                swapNode(currentNode, null);
            }
            //Ha csak egy gyereke van
            else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                swapNode(currentNode, currentNode.getLeft());
            } else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                swapNode(currentNode, currentNode.getRight());
            }
            //Ha 2 gyereke van
            else {
                //megszámolni a Node-ok méretét
                final List<Node> nodesRight = collectNodes(currentNode.getRight());
                final List<Node> nodesLeft = collectNodes(currentNode.getLeft());
                //a nagyobbat becserélni, a kisebb Node elemeit hozzáadni
                if (nodesLeft.size() >= nodesRight.size()) {
                    swapNode(currentNode, currentNode.getLeft());
                    nodesRight.forEach(n -> add(n.getItem()));
                } else {
                    swapNode(currentNode, currentNode.getRight());
                    nodesLeft.forEach(n -> add(n.getItem()));
                }

            }
            size--;

        }

        return deleted;

    }

    private List<Node> collectNodes(final Node nodeToMap) {
        final List<Node> nodes = new ArrayList<>();
        nodes.add(nodeToMap);
        if (nodeToMap.getLeft() != null) {
            nodes.addAll(collectNodes(nodeToMap.getLeft()));
        }
        if (nodeToMap.getRight() != null) {
            nodes.addAll(collectNodes(nodeToMap.getRight()));
        }
        return nodes;
    }

    private void swapNode(final Node currentNode, final Node newNode) {
        if (currentNode == root) {
            root = newNode;
        } else {
            final Node parent = currentNode.getParent();
            if (parent.getLeft() == currentNode) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
        }
    }

}
