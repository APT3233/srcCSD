/**
 * @Expression_Tree
 * Xây dựng cây biểu thức từ biểu thức hậu tố bằng stack
 */



 import java.util.Stack;

// Định nghĩa lớp TreeNode cho cây biểu thức
class TreeNode {
    String value;
    TreeNode left, right;

    TreeNode(String val) {
        value = val;
        left = right = null;
    }
}

// Định nghĩa lớp ExpressionTree
public class ExpressionTree {
    
    // Phương thức kiểm tra xem một ký tự có phải toán tử không
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    // Phương thức xây dựng cây biểu thức từ biểu thức hậu tố
    public TreeNode buildExpressionTree(String postfix) {
        Stack<TreeNode> stack = new Stack<>();
        
        // Tách biểu thức thành các phần tử
        String[] tokens = postfix.split("\\s+");
        
        for (String token : tokens) {
            if (!isOperator(token)) {
                // Nếu là toán hạng, tạo nút và đẩy vào ngăn xếp
                stack.push(new TreeNode(token));
            } else {
                // Nếu là toán tử, pop hai nút, tạo nút mới và đẩy lại
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                TreeNode operatorNode = new TreeNode(token);
                operatorNode.left = left;
                operatorNode.right = right;
                stack.push(operatorNode);
            }
        }
        
        // Nút duy nhất còn lại là gốc của cây
        return stack.pop();
    }

    // Phương thức duyệt cây theo Pre-order để in biểu thức
    public void preOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.value + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        ExpressionTree et = new ExpressionTree();
        
        // Ví dụ: Biểu thức trung tố (A + B) * (C - D)
        // Hậu tố tương ứng: A B + C D - *
        String postfix = "A B + C D - *";
        
        // Xây dựng cây biểu thức
        TreeNode root = et.buildExpressionTree(postfix);
        
        // In biểu thức theo Pre-order để xác minh
        System.out.println("Biểu thức Pre-order:");
        et.preOrderTraversal(root);
        // Kết quả: * + A B - C D 
    }
}
