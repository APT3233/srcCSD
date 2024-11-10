



// Định nghĩa lớp Node cho danh sách liên kết
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class QuickSortLinkedList {

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

    // Phương thức tìm nút cuối cùng của danh sách liên kết
    public static Node getTail(Node head) {
        while (head != null && head.next != null)
            head = head.next;
        return head;
    }

    // Phương thức phân chia danh sách liên kết và trả về pivot
    public static Node partition(Node head, Node end, Node[] newHead, Node[] newEnd) {
        Node pivot = end;
        Node prev = null;
        Node curr = head;
        Node tail = pivot;

        // Dùng để xây dựng danh sách nhỏ hơn pivot và lớn hơn pivot
        Node localHead = null, localEnd = pivot;

        while (curr != pivot) {
            if (curr.data < pivot.data) {
                // Nếu đây là phần tử nhỏ hơn pivot, thêm vào đầu danh sách nhỏ hơn
                if (localHead == null)
                    localHead = curr;

                prev = curr;
                curr = curr.next;
            } else {
                // Nếu phần tử lớn hơn hoặc bằng pivot, di chuyển nó đến cuối danh sách
                if (prev != null)
                    prev.next = curr.next;
                Node tmp = curr.next;
                curr.next = null;
                tail.next = curr;
                tail = curr;
                curr = tmp;
            }
        }

        // Nếu không có phần tử nào nhỏ hơn pivot
        if (localHead == null)
            localHead = pivot;

        // Trả về đầu và cuối của danh sách đã được phân chia
        newHead[0] = localHead;
        newEnd[0] = tail;

        return pivot;
    }

    // Hàm chính Quick Sort đệ quy
    public static Node quickSortRecur(Node head, Node end) {
        if (head == null || head == end)
            return head;

        Node[] newHead = new Node[1];
        Node[] newEnd = new Node[1];

        // Phân chia danh sách và lấy pivot
        Node pivot = partition(head, end, newHead, newEnd);

        // Nếu pivot là nhỏ nhất, không cần sắp xếp phần bên trái
        if (newHead[0] != pivot) {
            // Tìm node trước pivot để cắt danh sách
            Node temp = newHead[0];
            while (temp.next != pivot)
                temp = temp.next;
            temp.next = null;

            // Đệ quy sắp xếp phần bên trái pivot
            newHead[0] = quickSortRecur(newHead[0], temp);

            // Kết nối phần đã sắp xếp với pivot
            temp = getTail(newHead[0]);
            temp.next = pivot;
        }

        // Đệ quy sắp xếp phần bên phải pivot
        pivot.next = quickSortRecur(pivot.next, newEnd[0]);

        return newHead[0];
    }

    // Hàm thực hiện Quick Sort cho danh sách liên kết
    public static Node quickSort(Node head) {
        // Tìm node cuối cùng của danh sách
        Node end = getTail(head);

        // Gọi hàm Quick Sort đệ quy
        return quickSortRecur(head, end);
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

        head = quickSort(head);

        System.out.println("Danh sách liên kết sau khi sắp xếp (Quick Sort):");
        printList(head);
    }
}
