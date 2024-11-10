/**
 * @PriorityQueue_LinkedList
 * 
 */



public class PriorityQueueLinkedList {

    // Lớp Node đại diện cho mỗi phần tử trong hàng đợi ưu tiên
    private class Node {
        int data;       // Dữ liệu của phần tử
        int priority;   // Độ ưu tiên của phần tử
        Node next;      // Tham chiếu tới phần tử tiếp theo

        // Constructor
        public Node(int data, int priority) {
            this.data = data;
            this.priority = priority;
            this.next = null;
        }
    }

    private Node head; // Tham chiếu tới phần tử đầu tiên của hàng đợi

    // Constructor
    public PriorityQueueLinkedList() {
        this.head = null;
    }

    // Thêm một phần tử vào hàng đợi ưu tiên
    public void enqueue(int data, int priority) {
        Node newNode = new Node(data, priority);

        // Nếu hàng đợi rỗng hoặc độ ưu tiên của newNode cao hơn head
        if (head == null || priority < head.priority) {
            newNode.next = head;
            head = newNode;
            System.out.println("Đã thêm: " + data + " với độ ưu tiên " + priority);
        } else {
            // Duyệt danh sách để tìm vị trí chèn thích hợp
            Node current = head;
            while (current.next != null && current.next.priority <= priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            System.out.println("Đã thêm: " + data + " với độ ưu tiên " + priority);
        }
    }

    // Loại bỏ và trả về phần tử có độ ưu tiên cao nhất
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Hàng đợi ưu tiên rỗng. Không thể loại bỏ phần tử.");
            return -1; // Hoặc ném ngoại lệ
        }
        int value = head.data;
        head = head.next;
        System.out.println("Đã loại bỏ: " + value);
        return value;
    }

    // Kiểm tra hàng đợi có rỗng không
    public boolean isEmpty() {
        return head == null;
    }

    // Hiển thị các phần tử trong hàng đợi
    public void print() {
        if (isEmpty()) {
            System.out.println("Hàng đợi ưu tiên rỗng.");
            return;
        }
        System.out.print("Nội dung hàng đợi ưu tiên: ");
        Node current = head;
        while (current != null) {
            System.out.print("(" + current.data + ", " + current.priority + ") ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PriorityQueueLinkedList priorityQueue = new PriorityQueueLinkedList();

        // Thêm phần tử vào hàng đợi ưu tiên
        priorityQueue.enqueue(30, 3);
        priorityQueue.enqueue(20, 2);
        priorityQueue.enqueue(40, 4);
        priorityQueue.enqueue(10, 1);

        // Hiển thị hàng đợi ưu tiên
        priorityQueue.print(); // Output: (10, 1) (20, 2) (30, 3) (40, 4)

        // Loại bỏ phần tử
        priorityQueue.dequeue(); // Loại bỏ 10

        // Hiển thị hàng đợi ưu tiên sau khi loại bỏ
        priorityQueue.print(); // Output: (20, 2) (30, 3) (40, 4)

        // Kiểm tra hàng đợi rỗng
        System.out.println("Hàng đợi ưu tiên rỗng: " + priorityQueue.isEmpty());

        // Loại bỏ tất cả các phần tử
        priorityQueue.dequeue();
        priorityQueue.dequeue();
        priorityQueue.dequeue();

        // Kiểm tra hàng đợi rỗng
        System.out.println("Hàng đợi ưu tiên rỗng: " + priorityQueue.isEmpty());
    }
}
