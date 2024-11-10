/**
 * @Infix_To_Prefix
 * 
 */


import java.util.Stack;

public class InfixToPrefix {

    // Kiểm tra xem ký tự có phải là toán tử không
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Xác định độ ưu tiên của toán tử
    static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1; // Thấp nhất
            case '*':
            case '/':
                return 2;
            case '^':
                return 3; // Cao nhất
        }
        return -1;
    }

    // Hàm chính để chuyển đổi infix sang prefix
    static String infixToPrefix(String infix) {
        // Đảo ngược chuỗi infix
        StringBuilder reversedInfix = new StringBuilder(infix).reverse();
        // Thay đổi dấu ngoặc
        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);
            if (c == '(') {
                reversedInfix.setCharAt(i, ')');
            } else if (c == ')') {
                reversedInfix.setCharAt(i, '(');
            }
        }

        // Chuyển đổi infix đảo ngược sang postfix
        String postfix = infixToPostfix(reversedInfix.toString());

        // Đảo ngược chuỗi postfix để nhận được prefix
        String prefix = new StringBuilder(postfix).reverse().toString();

        return prefix;
    }

    // Chuyển đổi infix sang postfix (sử dụng trong quá trình chuyển đổi prefix)
    static String infixToPostfix(String infix) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Nếu là toán hạng, thêm vào kết quả
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // Nếu gặp dấu '(', đẩy vào stack
            else if (c == '(') {
                stack.push(c);
            }
            // Nếu gặp dấu ')', pop từ stack đến khi gặp '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Biểu thức không hợp lệ";
                } else {
                    stack.pop();
                }
            }
            // Nếu là toán tử
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop các toán tử còn lại từ stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Biểu thức không hợp lệ";
            }
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String infixExpr = "(A-B/C)*(A/K-L)";
        System.out.println("Biểu thức Infix: " + infixExpr);
        String prefixExpr = infixToPrefix(infixExpr);
        System.out.println("Biểu thức Prefix: " + prefixExpr);
    }
}
