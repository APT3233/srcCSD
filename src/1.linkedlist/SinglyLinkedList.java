/*
 * CSD
 * Singly LinkedList Java
 * AUthor: APT3233
 */


class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
    Node(){}
}

class SinglyLinkedList {
    Node head;

    // Thêm vào đầu danh sách
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Thêm vào cuối danh sách
    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Thêm vào vị trí n-th (n bắt đầu từ 0)
    public void insertAtPosition(int data, int position) {
        if (position < 0) return;
        Node newNode = new Node(data);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) return;
        newNode.next = current.next;
        current.next = newNode;
    }

    // Tìm kiếm một phần tử trong danh sách
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Tìm kiểm và trả về ví trị của node đó
    public int search2(int data) {
        Node current = head;
        int position = 0;
        
        while (current != null) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }
        
        return -1;
    }

    // Xoá phần tử có giá trị data
    public void delete(int data) {
        if (head == null) return;
        if (head.data == data) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Xóa phần tử thứ n
    public void deleteNth(int pos){
        if(head == null || pos < 0)    return;
        Node dummy = new Node();
        dummy.next = head;
        Node curr = dummy;

        for(int i = 0; i < pos && curr.next != null ; i++)
            curr = curr.next;
        
        if(curr.next != null)   curr.next = curr.next.next;

        head = dummy.next;
    }
    

    // In từ đầu đến cuối
    public void printInfix() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // In ngược từ cuối về đầu (sử dụng đệ quy)
    public void printReverse(Node node) {
        if (node == null) return;
        printReverse(node.next);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // Thêm vào danh sách
        for(int i = 1; i <= 10; i++)
            list.insertAtTail(i);
        list.insertAtPosition(20, 1);
        System.out.print("Infix: ");
        list.printInfix();
        // --> Out: 1 20 2 3 4 5 6 7 8 9 10 

        // In ngược từ cuối về đầu
        System.out.print("Reverse: ");list.printReverse(list.head);
        

        System.out.println("\nSearch 5: " + list.search(5));
        System.out.println("Search 15 in position: " + list.search2(15));

        list.deleteNth(1);
        System.out.print("After deletion node 2(start:0): ");list.printInfix();
        
    }
}
