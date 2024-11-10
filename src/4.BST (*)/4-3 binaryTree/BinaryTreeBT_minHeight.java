/**
 * @Binary_Tree
 * Xây dựng một cây nhị phân có chiều cao nhỏ nhất từ một mảng đầu vào
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

// Định nghĩa lớp BinaryTree
public class BinaryTreeBT_minHeight {
    TreeNode root;

    public BinaryTreeBT_minHeight() {
        root = null;
    }

    // Phương thức xây dựng cây có chiều cao nhỏ nhất từ mảng đã sắp xếp
    public TreeNode buildMinHeightTree(int[] arr, int start, int end) {
        if (start > end)
            return null;

        // Chọn phần tử giữa làm nút gốc
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(arr[mid]);

        // Đệ quy xây dựng cây con bên trái và bên phải
        node.left = buildMinHeightTree(arr, start, mid - 1);
        node.right = buildMinHeightTree(arr, mid + 1, end);

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
        BinaryTreeBT_minHeight tree = new BinaryTreeBT_minHeight();
        int[] input = {1, 2, 3, 4, 5, 6, 7};

        // Sắp xếp mảng nếu cần thiết
        Arrays.sort(input);

        // Xây dựng cây có chiều cao nhỏ nhất
        tree.root = tree.buildMinHeightTree(input, 0, input.length - 1);

        System.out.println("Cây có chiều cao nhỏ nhất (Pre-order):");
        tree.printPreOrder(tree.root);
        // Kết quả: 4 2 1 3 6 5 7 
    }
}
