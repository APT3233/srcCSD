/**
 * @DFS
 * Sử dụng DFS để kiểm tra tính liên thông của một đồ thị.
 */

import java.util.ArrayList;
import java.util.List;

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

    // Hàm kiểm tra tính liên thông sử dụng DFS
    public boolean isConnected() {
        boolean[] visited = new boolean[numVertices];
        // Bắt đầu DFS từ nút 0
        dfs(0, visited);

        // Kiểm tra xem tất cả các nút đã được duyệt chưa
        for(boolean v : visited) {
            if(!v)
                return false;
        }
        return true;
    }

    // Hàm DFS đệ quy
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for(int neighbor : adjList.get(v)) {
            if(!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // Hàm chính để chạy ví dụ
    public static void main(String[] args) {
        // Tạo đồ thị với 5 nút (0 đến 4)
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(3, 4);

        // Kiểm tra tính liên thông
        if(graph.isConnected())
            System.out.println("Đồ thị liên thông.");
        else
            System.out.println("Đồ thị không liên thông.");
        // Kết quả: Đồ thị không liên thông.

        // Thêm cạnh để đồ thị trở thành liên thông
        graph.addEdge(2, 3);

        // Kiểm tra lại
        if(graph.isConnected())
            System.out.println("Đồ thị liên thông.");
        else
            System.out.println("Đồ thị không liên thông.");
        // Kết quả: Đồ thị liên thông.
    }
}
