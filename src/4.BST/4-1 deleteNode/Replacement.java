/**
 * @DeleteNode 
 * @Method: Replacement (Sao chép)
 * 
 * @Usage
 * 1. Tìm node cần xóa
 *      Nếu key nhỏ hơn root.key, đi sang trái.
 *      Nếu key lớn hơn root.key, đi sang phải.
 *      Nếu key bằng root.key, ta tìm được nút cần xóa.
 * 2. Xóa node
 *      Trường hợp 1: Nút không có con (lá) — xóa trực tiếp nút đó.
 *      Trường hợp 2: Nút có một con — thay thế nút bằng con của nó.
 *      Trường hợp 3: Nút có hai con — tìm successor nhỏ nhất trong cây con phải, 
 *          thay thế giá trị của nút cần xóa bằng successor, sau đó xóa successor từ cây con phải.
 */


class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
        left = right = null;
    }
}

public class Replacement {

    Node root;

    // Hàm tìm successor nhỏ nhất trong cây con phải
    Node findLeftMost(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Hàm xóa nút
    Node deleteNode(Node root, int key) {
        if (root == null) return root;

        // Tìm nút cần xóa
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        
        else {
            // Nút có một hoặc không có con
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Nút có hai con: tìm successor    6
            Node tmp = findLeftMost(root.right);

            // Thay thế giá trị 8->6
            root.key = tmp.key;

            // Xóa successor
            root.right = deleteNode(root.right, tmp.key);
        }
        return root;
    }
}
 