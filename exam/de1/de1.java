package exam.de1;

import java.util.ArrayList;
import java.util.List;

class Workers{
    int key, age;
    String name;
    Workers left, right;

    Workers(int key, String name, int age){
        this.key = key;
        this.name = name;
        this.age = age;
        left = right = null;
    }
}

class T {
    Workers root;
    T(){
        root = null;
    }

    // Q1
    public boolean findNode(int key){
        return _findNode(root, null, key) != null;
    }
    private Workers _findNode(Workers worker, Workers parent, int key){
        if(worker==null)
            return worker;
        if(worker.key == key){
            System.out.println("Parent Key: " + (parent != null ? parent.key : "None"));
            return worker;
        }
        
        if(key < worker.key)
            return _findNode(worker.left, worker, key);
        return _findNode(worker.right, worker, key);
    }

    // Q2
    public void insert(int key, String name, int age){
        root = _insert(root, key, name, age);
    }
    private Workers _insert(Workers root, int key, String name, int age){
        if(root == null){
            root = new Workers(key, name, age);
            return root;
        }
        
        if(key > root.key)
            root.right = _insert(root.right, key, name, age);
        if(key < root.key)
            root.left = _insert(root.left, key, name, age);

        return root;
    }

    // Q3
    public void printDesc(){
        _printDesc(root);
    }
    private void _printDesc(Workers root){
        if(root==null)  return;

        _printDesc(root.right);
        System.out.println(root.key + " " + root.name + " " + root.age);
        _printDesc(root.left);
    }

    // Q4
    public int countWorker(){
        return _countWorker(root);
    }
    private int _countWorker(Workers root){
        if(root==null)
            return 0;
    
        int count = (root.age < 25) ? 1:0;
        count += _countWorker(root.left);
        count += _countWorker(root.right);
        return count;   
    }

    // Q5
    public void deleteRightMost(){
        root = _deleteRighMost(root);
    }
    private Workers _deleteRighMost(Workers root){
        if(root == null)
            return null;
        if(root.right == null)
            return root.left;
        root.right = _deleteRighMost(root.right);

        return root;
    }

    // Q6
    public int findHeight(){
        return _findHeight(root);
    }
    private int _findHeight(Workers root){
        if(root==null)    return -1;
        
        int leftHeight = _findHeight(root.left);
        int rightHeight = _findHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
    // public int findHeightLevelOrder() {
    //     if (root == null) return -1;
    
    //     Queue<Workers> queue = new LinkedList<>();
    //     queue.add(root);
    //     int height = -1;
    
    //     while (!queue.isEmpty()) {
    //         int levelSize = queue.size();
    //         height++;
    //         for (int i = 0; i < levelSize; i++) {
    //             Workers current = queue.poll();
    //             if (current.left != null) queue.add(current.left);
    //             if (current.right != null) queue.add(current.right);
    //         }
    //     }
    //     return height;
    // }
    

    // Q7 
    public void createMaxHeightBST(List<Workers> workers) {
        root = null;
        for (Workers worker : workers) {
            root = insertMaxHeight(root, worker);
        }
    }
    private Workers insertMaxHeight(Workers root, Workers worker) {
        if (root == null) {
            return new Workers(worker.key, worker.name, worker.age);
        }
        
        root.right = insertMaxHeight(root.right, worker);
        return root;
    }
    
    public static void main(String[] args) {
        T tree = new T();

        // Tạo một số công nhân
        tree.insert(10, "Alice", 30);
        tree.insert(5, "Bob", 20);
        tree.insert(15, "Charlie", 22);
        tree.insert(3, "David", 28);
        tree.insert(7, "Eve", 24);
        tree.insert(20, "Frank", 26);

        // Q1: Tìm node chứa công nhân với khóa cho trước
        System.out.println("Q1: Find Node with key 15:");
        tree.findNode(15); // Tìm công nhân có khóa là 15

        // Q2: Chèn công nhân mới nếu chưa tồn tại
        System.out.println("\nQ2: Insert a new worker with key 12:");
        tree.insert(12, "Grace", 29);
        
        // Q3: Xuất ra các công nhân theo thứ tự giảm dần
        System.out.println("\nQ3: Print workers in descending order:");
        tree.printDesc();

        // Q4: Đếm số lượng công nhân dưới 25 tuổi
        System.out.println("\nQ4: Count workers with age less than 25:");
        int count = tree.countWorker();
        System.out.println("Number of workers under age 25: " + count);

        // Q5: Xóa node ngoài cùng bên phải
        System.out.println("\nQ5: Delete right-most node:");
        tree.deleteRightMost();
        System.out.println("Tree after deleting right-most node:");
        tree.printDesc();

        // Q6: Xác định chiều cao của cây
        System.out.println("\nQ6: Find height of the tree:");
        int height = tree.findHeight();
        System.out.println("Height of the tree: " + height);

        // Q7: Tạo cây có chiều cao lớn nhất từ danh sách công nhân
        System.out.println("\nQ7: Create a BST with maximum height:");
        List<Workers> workers = new ArrayList<>();
        workers.add(new Workers(1, "Henry", 30));
        workers.add(new Workers(2, "Isabel", 25));
        workers.add(new Workers(3, "Jack", 28));
        workers.add(new Workers(4, "Karen", 32));

        T maxHeightTree = new T();
        maxHeightTree.createMaxHeightBST(workers);
        System.out.println("Max-height BST (linear structure):");
        maxHeightTree.printDesc(); // Sẽ in theo thứ tự từ lớn đến bé
    }
}
