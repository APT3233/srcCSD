package exam.de2;

class Teacher{
    int code;
    double coeff;
    Teacher(int code, double coeff){
        this.code = code;
        this.coeff = coeff;
    }
}
class Node{
    Teacher data;
    Node left, right;
    int bal; // Sự chênh lệch chiều cao giữa cây con trái và cây con phải

    Node(Teacher data){
        this.data = data;
        left = right = null;
        bal = 0;
    }
}

public class BST{
    Node root = null;
    
    // Q1: Tạo 1 cây từ mạng A
    public void createTreeFromArray(Teacher[] teachers){
        for (Teacher teacher : teachers) 
            root = _insert(root, teacher);
    }
    private Node _insert(Node root, Teacher teacher){
        if(root==null)
            return new Node(teacher);
        if(teacher.code < root.data.code)
            root.left = _insert(root.left, teacher);
        else if(teacher.code > root.data.code)
            root.right = _insert(root.right, teacher);

        return root;
    }
    

    // Q2 Tăng hệ số của mỗi giáo viên thêm M/2.0
    public void increaseCoeff(){
        double maxCoeff = findMaxCoeff(root);
        _increaseCoeff(root, maxCoeff / 2.0);
    }
    private double findMaxCoeff(Node root){
        if(root==null)  return Double.MIN_VALUE;

        double max = root.data.coeff;
        double leftMax = findMaxCoeff(root.left);
        double rightMax = findMaxCoeff(root.right);
        
        return Math.max(max, Math.max(leftMax, rightMax));
    }
    private void _increaseCoeff(Node root, double increaseCoeff){
        if(root==null)  return;
        root.data.coeff += increaseCoeff;
        _increaseCoeff(root.left, increaseCoeff);
        _increaseCoeff(root.right, increaseCoeff);
    }

    // Q3 In tất cả các nút dưới dạng <data, level>
    public void printAllNodesWithLevel() {
        printAllNodesWithLevel(root, 1);
    }

    private void printAllNodesWithLevel(Node node, int level) {
        if (node == null) return;

        System.out.println("<" + node.data.code + ", " + node.data.coeff + ", Level: " + level + ">");
        printAllNodesWithLevel(node.left, level + 1);
        printAllNodesWithLevel(node.right, level + 1);
    }

    // Q4 Xác định giá trị `bal` cho tất cả các nút
    public void setBalanceFactor() {
        setBalanceFactor(root);
    }

    private int setBalanceFactor(Node node) {
        if (node == null) return -1;

        int leftHeight = setBalanceFactor(node.left);
        int rightHeight = setBalanceFactor(node.right);
        node.bal = leftHeight - rightHeight;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Q5 Xuất ra thông tin tất cả giáo viên theo thứ tự duyệt trước
    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node node) {
        if (node == null) return;

        System.out.println("Code: " + node.data.code + ", Coeff: " + node.data.coeff);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

     // II. Sắp xếp mảng giáo viên theo thứ tự giảm dần của `code` (Bubble Sort)
     public static void treeSort(Teacher[] teachers) {
        for (int i = 0; i < teachers.length - 1; i++) {
            for (int j = 0; j < teachers.length - i - 1; j++) {
                if (teachers[j].code < teachers[j + 1].code) {
                    Teacher temp = teachers[j];
                    teachers[j] = teachers[j + 1];
                    teachers[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Dữ liệu giáo viên
        Teacher[] teachers = {
            new Teacher(5, 5), new Teacher(3, 3), new Teacher(2, 2),
            new Teacher(4, 4), new Teacher(7, 7), new Teacher(6, 6),
            new Teacher(8, 8), new Teacher(1, 1), new Teacher(9, 9)
        };

        // Tạo cây và thêm các giáo viên vào cây
        BST bst = new BST();
        bst.createTreeFromArray(teachers);

        // I.2 Tăng hệ số của mỗi giáo viên thêm M/2.0
        bst.increaseCoeff();

        // I.3 In tất cả các nút dưới dạng <data, level>
        System.out.println("In tất cả các nút dưới dạng <data, level>:");
        bst.printAllNodesWithLevel();

        // I.4 Xác định giá trị `bal` cho tất cả các nút
        bst.setBalanceFactor();
        System.out.println("\nBalance factor (bal) cho mỗi nút đã được thiết lập.");

        // I.5 Xuất ra thông tin tất cả giáo viên theo thứ tự duyệt trước
        System.out.println("\nIn thông tin tất cả giáo viên theo thứ tự duyệt trước (pre-order):");
        bst.printPreOrder();

        // II. Sắp xếp mảng giáo viên theo thứ tự giảm dần của `code` (tree_sort)
        System.out.println("\nSắp xếp mảng giáo viên theo thứ tự giảm dần của code:");
        BST.treeSort(teachers);
        for (Teacher teacher : teachers) {
            System.out.println("Code: " + teacher.code + ", Coeff: " + teacher.coeff);
        }
    }
}