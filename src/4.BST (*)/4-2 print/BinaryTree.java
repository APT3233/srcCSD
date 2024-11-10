/**
 * @PRINT 
 * @Method: Pre/In/Post-order + stack 
 * 
 * @Note: ko sử dụng đệ quy
 * 
 */

 
import java.util.Stack;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class BinaryTree {
    Node root;

    // Phương thức In-order Traversal (Iterative)
    public void inOrderTraversalIterative(Node node) {
        Stack<Node> stack = new Stack<>();
        Node current = node;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");

            current = current.right;
        }
    }

    // Phương thức Pre-order Traversal (Iterative)
    public void preOrderTraversalIterative(Node node) {
        if (node == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
        }
    }

    // Phương thức Post-order Traversal (Iterative)
    public void postOrderTraversalIterative(Node node) {
        if (node == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node lastVisited = null;
        Node current = node;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    System.out.print(peekNode.data + " ");
                    lastVisited = stack.pop();
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("In-order Traversal (Iterative):");
        tree.inOrderTraversalIterative(tree.root);
        System.out.println();

        System.out.println("Pre-order Traversal (Iterative):");
        tree.preOrderTraversalIterative(tree.root);
        System.out.println();

        System.out.println("Post-order Traversal (Iterative):");
        tree.postOrderTraversalIterative(tree.root);
        System.out.println();
    }
}
