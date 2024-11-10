/**
 * @Binary_Tree
 * Xây dựng một cây nhị phân có chiều cao nhỏ nhất từ cây nhị phân có sẵn
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

// Định nghĩa lớp BinaryTree
public class BinaryTreeArray_minHeight {
    TreeNode root;

    public BinaryTreeArray_minHeight() {
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

    // Phương thức xây dựng cây có chiều cao nhỏ nhất từ mảng đã sắp xếp
    public TreeNode buildMinHeightTree(int[] arr, int start, int end) {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(arr[mid]);
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
        // Giả sử bạn có một cây nhị phân như sau:
        /*
              4
             / \
            2   6
           / \ / \
          1  3 5 7
        */
        BinaryTreeArray_minHeight originalTree = new BinaryTreeArray_minHeight();
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
        int[] sortedArray = values.stream().mapToInt(i -> i).toArray();

        // Xây dựng cây mới có chiều cao nhỏ nhất
        BinaryTreeArray_minHeight minHeightTree = new BinaryTreeArray_minHeight();
        minHeightTree.root = minHeightTree.buildMinHeightTree(sortedArray, 0, sortedArray.length - 1);
        System.out.println("Cây mới có chiều cao nhỏ nhất (Pre-order):");
        minHeightTree.printPreOrder(minHeightTree.root);
        // Kết quả: 4 2 1 3 6 5 7 
    }
}
