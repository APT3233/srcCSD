import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Lớp đại diện cho một cạnh trong đồ thị
class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

// Lớp đại diện cho đồ thị sử dụng danh sách kề
class Graph {
    private int numVertices;
    private List<List<Edge>> adjList;

    // Constructor
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for(int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Phương thức thêm cạnh vào đồ thị (đồ thị vô hướng)
    public void addEdge(int from, int to, int weight) {
        adjList.get(from).add(new Edge(to, weight));
        adjList.get(to).add(new Edge(from, weight));
    }

    // Getter cho danh sách kề
    public List<List<Edge>> getAdjList() {
        return adjList;
    }

    // Getter cho số lượng đỉnh
    public int getNumVertices() {
        return numVertices;
    }
}

public class DijkstraAlgorithm {
    private int numVertices;
    private List<List<Edge>> adjList;
    private int[] distances;
    private int[] parents;

    // Constructor
    public DijkstraAlgorithm(Graph graph) {
        this.numVertices = graph.getNumVertices();
        this.adjList = graph.getAdjList();
        this.distances = new int[numVertices];
        this.parents = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
    }

    // Hàm thực hiện thuật toán Dijkstra
    public void dijkstra(int source) {
        // Khoảng cách từ nguồn đến nguồn là 0
        distances[source] = 0;

        // Sử dụng PriorityQueue để chọn đỉnh có khoảng cách nhỏ nhất
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex(source, 0));

        while(!priorityQueue.isEmpty()) {
            Vertex current = priorityQueue.poll();
            int u = current.vertex;

            // Duyệt tất cả các đỉnh kề của u
            for(Edge edge : adjList.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                // Nếu tìm thấy đường đi ngắn hơn đến v thông qua u
                if(distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    parents[v] = u;
                    priorityQueue.add(new Vertex(v, distances[v]));
                }
            }
        }
    }

    // Hàm in khoảng cách từ nguồn đến tất cả các đỉnh
    public void printDistances(int source) {
        System.out.println("Khoảng cách từ đỉnh " + source + " đến các đỉnh khác:");
        for(int i = 0; i < numVertices; i++) {
            if(distances[i] == Integer.MAX_VALUE)
                System.out.println(source + " -> " + i + " : Không thể đạt được");
            else
                System.out.println(source + " -> " + i + " : " + distances[i]);
        }
    }

    // Hàm in đường đi ngắn nhất từ nguồn đến đích
    public void printPath(int source, int dest) {
        if(distances[dest] == Integer.MAX_VALUE) {
            System.out.println("Không có đường đi từ " + source + " đến " + dest);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int current = dest;
        while(current != -1) {
            path.add(current);
            current = parents[current];
        }

        // In đường đi ngược lại
        System.out.print("Đường đi ngắn nhất từ " + source + " đến " + dest + " là: ");
        for(int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
            if(i != 0)
                System.out.print(" -> ");
        }
        System.out.println("\nTổng khoảng cách: " + distances[dest]);
    }

    // Lớp đại diện cho một đỉnh trong PriorityQueue
    class Vertex implements Comparable<Vertex> {
        int vertex;
        int distance;

        Vertex(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        // So sánh dựa trên khoảng cách để PriorityQueue hoạt động đúng
        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // Hàm chính để chạy ví dụ
    public static void main(String[] args) {
        /*
         Ví dụ Đồ thị:
             (0)
            /   \
          10     5
          /       \
        (1)---1---(2)
          \     \
           1     2
            \     \
            (4)---1---(3)
        */
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 2);
        graph.addEdge(4, 3, 1);
        graph.addEdge(2, 3, 3);

        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        int source = 0;
        da.dijkstra(source);
        da.printDistances(source);

        int dest = 3;
        da.printPath(source, dest);
    }
}
