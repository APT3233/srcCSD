import java.util.List;

/**
 * @Tree 
 * Toàn bộ hàm về tree
 */

 class Node{
    int value;
    Node left, right;

    Node(int value){
        this.value = value;
        left = right = null;
    }
}

class Tree {
    Node root;
    Tree()  { root = null; }

    // insert value
    public void insert(int value) {
        root = _insert(root, value);
    }

    private Node _insert(Node root, int value) {
        if(root == null) {
            root = new Node(value);
            return root;
        }
        if(value < root.value)
            root.left = _insert(root.left, value);
        else if(value > root.value)
            root.right = _insert(root.right, value);
        
        return root;
    }

    // find value
    public boolean find(int value) {
        return _find(root, value) != null;
    }

    private Node _find(Node root, int value) {
        if(root == null)
            return null;
        
        if(root.value == value)
            return root;
        
        if(value < root.value)
            return _find(root.left, value);
        else 
            return _find(root.right, value);
    }

    // remove
    public void remove(int value) {
        root = _remove(root, value);
    }

    private Node _remove(Node root, int value) {
        if(root == null)  
            return null;
        
        // di chuyen toi node can xoa
        if(value > root.value)
            root.right = _remove(root.right, value);
        else if(value < root.value)
            root.left = _remove(root.left, value);
        
        else {
            if(root.left == null)
                return root.right;
            if(root.right == null)
                return root.left;
            
            else {
                Node tmp = findLeftMost(root.right);
                root.value = tmp.value; 
                root.right = _remove(root.right, tmp.value);
            }
        }
        return root;
    }

    private Node findLeftMost(Node node) {
        Node curr = node;
        while(curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    // print In-order
    public void inOrder(Node node) {
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    // count node
    public int countNodes() {
        return countNodesRec(root);
    }
    private int countNodesRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    // count height
    public int height() {
        return _heightRec(root);
    }
    private int _heightRec(Node node) {
        if (node == null) {
            return -1; 
        }
        int leftHeight = _heightRec(node.left);
        int rightHeight = _heightRec(node.right);
        
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // BST max height
    public void createMaxHeightBST(List<Node> node) {
        root = null;
        for (Node worker : node) {
            root = insertMaxHeight(root, worker);
        }
    }
    private Node insertMaxHeight(Node root, Node node) {
        if (root == null) {
            return new Node(node.value);
        }
        
        root.right = insertMaxHeight(root.right,node);
        return root;
    }

    // BST min height
    public void createMinHeightBST(List<Integer> values) {
        root = null;
        root = buildMinHeightBST(values, 0, values.size() - 1);
    }
    private Node buildMinHeightBST(List<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(values.get(mid));

        node.left = buildMinHeightBST(values, start, mid - 1);
        node.right = buildMinHeightBST(values, mid + 1, end);

        return node;
    }

    // Kiểm tra cây có phải là AVL không
    public boolean isAVL() {
        return checkAVL(root) != -1;
    }
    private int checkAVL(Node node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);
        
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        // Trả về chiều cao của cây
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Hàm cập nhật giá trị của một node
    public boolean update(int oldValue, int newValue) {
        Node targetNode = _find(root, oldValue);
        if (targetNode != null) {
            remove(oldValue);     
            insert(newValue);     
            return true;
        } else {
            return false;         
        }
    }
    
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("In-order traversal:");
        tree.inOrder(tree.root);

        // Kiểm tra tìm kiếm
        System.out.println("Tìm 40: " + tree.find(40));
        System.out.println("Tìm 100: " + tree.find(100));

        // Thử xóa một số phần tử
        tree.remove(20);
        System.out.println("Sau khi xóa 20:");
        tree.inOrder(tree.root);

        tree.remove(30);
        System.out.println("Sau khi xóa 30:");
        tree.inOrder(tree.root);

        tree.remove(50);
        System.out.println("Sau khi xóa 50:");
        tree.inOrder(tree.root);
    }
}
