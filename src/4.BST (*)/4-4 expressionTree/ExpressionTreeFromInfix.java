/**
 * @ExpressionTree
 * Tạo Cây Biểu Thức Từ Phép Duyệt Trung Tố (Infix)
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

// Định nghĩa lớp ExpressionTreeFromInfix
public class ExpressionTreeFromInfix {

    // Phương thức kiểm tra xem một ký tự có phải toán tử không
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    // Phương thức xác định độ ưu tiên của toán tử
    private int precedence(String op) {
        switch(op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    // Phương thức chuyển đổi biểu thức trung tố thành hậu tố
    public String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();
        
        // Tách biểu thức thành các phần tử
        String[] tokens = infix.split("\\s+");
        
        for (String token : tokens) {
            if (token.matches("[A-Za-z0-9]+")) {
                // Nếu là toán hạng, thêm vào hậu tố
                postfix.append(token).append(" ");
            }
            else if (token.equals("(")) {
                stack.push(token);
            }
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                }
            }
            else if (isOperator(token)) {
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }
        
        // Pop tất cả các toán tử còn lại trong ngăn xếp
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        
        return postfix.toString().trim();
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
        ExpressionTreeFromInfix et = new ExpressionTreeFromInfix();
        
        // Ví dụ: Biểu thức trung tố ( A + B ) * ( C - D )
        String infix = "( A + B ) * ( C - D )";
        
        // Chuyển đổi trung tố thành hậu tố
        String postfix = et.infixToPostfix(infix);
        System.out.println("Biểu thức Hậu tố: " + postfix);
        // Kết quả: A B + C D - *

        // Xây dựng cây biểu thức từ hậu tố
        TreeNode root = et.buildExpressionTree(postfix);
        
        // In biểu thức theo Pre-order để xác minh
        System.out.println("Biểu thức Pre-order:");
        et.preOrderTraversal(root);
        // Kết quả: * + A B - C D 
    }
}
