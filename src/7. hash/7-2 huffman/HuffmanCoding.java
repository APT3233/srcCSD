/**
 * @Huffman Encrypt
 * Thuật toán Huffman Coding bằng Java, sử dụng cây nhị phân và hàng đợi ưu tiên.
 */


import java.util.*;

class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left, right;

    // Constructor cho nút lá
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Constructor cho nút nội bộ
    public Node(int frequency, Node left, Node right) {
        this.character = '\0'; // Không đại diện cho bất kỳ ký tự nào
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // So sánh các nút dựa trên tần suất
    @Override
    public int compareTo(Node other) {
        return this.frequency - other.frequency;
    }

    // Kiểm tra xem nút có phải là nút lá không
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}

public class HuffmanCoding {
    private Node root;
    private Map<Character, String> huffmanCodes;

    // Constructor
    public HuffmanCoding() {
        this.root = null;
        this.huffmanCodes = new HashMap<>();
    }

    // Hàm xây dựng tần suất ký tự
    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        return frequencyMap;
    }

    // Hàm xây dựng cây Huffman
    private void buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Tạo nút lá cho mỗi ký tự và thêm vào hàng đợi ưu tiên
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Xây dựng cây Huffman
        while (pq.size() > 1) {
            Node left = pq.poll(); // Nút có tần suất thấp nhất
            Node right = pq.poll(); // Nút có tần suất thấp thứ hai

            // Tạo nút nội bộ mới với tần suất là tổng của hai nút
            Node parent = new Node(left.frequency + right.frequency, left, right);
            pq.add(parent);
        }

        // Nút cuối cùng trong hàng đợi là gốc của cây Huffman
        this.root = pq.poll();
    }

    // Hàm tạo mã Huffman cho từng ký tự
    private void generateCodes(Node node, String code) {
        if (node == null)
            return;

        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code.length() > 0 ? code : "0"); // Xử lý trường hợp chỉ có một ký tự
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    // Hàm xây dựng mã Huffman
    public void buildHuffmanCodes(String text) {
        Map<Character, Integer> frequencyMap = buildFrequencyMap(text);
        buildHuffmanTree(frequencyMap);
        generateCodes(this.root, "");
    }

    // Hàm mã hóa văn bản
    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encoded.append(huffmanCodes.get(ch));
        }
        return encoded.toString();
    }

    // Hàm giải mã văn bản
    public String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        Node current = this.root;
        for (int i = 0; i < encodedText.length(); i++) {
            char bit = encodedText.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            } else {
                throw new IllegalArgumentException("Chuỗi mã hóa không hợp lệ.");
            }

            if (current.isLeaf()) {
                decoded.append(current.character);
                current = this.root;
            }
        }
        return decoded.toString();
    }

    // Hàm in mã Huffman cho từng ký tự
    public void printHuffmanCodes() {
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        String text = "this is an example for huffman encoding";

        HuffmanCoding hc = new HuffmanCoding();
        hc.buildHuffmanCodes(text);

        System.out.println("Mã Huffman cho từng ký tự:");
        hc.printHuffmanCodes();

        String encoded = hc.encode(text);
        System.out.println("\nChuỗi mã hóa:");
        System.out.println(encoded);

        String decoded = hc.decode(encoded);
        System.out.println("\nChuỗi giải mã:");
        System.out.println(decoded);
    }
}

