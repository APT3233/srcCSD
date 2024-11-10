/**
 * @MDeleteNode
 * @Moethod: Merging (Hợp nhất)
 * 
 * 
 */

 class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
        left = right = null;
    }
}

public class Merging {

    Node root;

    // Hàm tìm giá trị lớn nhất trong cây
    Node findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    // Hàm hợp nhất hai cây con
    Node mergeTrees(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        // Tìm giá trị lớn nhất trong cây con trái
        Node max = findMax(left);
        // Gắn cây con phải vào cây con phải của max
        max.right = right;
        return left;
    }

    // Hàm xóa nút
    Node deleteNode(Node root, int key) {
        if (root == null) return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            // Nút cần xóa đã được tìm thấy
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                // Hợp nhất cây con trái và cây con phải
                return mergeTrees(root.left, root.right);
            }
        }

        return root;
    }
}

