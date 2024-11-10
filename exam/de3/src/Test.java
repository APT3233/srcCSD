
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
class Student {

    String id;
    String studentName;
    String studentClass;
    int sememter;

    public Student(String id, String stName, String stClass, int semeter) {
        this.id = id;
        this.studentName = stName;
        this.studentClass = stClass;
        this.sememter = semeter;
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public int getSememter() {
        return sememter;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setSememter(int sememter) {
        this.sememter = sememter;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentName=" + studentName + ", studentClass=" + studentClass + ", sememter=" + sememter + '}';
    }

}

class Node {

    Student student;
    Node left, right;

    public Node() {

    }

    public Node(Student st) {
        this.student = st;
        this.left = null;
        this.right = null;
    }
}

class BST {

    Node root;

    public BST() {

    }

    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    public Node insertRecursive(Node root, Student student) {
        if (root == null) {
            root = new Node(student);
            return root;
        }
        if (student.getId().compareTo(root.student.getId()) < 0) {
            root.left = insertRecursive(root.left, student);
        } else if (student.getId().compareTo(root.student.getId()) > 0) {
            root.right = insertRecursive(root.right, student);
        }
        return root;
    }

    public void TreeTraversal(Node root) {
        inorderTraversalRecursive(root);
    }

    public void inorderTraversalRecursive(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursive(root.left);
        System.out.println(root.student.toString());
        inorderTraversalRecursive(root.right);
    }

    public boolean searchByName(Node node, String name) {
        name = name.toLowerCase();
        boolean found = searchRecursive(node, name);
        return found;
    }

    private boolean searchRecursive(Node node, String name) {
        if (node == null) {
            return false;
        }

        boolean foundInCurrentNode = node.student.getStudentName().toLowerCase().contains(name);
        if (foundInCurrentNode) {
            System.out.println(node.student.toString());
        }

        // Continue searching in both left and right subtrees
        boolean foundInLeft = searchRecursive(node.left, name);
        boolean foundInRight = searchRecursive(node.right, name);

        // Return true if found in current node or in any subtree
        return foundInCurrentNode || foundInLeft || foundInRight;
    }

    public Node deleteStudent(Node node, String id) {
        if (node == null) {
            return null;
        }

        if (id.compareTo(node.student.id) < 0) {
            node.left = deleteStudent(node.left, id);
        } else if (id.compareTo(node.student.id) > 0) {
            node.right = deleteStudent(node.right, id);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children: get the inorder successor (smallest in the right subtree)
            Node successor = findMin(node.right);
            node.student = successor.student;
            node.right = deleteStudent(node.right, successor.student.id);
        }
        return node;
    }

    public Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node findMaxseme(Node node) {
        if (node == null) {
            return null;
        }

        Node maxNode = node;

        Node leftMax = findMaxseme(node.left);
        if (leftMax != null && leftMax.student.sememter > maxNode.student.sememter) {
            maxNode = leftMax;
        }

        Node rightMax = findMaxseme(node.right);
        if (rightMax != null && rightMax.student.sememter > maxNode.student.sememter) {
            maxNode = rightMax;
        }

        return maxNode;
    }

    public boolean isBalanced() {
        return checkBalance(root) != -1;
    }

    private int checkBalance(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkBalance(node.left);
        int rightHeight = checkBalance(node.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Tree is unbalanced
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST b = new BST();
        b.insert(new Student("DE190565", "Nguyen Huynh Dang Khoi", "SE19C02", 2));
        b.insert(new Student("DE170916", "Tan Huynh Kim Hoang", "SE180D01", 3));
        b.insert(new Student("DE180937", "Vo Phan Huy", "SE180D01", 4));
        b.insert(new Student("DE180986", "Nguyen Dinh Quoc", "SE19C02", 1));
        b.insert(new Student("DE191047", "Tran Le Dang Khoa", "SE19C02", 1));
        b.insert(new Student("DE190229", "Ngo Dinh Bao Chuong", "SE18D01", 3));
        b.insert(new Student("DE190229", "Tran Le Dang Khoa", "SE18D01", 4));
        b.insert(new Student("DE190970", "Tran Le Dang Khoa", "SE18C02", 3));
        b.insert(new Student("DE190347", "Tran Le Dang Khoa", "SE18C02", 2));

        System.out.println("enter name to search: ");
        String name = sc.nextLine();
        boolean found = b.searchByName(b.root, name);
        if (!found) {
            System.out.println("Not found");
        }
        System.out.println("");

        b.TreeTraversal(b.root);

        Node res = b.findMaxseme(b.root);
        System.out.println("student with highest semeter: " + res.student.toString());

        System.out.println("Is the tree balanced? " + b.isBalanced());

        System.out.println("enter student id to remove: ");
        String id = sc.nextLine();
        b.root = b.deleteStudent(b.root, id);
        System.out.println("delete success! tree after delete: ");
        b.TreeTraversal(b.root);
    }
}
