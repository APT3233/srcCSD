


// Định nghĩa lớp Node cho danh sách liên kết
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MergeSortLinkedListIterative {

    // Phương thức thêm nút vào danh sách liên kết
    public static Node addNode(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null)
            return newNode;
        Node current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
        return head;
    }

    // Phương thức in danh sách liên kết
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Phương thức trộn hai danh sách đã sắp xếp và trả về danh sách đã trộn
    public static Node mergeTwoLists(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null)
            tail.next = l1;
        else
            tail.next = l2;

        return dummy.next;
    }

    // Phương thức tính độ dài của danh sách liên kết
    public static int getLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    // Phương thức trộn các sublists với kích thước 'step'
    public static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;

        int length = getLength(head);

        Node dummy = new Node(0);
        dummy.next = head;

        for (int step = 1; step < length; step *= 2) {
            Node current = dummy.next;
            Node tail = dummy;

            while (current != null) {
                Node left = current;
                Node right = split(left, step);
                current = split(right, step);
                tail.next = mergeTwoLists(left, right);
                while (tail.next != null)
                    tail = tail.next;
            }
        }

        return dummy.next;
    }

    // Phương thức tách danh sách thành hai phần, tách sau 'step' nút
    public static Node split(Node head, int step) {
        if (head == null)
            return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        Node second = head.next;
        head.next = null;
        return second;
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        Node head = null;
        head = addNode(head, 10);
        head = addNode(head, 7);
        head = addNode(head, 8);
        head = addNode(head, 9);
        head = addNode(head, 1);
        head = addNode(head, 5);

        System.out.println("Danh sách liên kết ban đầu:");
        printList(head);

        head = mergeSort(head);

        System.out.println("Danh sách liên kết sau khi sắp xếp (Merge Sort Không Đệ Quy):");
        printList(head);
    }
}
