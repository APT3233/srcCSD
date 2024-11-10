/**
 * @AVL
 * Insert/delete node trong cây AVL và thực hiện các phép quay khi cần thiết.
 */


 class Node {
    int key;
    Node left, right;
    int height;

    Node(int d) {
        key = d;
        height = 1; // Chiều cao của nút mới là 1
    }
}

 class AVLTree {
    private Node root;

    // Hàm để lấy chiều cao của nút
    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Hàm để lấy hệ số cân bằng của nút
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Hàm quay phải
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Thực hiện quay
        x.right = y;
        y.left = T2;

        // Cập nhật chiều cao
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Trả về nút mới sau quay
        return x;
    }

    // Hàm quay trái
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Thực hiện quay
        y.left = x;
        x.right = T2;

        // Cập nhật chiều cao
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Trả về nút mới sau quay
        return y;
    }

    // Hàm chèn một nút vào cây AVL
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        // 1. Thực hiện chèn như trong BST
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insertRec(node.left, key);
        else if (key > node.key)
            node.right = insertRec(node.right, key);
        else // Không cho phép các giá trị trùng lặp
            return node;

        // 2. Cập nhật chiều cao của nút cha
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Kiểm tra hệ số cân bằng
        int balance = getBalance(node);

        // 4. Nếu nút mất cân bằng, thực hiện quay phù hợp

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Trả về nút không thay đổi
        return node;
    }

    // Hàm tìm nút có giá trị nhỏ nhất trong cây
    private Node minValueNode(Node node) {
        Node current = node;

        // Tìm nút có giá trị nhỏ nhất
        while (current.left != null)
            current = current.left;

        return current;
    }

    // Hàm xóa một nút khỏi cây AVL
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        // 1. Thực hiện xóa như trong BST
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            // Nút có một hoặc không có con
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                // Không có con
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // Có một con
                    root = temp; // Thay thế root bằng con của nó
            } else {
                // Nút có hai con: lấy giá trị nhỏ nhất trong cây con phải
                Node temp = minValueNode(root.right);
                // Sao chép giá trị nhỏ nhất đó vào nút hiện tại
                root.key = temp.key;
                // Xóa nút nhỏ nhất trong cây con phải
                root.right = deleteRec(root.right, temp.key);
            }
        }

        // Nếu cây chỉ có một nút
        if (root == null)
            return root;

        // 2. Cập nhật chiều cao của nút cha
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // 3. Kiểm tra hệ số cân bằng
        int balance = getBalance(root);

        // 4. Nếu nút mất cân bằng, thực hiện quay phù hợp

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Hàm duyệt cây theo thứ tự In-order để in các giá trị
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.key + " ");
            inOrderRec(node.right);
        }
    }

    // Hàm duyệt cây theo thứ tự Pre-order
    public void preOrder() {
        preOrderRec(root);
    }

    private void preOrderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    // Hàm duyệt cây theo thứ tự Post-order
    public void postOrder() {
        postOrderRec(root);
    }

    private void postOrderRec(Node node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.key + " ");
        }
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Chèn các giá trị vào cây AVL */
        int[] insertValues = {10, 20, 30, 40, 50, 25};
        for (int key : insertValues) {
            tree.insert(key);
            System.out.println("Đã chèn " + key + ":");
            System.out.print("Duyệt In-order: ");
            tree.inOrder();
            System.out.println("\n");
        }

        /* In cây theo thứ tự In-order, Pre-order và Post-order */
        System.out.println("Duyệt In-order của cây AVL:");
        tree.inOrder();
        System.out.println("\n");

        System.out.println("Duyệt Pre-order của cây AVL:");
        tree.preOrder();
        System.out.println("\n");

        System.out.println("Duyệt Post-order của cây AVL:");
        tree.postOrder();
        System.out.println("\n");

        /* Xóa các nút khỏi cây AVL */
        int[] deleteValues = {40, 30, 50};
        for (int key : deleteValues) {
            tree.delete(key);
            System.out.println("Đã xóa " + key + ":");
            System.out.print("Duyệt In-order: ");
            tree.inOrder();
            System.out.println("\n");
        }

        /* In cây sau khi xóa */
        System.out.println("Duyệt In-order của cây AVL sau khi xóa:");
        tree.inOrder();
        System.out.println("\n");

        System.out.println("Duyệt Pre-order của cây AVL sau khi xóa:");
        tree.preOrder();
        System.out.println("\n");

        System.out.println("Duyệt Post-order của cây AVL sau khi xóa:");
        tree.postOrder();
        System.out.println("\n");
    }
}
