/**
 * @Hash
 * Bảng Băm với Thăm dò Bậc hai (Quadratic Probing)
 * 
 */


 class QuadraticProbingHashTable<K, V> {
    private int capacity; // Số lượng bucket
    private int size; // Số phần tử hiện tại
    private K[] keys;
    private V[] values;
    private final K DELETED = (K) new Object(); // Đánh dấu vị trí đã xóa

    // Constructor
    @SuppressWarnings("unchecked")
    public QuadraticProbingHashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    // Hàm băm
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    // Thêm cặp key-value vào bảng băm với thăm dò bậc hai
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key không được null");

        if (size >= capacity / 2) {
            resize(2 * capacity);
        }

        int hashValue = hash(key);
        int i = 0;
        int index;
        while (i < capacity) {
            index = (hashValue + i * i) % capacity;
            if (keys[index] == null || keys[index] == DELETED) {
                keys[index] = key;
                values[index] = value;
                size++;
                return;
            } else if (keys[index].equals(key)) {
                values[index] = value; // Cập nhật giá trị nếu key đã tồn tại
                return;
            }
            i++;
        }

        throw new RuntimeException("Bảng băm đầy, không thể thêm key mới.");
    }

    // Lấy giá trị dựa trên key
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key không được null");

        int hashValue = hash(key);
        int i = 0;
        int index;
        while (i < capacity) {
            index = (hashValue + i * i) % capacity;
            if (keys[index] == null) {
                return null; // Key không tồn tại
            }
            if (keys[index].equals(key)) {
                return values[index];
            }
            i++;
        }
        return null; // Key không tồn tại
    }

    // Xóa cặp key-value dựa trên key
    public void remove(K key) {
        if (key == null) throw new IllegalArgumentException("Key không được null");
        if (!contains(key)) return;

        int hashValue = hash(key);
        int i = 0;
        int index;
        while (i < capacity) {
            index = (hashValue + i * i) % capacity;
            if (keys[index].equals(key)) {
                keys[index] = DELETED;
                values[index] = null;
                size--;
                break;
            }
            i++;
        }

        // Giảm kích thước bảng băm nếu cần
        if (size > 0 && size <= capacity / 8) {
            resize(capacity / 2);
        }
    }

    // Kiểm tra xem key có tồn tại trong bảng băm không
    public boolean contains(K key) {
        return get(key) != null;
    }

    // Hàm để resize bảng băm
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        QuadraticProbingHashTable<K, V> temp = new QuadraticProbingHashTable<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != DELETED) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        capacity = temp.capacity;
        size = temp.size;
    }

    // In bảng băm
    public void printHashTable() {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != DELETED) {
                System.out.println("Bucket " + i + ": {" + keys[i] + ": " + values[i] + "}");
            } else {
                System.out.println("Bucket " + i + ": null");
            }
        }
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        QuadraticProbingHashTable<String, Integer> hashTable = new QuadraticProbingHashTable<>(5);

        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("orange", 3);
        hashTable.put("grape", 4);
        hashTable.put("melon", 5);
        hashTable.put("pear", 6); // Sẽ gây va chạm và mở rộng bảng băm

        System.out.println("Bảng băm sau khi thêm các phần tử:");
        hashTable.printHashTable();

        System.out.println("\nGiá trị của key 'banana': " + hashTable.get("banana"));

        hashTable.remove("banana");
        System.out.println("\nBảng băm sau khi xóa key 'banana':");
        hashTable.printHashTable();
    }
}
