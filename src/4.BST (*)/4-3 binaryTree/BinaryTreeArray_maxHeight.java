/**
 * @Binary_Tree
 * Xây dựng một cây nhị phân có chiều cao lớn nhất từ mảng đầu vào
 */


import java.util.Arrays;

// Định nghĩa lớp TreeNode
class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

// Định nghĩa lớp BinaryTreeArray_maxHeight
public class BinaryTreeArray_maxHeight {
    TreeNode root;

    public BinaryTreeArray_maxHeight() {
        root = null;
    }

    // Phương thức thêm nút vào cây lệch sang phải (Right Skewed)
    public void buildMaxHeightRightSkewedTree(int[] arr) {
        for (int value : arr) {
            root = insertRightSkewed(root, value);
        }
    }

    // Phương thức thêm nút vào cây lệch sang phải
    private TreeNode insertRightSkewed(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        node.right = insertRightSkewed(node.right, data);
        return node;
    }

    // Phương thức thêm nút vào cây lệch sang trái (Left Skewed)
    public void buildMaxHeightLeftSkewedTree(int[] arr) {
        for (int value : arr) {
            root = insertLeftSkewed(root, value);
        }
    }

    // Phương thức thêm nút vào cây lệch sang trái
    private TreeNode insertLeftSkewed(TreeNode node, int data) {
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
        BinaryTreeArray_maxHeight treeRight = new BinaryTreeArray_maxHeight();
        BinaryTreeArray_maxHeight treeLeft = new BinaryTreeArray_maxHeight();
        int[] input = {1, 2, 3, 4, 5, 6, 7};

        // Xây dựng cây lệch sang phải
        treeRight.buildMaxHeightRightSkewedTree(input);
        System.out.println("Cây có chiều cao lớn nhất (Right Skewed, Pre-order):");
        treeRight.printPreOrder(treeRight.root);
        // Kết quả: 1 2 3 4 5 6 7 

        // Xây dựng cây lệch sang trái
        treeLeft.buildMaxHeightLeftSkewedTree(input);
        System.out.println("\nCây có chiều cao lớn nhất (Left Skewed, Pre-order):");
        treeLeft.printPreOrder(treeLeft.root);
        // Kết quả: 1 2 3 4 5 6 7 
    }
}
