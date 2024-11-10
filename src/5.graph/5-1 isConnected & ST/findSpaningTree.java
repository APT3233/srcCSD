/**
 * @Spaning Tree (Cây bao trùm)
 * Sử dụng BFS để tìm Spaning Tree của một đồ thị.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Định nghĩa lớp Graph
class Graph {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Constructor
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for(int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Hàm thêm cạnh vào đồ thị (đồ thị vô hướng)
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    // Hàm tìm cây bao trùm sử dụng BFS
    public List<String> getSpanningTreeBFS(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        List<String> spanningTree = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Bắt đầu BFS từ nút startVertex
        visited[startVertex] = true;
        queue.add(startVertex);

        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(int neighbor : adjList.get(u)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    spanningTree.add(u + " - " + neighbor);
                }
            }
        }

        return spanningTree;
    }

    // Hàm chính để chạy ví dụ
    public static void main(String[] args) {
        // Tạo đồ thị với 6 nút (0 đến 5)
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        // Tìm cây bao trùm sử dụng BFS bắt đầu từ nút 0
        List<String> spanningTree = graph.getSpanningTreeBFS(0);

        // In ra các cạnh của cây bao trùm
        System.out.println("Cây bao trùm sử dụng BFS:");
        for(String edge : spanningTree) {
            System.out.println(edge);
        }

        // Kết quả có thể là:
        // Cây bao trùm sử dụng BFS:
        // 0 - 1
        // 0 - 2
        // 1 - 3
        // 2 - 4
        // 3 - 5
    }
}
