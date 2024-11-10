/**
 * @PRINT 
 * @Method: Pre/In/Post-order + stack 
 * 
 * @Note: sử dụng đệ quy
 * 
 */


 class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

public class BinaryTree2 {
    TreeNode root;

    public BinaryTree2() {
        root = null;
    }

    // pre-order
    void printPreOrder(TreeNode node) {
        if (node == null)
            return;

        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    // Duyệt In-order (LNR)
    void printInOrder(TreeNode node) {
        if (node == null)
            return;

        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    // Duyệt Post-order (LRN)
    void printPostOrder(TreeNode node) {
        if (node == null)
            return;

        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.data + " ");
    }

    

   
}
