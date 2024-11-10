/**
 * @Hash
 *  Bảng Băm với Chuỗi riêng biệt (Separate Chaining)
 */



import java.util.LinkedList;

class HashNode<K, V> {
    K key;
    V value;

    // Constructor
    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class SeparateChainingHashTable<K, V> {
    private int numBuckets; // Số bucket
    private LinkedList<HashNode<K, V>>[] buckets;

    // Constructor
    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int numBuckets) {
        this.numBuckets = numBuckets;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // Hàm băm
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index < 0 ? index * -1 : index;
    }

    // Thêm cặp key-value vào bảng băm
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> bucket = buckets[bucketIndex];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value; // Cập nhật giá trị nếu key đã tồn tại
                return;
            }
        }

        // Thêm node mới nếu key chưa tồn tại
        bucket.add(new HashNode<>(key, value));
    }

    // Lấy giá trị dựa trên key
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> bucket = buckets[bucketIndex];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null; // Trả về null nếu key không tồn tại
    }

    // Xóa cặp key-value dựa trên key
    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> bucket = buckets[bucketIndex];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                return;
            }
        }
    }

    // In bảng băm
    public void printHashTable() {
        for (int i = 0; i < numBuckets; i++) {
            LinkedList<HashNode<K, V>> bucket = buckets[i];
            System.out.print("Bucket " + i + ": ");
            for (HashNode<K, V> node : bucket) {
                System.out.print("{" + node.key + ": " + node.value + "} ");
            }
            System.out.println();
        }
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        SeparateChainingHashTable<String, Integer> hashTable = new SeparateChainingHashTable<>(5);

        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("orange", 3);
        hashTable.put("grape", 4);
        hashTable.put("melon", 5);
        hashTable.put("pear", 6);

        System.out.println("Bảng băm sau khi thêm các phần tử:");
        hashTable.printHashTable();

        System.out.println("\nGiá trị của key 'banana': " + hashTable.get("banana"));

        hashTable.remove("banana");
        System.out.println("\nBảng băm sau khi xóa key 'banana':");
        hashTable.printHashTable();
    }
}
