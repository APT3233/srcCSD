/**
 * Stack using Array [int]
 * Advange 
 */



public class StackDynamic {
    private int[] stackArray; // Mảng lưu trữ các phần tử của ngăn xếp
    private int length; // Số lượng phần tử hiện tại trong ngăn xếp

    // Constructor
    public StackDynamic(int size) {
        this.stackArray = new int[size];
        this.length = 0;
    }

    // Thêm một phần tử vào ngăn xếp
    public void push(int value) {
        if (isFull()) {
            expandCapacity();
        }
        stackArray[length++] = value;
    }
    // Mở rộng kích thước của mảng
    private void expandCapacity() {
        int newSize = stackArray.length * 2;
        int[] newArray = new int[newSize];
        System.arraycopy(stackArray, 0, newArray, 0, stackArray.length);
        stackArray = newArray;
    }


    // Loại bỏ và trả về phần tử trên cùng của ngăn xếp
    public int pop() {
        if (isEmpty()) {
            System.out.println("Ngăn xếp rỗng. Không thể xóa phần tử.");
            return -1;
        }
        int value = stackArray[--length];
        return value;
    }

    // Xem phần tử trên cùng của ngăn xếp mà không loại bỏ nó
    public int peek() {
        if (isEmpty()) {
            System.out.println("Ngăn xếp rỗng. Không có phần tử trên cùng.");
            return -1;
        }
        return stackArray[length - 1];
    }

    // Kiểm tra ngăn xếp có rỗng không
    public boolean isEmpty() {
        return (length == 0);
    }

    // Kiểm tra ngăn xếp có đầy không
    public boolean isFull() {
        return (length == stackArray.length);
    }

    // Tìm kiếm một phần tử trong ngăn xếp
    public int search(int value) {
        for (int i = length - 1; i >= 0; i--) {
            if (stackArray[i] == value) {
                return length - 1 - i; // Vị trí tính từ đỉnh ngăn xếp
            }
        }
        return -1;
    }

    // Hiển thị các phần tử trong ngăn xếp
    public void print() {
        if (isEmpty()) {
            System.out.println("Ngăn xếp rỗng.");
            return;
        }
        System.out.print("Nội dung ngăn xếp: ");
        for (int i = length - 1; i >= 0; i--) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StackDynamic stack = new StackDynamic(10); 
    
        // Thêm phần tử vào ngăn xếp
        stack.push(10);
        stack.push(20);
        stack.push(30);
    
        stack.print(); // Output --> 30 20 10
    
        System.out.println("Phần tử trên cùng: " + stack.peek());
    
        // Tìm kiếm phần tử
        System.out.println("Vị trí của 20: " + stack.search(20));
    
        // Loại bỏ phần tử
        stack.pop();
    
        // Hiển thị ngăn xếp sau khi pop
        stack.print(); // Output: Nội dung ngăn xếp: 20 10
    
        // Kiểm tra ngăn xếp rỗng
        System.out.println("Ngăn xếp rỗng: " + stack.isEmpty());
    
        // Thêm phần tử đến khi đầy
        stack.push(40);
        stack.push(50);
        stack.push(60); // Sẽ thông báo mở rộng ngăn xếp
    
        // Hiển thị ngăn xếp đầy đủ
        stack.print(); // Output: Nội dung ngăn xếp: 60 50 40 20 10
    
        // Kiểm tra ngăn xếp đầy
        System.out.println("Ngăn xếp đầy: " + stack.isFull());
    }
    
}
