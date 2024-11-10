
public class CircularQueue {
    private int[] queueArray; // Mảng lưu trữ các phần tử của hàng đợi
    private int front;        // Chỉ số phần tử đầu hàng đợi
    private int rear;         // Chỉ số phần tử cuối hàng đợi
    private int size;         // Kích thước hiện tại của hàng đợi
    private int capacity;     // Sức chứa tối đa của hàng đợi

    // Constructor
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queueArray = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * @add_basic
     * public void enqueue(int value) {
           if (isFull()) {
                System.out.println("Hàng đợi đã đầy. Không thể thêm phần tử.");
                return;
            }
            rear = (rear + 1) % capacity;
            queueArray[rear] = value;
            size++;
            System.out.println("Đã thêm: " + value);
        }
     *  
     */ 

    /**
     * Thêm phương thức expandCapacity trong lớp CircularQueue
     * @Advange
     */ 
    private void expandCapacity() {
        int newCapacity = capacity * 2;
        int[] newArray = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = queueArray[(front + i) % capacity];
        }
        queueArray = newArray;
        capacity = newCapacity;
        front = 0;
        rear = size - 1;
        System.out.println("Đã mở rộng kích thước hàng đợi lên: " + capacity);
    }

    // Chỉnh sửa phương thức enqueue
    public void enqueue(int value) {
        if (isFull()) {
            expandCapacity();
        }
        rear = (rear + 1) % capacity;
        queueArray[rear] = value;
        size++;
        System.out.println("Đã thêm: " + value);
    }


    

    // Loại bỏ và trả về phần tử ở đầu hàng đợi
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Hàng đợi rỗng. Không thể loại bỏ phần tử.");
            return -1; // Hoặc ném ngoại lệ
        }
        int value = queueArray[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Đã loại bỏ: " + value);
        return value;
    }

    // Xem phần tử ở đầu hàng đợi mà không loại bỏ nó
    public int peek() {
        if (isEmpty()) {
            System.out.println("Hàng đợi rỗng. Không có phần tử để xem.");
            return -1; // Hoặc ném ngoại lệ
        }
        return queueArray[front];
    }

    // Kiểm tra hàng đợi có rỗng không
    public boolean isEmpty() {
        return size == 0;
    }

    // Kiểm tra hàng đợi có đầy không
    public boolean isFull() {
        return size == capacity;
    }

    // Hiển thị các phần tử trong hàng đợi
    public void print() {
        if (isEmpty()) {
            System.out.println("Hàng đợi rỗng.");
            return;
        }
        System.out.print("Nội dung hàng đợi: ");
        for (int i = 0; i < size; i++) {
            System.out.print(queueArray[(front + i) % capacity] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);


        queue.print(); // Output:  10 20 30 40

        System.out.println("Phần tử đầu hàng đợi: " + queue.peek());

        // Loại bỏ phần tử
        queue.dequeue();
        queue.dequeue();

        queue.print(); // Output: Nội dung hàng đợi: 30 40


        queue.enqueue(50);
        queue.enqueue(60);

        queue.print(); // Output: Nội dung hàng đợi: 30 40 50 60

        // Thử thêm khi hàng đợi đầy
        queue.enqueue(70); // Sẽ thông báo hàng đợi đã đầy

        // Loại bỏ tất cả các phần tử
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();


        System.out.println("Hàng đợi rỗng: " + queue.isEmpty());
    }
}
