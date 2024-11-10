/**
 * @Binary_Tree
 *  Xây dựng một cây nhị phân có chiều cao lớn nhất từ cây nhị phân 
 */

import java.util.ArrayList;
import java.util.List;

// Định nghĩa lớp TreeNode
class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

// Định nghĩa lớp BinaryTreeBT_maxHeight
public class BinaryTreeBT_maxHeight {
    TreeNode root;

    public BinaryTreeBT_maxHeight() {
        root = null;
    }

    // Phương thức duyệt In-order và thu thập giá trị
    public void inOrderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        inOrderTraversal(node.left, list);
        list.add(node.data);
        inOrderTraversal(node.right, list);
    }

    // Phương thức thêm nút vào cây lệch sang phải
    public TreeNode insertRightSkewed(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        node.right = insertRightSkewed(node.right, data);
        return node;
    }

    // Phương thức thêm nút vào cây lệch sang trái
    public TreeNode insertLeftSkewed(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        node.left = insertLeftSkewed(node.left, data);
        return node;
    }

    // Phương thức in cây theo thứ tự Pre-order
    void printPreOrder(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        // Giả sử bạn có một cây nhị phân như sau:
        /*
              4
             / \
            2   6
           / \ / \
          1  3 5 7
        */
        BinaryTreeBT_maxHeight originalTree = new BinaryTreeBT_maxHeight();
        originalTree.root = new TreeNode(4);
        originalTree.root.left = new TreeNode(2);
        originalTree.root.right = new TreeNode(6);
        originalTree.root.left.left = new TreeNode(1);
        originalTree.root.left.right = new TreeNode(3);
        originalTree.root.right.left = new TreeNode(5);
        originalTree.root.right.right = new TreeNode(7);

        // Tạo danh sách các giá trị bằng cách duyệt In-order
        List<Integer> values = new ArrayList<>();
        originalTree.inOrderTraversal(originalTree.root, values);

        // Xây dựng cây mới có chiều cao lớn nhất (Right Skewed)
        BinaryTreeBT_maxHeight maxHeightRightSkewedTree = new BinaryTreeBT_maxHeight();
        for (int value : values) {
            maxHeightRightSkewedTree.root = maxHeightRightSkewedTree.insertRightSkewed(maxHeightRightSkewedTree.root, value);
        }
        System.out.println("Cây mới có chiều cao lớn nhất (Right Skewed, Pre-order):");
        maxHeightRightSkewedTree.printPreOrder(maxHeightRightSkewedTree.root);
        // Kết quả: 1 2 3 4 5 6 7 

        // Xây dựng cây mới có chiều cao lớn nhất (Left Skewed)
        BinaryTreeBT_maxHeight maxHeightLeftSkewedTree = new BinaryTreeBT_maxHeight();
        for (int value : values) {
            maxHeightLeftSkewedTree.root = maxHeightLeftSkewedTree.insertLeftSkewed(maxHeightLeftSkewedTree.root, value);
        }
        System.out.println("\nCây mới có chiều cao lớn nhất (Left Skewed, Pre-order):");
        maxHeightLeftSkewedTree.printPreOrder(maxHeightLeftSkewedTree.root);
        // Kết quả: 1 2 3 4 5 6 7 
    }
}
