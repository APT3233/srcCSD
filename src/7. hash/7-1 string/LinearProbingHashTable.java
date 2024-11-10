/**
 * @Hash
 * Bảng Băm với Địa chỉ mở bằng Dò tuyến tính (Linear Probing)
 */



class LinearProbingHashTable<K, V> {
    private int capacity; // Số lượng bucket
    private int size; // Số phần tử hiện tại
    private K[] keys;
    private V[] values;

    // Constructor
    @SuppressWarnings("unchecked")
    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    // Hàm băm
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    // Thêm cặp key-value vào bảng băm
    public void put(K key, V value) {
        if (size >= capacity / 2) {
            resize(2 * capacity);
        }

        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                values[i] = value; // Cập nhật giá trị nếu key đã tồn tại
                return;
            }
            i = (i + 1) % capacity; // Dò tuyến tính
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }

    // Lấy giá trị dựa trên key
    public V get(K key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % capacity; // Dò tuyến tính
        }
        return null; // Trả về null nếu key không tồn tại
    }

    // Xóa cặp key-value dựa trên key
    public void remove(K key) {
        if (!contains(key)) {
            return;
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        values[i] = null;
        size--;

        // Dò các khóa tiếp theo để đảm bảo không có khóa nào bị bỏ sót
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            K tempKey = keys[i];
            V tempValue = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(tempKey, tempValue);
            i = (i + 1) % capacity;
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
        LinearProbingHashTable<K, V> temp = new LinearProbingHashTable<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        capacity = temp.capacity;
    }

    // In bảng băm
    public void printHashTable() {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                System.out.println("Bucket " + i + ": {" + keys[i] + ": " + values[i] + "}");
            } else {
                System.out.println("Bucket " + i + ": null");
            }
        }
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        LinearProbingHashTable<String, Integer> hashTable = new LinearProbingHashTable<>(5);

        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("orange", 3);
        hashTable.put("grape", 4);
        hashTable.put("melon", 5);
        hashTable.put("pear", 6); // Sẽ gây va chạm và phải mở rộng bảng băm

        System.out.println("Bảng băm sau khi thêm các phần tử:");
        hashTable.printHashTable();

        System.out.println("\nGiá trị của key 'banana': " + hashTable.get("banana"));

        hashTable.remove("banana");
        System.out.println("\nBảng băm sau khi xóa key 'banana':");
        hashTable.printHashTable();
    }
}
