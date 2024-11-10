
/**
 * @FindMST
 * @Method: Prim
 */


 import java.util.*;

 class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Constructor
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // So sánh các cạnh dựa trên trọng số
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Node implements Comparable<Node> {
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    // So sánh các node dựa trên trọng số
    public int compareTo(Node compareNode) {
        return this.weight - compareNode.weight;
    }
}

class Subset {
    int parent, rank;
}


class PrimMST {
    int V;
    LinkedList<Node>[] adj;

    // Constructor
    PrimMST(int v) {
        V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>();
    }

    // Thêm cạnh vào đồ thị
    void addEdge(int src, int dest, int weight) {
        adj[src].add(new Node(dest, weight));
        adj[dest].add(new Node(src, weight)); // Đồ thị vô hướng
    }

    // Hàm để xây dựng và in MST sử dụng thuật toán Prim
    void primMST() {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];

        // Khởi tạo tất cả các khóa là vô cùng
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // Khởi tạo khóa của đỉnh đầu tiên là 0 để nó được chọn đầu tiên
        key[0] = 0;

        // Tạo Priority Queue và thêm đỉnh đầu tiên vào đó
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, key[0]));

        while (!pq.isEmpty()) {
            // Chọn đỉnh có khóa nhỏ nhất
            Node node = pq.poll();
            int u = node.vertex;

            if (inMST[u])
                continue;

            inMST[u] = true;

            // Duyệt qua tất cả các đỉnh kề của đỉnh u
            for (Node neighbor : adj[u]) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                // Nếu đỉnh v chưa thuộc MST và trọng số cạnh u-v nhỏ hơn khóa hiện tại của v
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        // Kiểm tra xem MST có bao gồm tất cả các đỉnh hay không
        boolean connected = true;
        for (int i = 0; i < V; i++) {
            if (!inMST[i]) {
                connected = false;
                break;
            }
        }

        if (!connected) {
            System.out.println("Đồ thị không liên thông. MST không tồn tại cho đồ thị này.");
            return;
        }

        // In kết quả MST
        System.out.println("Cạnh trong MST (Thuật toán Prim):");
        long totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " : " + key[i]);
            totalWeight += key[i];
        }
        System.out.println("Tổng trọng số của MST: " + totalWeight);
    }

    public static void main(String[] args) {
        /* Đồ thị ví dụ đã được sửa:
            Đỉnh: 0, 1, 2, 3, 4, 5
            Cạnh:
            0 - 1 (10)
            0 - 2 (6)
            0 - 3 (5)
            1 - 3 (15)
            2 - 3 (4)
            2 - 4 (5)
            3 - 4 (9)
            1 - 4 (7)
            3 - 5 (2) // Cạnh mới thêm để đảm bảo liên thông
        */
        int V = 6;
        PrimMST graph = new PrimMST(V);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 9);
        graph.addEdge(1, 4, 7);
        graph.addEdge(3, 5, 2); // Cạnh mới

        graph.primMST();
    }
}
