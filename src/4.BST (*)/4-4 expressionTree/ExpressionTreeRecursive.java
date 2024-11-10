/**
 * @Expression_Tree
 * Sử Dụng Đệ Quy Để Xây Dựng Cây Biểu Thức Từ Hậu Tố (Post)
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

// Định nghĩa lớp ExpressionTreeRecursive
public class ExpressionTreeRecursive {
    
    private int index; // Chỉ số để duyệt biểu thức

    // Phương thức kiểm tra xem một ký tự có phải toán tử không
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    // Phương thức xây dựng cây biểu thức từ biểu thức hậu tố bằng đệ quy
    public TreeNode buildExpressionTreeRecursive(String[] tokens) {
        if (index < 0)
            return null;
        
        String token = tokens[index--];
        
        if (!isOperator(token)) {
            // Nếu là toán hạng, tạo nút và trả về
            return new TreeNode(token);
        }
        else {
            // Nếu là toán tử, tạo nút và đệ quy cho con phải và trái
            TreeNode node = new TreeNode(token);
            node.right = buildExpressionTreeRecursive(tokens);
            node.left = buildExpressionTreeRecursive(tokens);
            return node;
        }
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
        ExpressionTreeRecursive et = new ExpressionTreeRecursive();
        
        // Ví dụ: Biểu thức hậu tố A B + C D - *
        String postfix = "A B + C D - *";
        String[] tokens = postfix.split("\\s+");
        
        // Khởi tạo chỉ số bắt đầu từ cuối mảng
        et.index = tokens.length - 1;
        
        // Xây dựng cây biểu thức bằng đệ quy
        TreeNode root = et.buildExpressionTreeRecursive(tokens);
        
        // In biểu thức theo Pre-order để xác minh
        System.out.println("Biểu thức Pre-order:");
        et.preOrderTraversal(root);
        // Kết quả: * + A B - C D 
    }
}
