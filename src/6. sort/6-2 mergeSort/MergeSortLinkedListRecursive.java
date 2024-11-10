// Định nghĩa lớp Node cho danh sách liên kết
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class MergeSortLinkedListRecursive {

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

    // Phương thức tìm giữa của danh sách liên kết
    public static Node getMiddle(Node head) {
        if (head == null)
            return head;

        Node slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Phương thức trộn hai danh sách liên kết đã sắp xếp
    public static Node sortedMerge(Node a, Node b) {
        if (a == null)
            return b;
        if (b == null)
            return a;

        Node result;

        if (a.data <= b.data) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }

        return result;
    }

    // Phương thức thực hiện Merge Sort đệ quy cho danh sách liên kết
    public static Node mergeSort(Node head) {
        // Base case: nếu danh sách rỗng hoặc chỉ có một nút
        if (head == null || head.next == null)
            return head;

        // Tìm giữa danh sách liên kết
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;

        // Tách danh sách thành hai nửa
        middle.next = null;

        // Đệ quy sắp xếp hai nửa
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        // Trộn hai nửa đã sắp xếp
        Node sortedList = sortedMerge(left, right);
        return sortedList;
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        Node head = null;
        head = addNode(head, 15);
        head = addNode(head, 10);
        head = addNode(head, 5);
        head = addNode(head, 20);
        head = addNode(head, 3);
        head = addNode(head, 2);

        System.out.println("Danh sách liên kết ban đầu:");
        printList(head);

        head = mergeSort(head);

        System.out.println("Danh sách liên kết sau khi sắp xếp (Merge Sort Đệ Quy):");
        printList(head);
    }
}
