/**
 * @Stack_Using_Linked-list
 * 
 */



public class StackLinkedList {

    // Lớp Node đại diện cho mỗi phần tử trong ngăn xếp
    private class Node {
        int data;
        Node next;

        // Constructor
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // Tham chiếu tới phần tử trên cùng của ngăn xếp

    // Constructor
    public StackLinkedList() {
        this.top = null;
    }

    // Thêm một phần tử vào ngăn xếp
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    // Loại bỏ và trả về phần tử trên cùng của ngăn xếp
    public int pop() {
        if (isEmpty()) {
            System.out.println("Ngăn xếp rỗng. Không thể xóa phần tử.");
            return -1; 
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    // Xem phần tử trên cùng của ngăn xếp mà không loại bỏ nó
    public int peek() {
        if (isEmpty()) {
            System.out.println("Ngăn xếp rỗng. Không có phần tử trên cùng.");
            return -1;
        }
        return top.data;
    }

    // Kiểm tra ngăn xếp có rỗng không
    public boolean isEmpty() {
        return top == null;
    }

    // Hiển thị các phần tử trong ngăn xếp
    public void print() {
        if (isEmpty()) {
            System.out.println("Ngăn xếp rỗng.");
            return;
        }
        System.out.print("Nội dung ngăn xếp: ");
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Tìm kiếm một phần tử trong ngăn xếp
    public int search(int value) {
        Node current = top;
        int position = 0;
        while (current != null) {
            if (current.data == value) {
                return position; // Vị trí tính từ đỉnh ngăn xếp
            }
            current = current.next;
            position++;
        }
        return -1; 
    }

    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();


        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Hiển thị ngăn xếp
        stack.print(); // Output: Nội dung ngăn xếp: 30 20 10

        // Xem phần tử trên cùng
        System.out.println("Phần tử trên cùng: " + stack.peek());

        // Tìm kiếm phần tử
        System.out.println("Vị trí của 20: " + stack.search(20));

        // Loại bỏ phần tử
        stack.pop();
        stack.print(); // Output: Nội dung ngăn xếp: 20 10

        // Kiểm tra ngăn xếp rỗng
        System.out.println("Ngăn xếp rỗng: " + stack.isEmpty());

        // Loại bỏ các phần tử còn lại
        stack.pop();
        stack.pop();
        System.out.println("Ngăn xếp rỗng: " + stack.isEmpty());

        // Thử pop khi ngăn xếp rỗng
        stack.pop(); // Sẽ thông báo ngăn xếp rỗng
    }
}
