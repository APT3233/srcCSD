/**
 * Double Circular Linked-list (DCL)
 * 
 */


 // Lớp Node đại diện cho mỗi phần tử trong danh sách
 class Node {
    int data;
    Node next;
    Node prev;

    // Constructor
    public Node(int data) {
        this.data = data;
    }
}
class DoubleCircularLinkedList {
    private Node head = null; // Tham chiếu tới nút đầu tiên

    // Chèn một nút mới vào cuối danh sách
    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            // Thêm newNode trước head và điều chỉnh các tham chiếu
            Node tail = head.prev;

            tail.next = newNode;
            newNode.prev = tail;

            newNode.next = head;
            head.prev = newNode;
        }
    }

    // Xóa một nút với dữ liệu được chỉ định
    public void delete(int data) {
        if (head == null) {
            System.out.println("Danh sách rỗng, không có gì để xóa.");
            return;
        }

        Node current = head;
        Node toDelete = null;

        do {
            if (current.data == data) {
                toDelete = current;
                break;
            }
            current = current.next;
        } while (current != head);

        if (toDelete != null) {
            if (toDelete.next == toDelete) {
                // Chỉ có một nút trong danh sách
                head = null;
            } else {
                toDelete.prev.next = toDelete.next;
                toDelete.next.prev = toDelete.prev;
                if (toDelete == head) {
                    head = toDelete.next;
                }
            }
            System.out.println("Đã xóa nút với dữ liệu: " + data);
        } else {
            System.out.println("Không tìm thấy nút với dữ liệu " + data + ".");
        }
    }

    // In danh sách từ đầu đến cuối
    public void print() {
        if (head == null) {
            System.out.println("Danh sách rỗng.");
            return;
        }

        Node current = head;

        System.out.print("Nội dung danh sách: ");

        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);

        System.out.println();
    }

    // Tìm kiếm một nút với dữ liệu được chỉ định
    public boolean search(int data) {
        if (head == null) {
            return false;
        }

        Node current = head;

        do {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        } while (current != head);

        return false;
    }

    public static void main(String[] args) {
        DoubleCircularLinkedList list = new DoubleCircularLinkedList();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        list.print(); // Output: Nội dung danh sách: 10 20 30 40

        System.out.println("Tìm kiếm 30: " + list.search(30)); // Output: true
        System.out.println("Tìm kiếm 50: " + list.search(50)); // Output: false

        list.delete(20);
        list.print(); // Output: Nội dung danh sách: 10 30 40

        // Xóa nút đầu tiên
        list.delete(10);
        list.print(); // Output: Nội dung danh sách: 30 40

        // Xóa các phần tử còn lại
        list.delete(30);
        list.delete(40);
        list.print(); // Output: Danh sách rỗng.
    }
}
