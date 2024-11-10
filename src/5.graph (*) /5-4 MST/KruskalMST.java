
/**
 * @FindMST
 * @Method: Kruskal
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


class KruskalMST {
    int V, E;
    Edge[] edge;

    // Constructor
    KruskalMST(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
    }

    // Tìm tập hợp của một đỉnh sử dụng path compression
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Hợp nhất hai tập hợp sử dụng union by rank
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        }
        else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        }
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Hàm để xây dựng và in MST sử dụng thuật toán Kruskal
    void KruskalMST() {
        // Sắp xếp các cạnh theo trọng số tăng dần
        Arrays.sort(edge);

        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; ++i)
            subsets[i] = new Subset();

        for (int i = 0; i < V; ++i) {
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        Edge[] result = new Edge[V];
        int e = 0; // Số cạnh trong kết quả
        int i = 0; // Chỉ số cạnh trong mảng đã sắp xếp

        while (e < V - 1 && i < E) {
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // Nếu không tạo thành chu trình, thêm vào kết quả
            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }

        // Kiểm tra xem MST có đầy đủ không
        if (e != V - 1) {
            System.out.println("Đồ thị không liên thông. MST không tồn tại.");
            return;
        }

        // In kết quả MST
        System.out.println("Cạnh trong MST (Thuật toán Kruskal):");
        int totalWeight = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " - " + result[i].dest + " : " + result[i].weight);
            totalWeight += result[i].weight;
        }
        System.out.println("Tổng trọng số của MST: " + totalWeight);
    }

    public static void main(String[] args) {
        /* Đồ thị ví dụ:
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
        int V = 6; // Số đỉnh
        int E = 9; // Số cạnh (đã thêm cạnh 3-5)

        KruskalMST graph = new KruskalMST(V, E);

        graph.edge[0] = new Edge(0, 1, 10);
        graph.edge[1] = new Edge(0, 2, 6);
        graph.edge[2] = new Edge(0, 3, 5);
        graph.edge[3] = new Edge(1, 3, 15);
        graph.edge[4] = new Edge(2, 3, 4);
        graph.edge[5] = new Edge(2, 4, 5);
        graph.edge[6] = new Edge(3, 4, 9);
        graph.edge[7] = new Edge(1, 4, 7);
        graph.edge[8] = new Edge(3, 5, 2); // Cạnh mới

        graph.KruskalMST();
    }
}
