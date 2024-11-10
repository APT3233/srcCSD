/**
 * @Backtracking (Quay lui)
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // Phương thức thêm cạnh (đồ thị vô hướng)
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    // Getter cho danh sách kề
    public List<List<Integer>> getAdjList() {
        return adjList;
    }

    // Getter cho số lượng đỉnh
    public int getNumVertices() {
        return numVertices;
    }
}


class GraphColoringBacktracking {
    private int numVertices;
    private List<List<Integer>> adjList;
    private int[] result;

    public GraphColoringBacktracking(Graph graph) {
        this.numVertices = graph.getNumVertices();
        this.adjList = graph.getAdjList();
        this.result = new int[numVertices];
        Arrays.fill(this.result, -1); // -1 nghĩa là chưa được gán màu
    }

    // Hàm kiểm tra xem việc gán màu c cho đỉnh v có hợp lệ không
    private boolean isSafe(int v, int color) {
        for(int i : adjList.get(v)) {
            if(result[i] == color)
                return false;
        }
        return true;
    }

    // Hàm quay lui để giải quyết bài toán tô màu
    private boolean graphColoringUtil(int v, int numColors) {
        // Nếu đã gán màu cho tất cả các đỉnh, trả về true
        if(v == numVertices)
            return true;

        // Thử gán từng màu cho đỉnh v
        for(int c = 1; c <= numColors; c++) {
            // Kiểm tra xem màu c có thể gán cho đỉnh v không
            if(isSafe(v, c)) {
                result[v] = c;

                // Gọi đệ quy để gán màu cho các đỉnh tiếp theo
                if(graphColoringUtil(v + 1, numColors))
                    return true;

                // Nếu không thể gán màu, quay lui
                result[v] = -1;
            }
        }

        // Nếu không thể gán màu nào cho đỉnh v, trả về false
        return false;
    }

    // Hàm tìm số màu tối thiểu cần sử dụng
    public int findMinColors() {
        for(int numColors = 1; numColors <= numVertices; numColors++) {
            Arrays.fill(result, -1);
            if(graphColoringUtil(0, numColors))
                return numColors;
        }
        return numVertices; // Trường hợp xấu nhất
    }

    // Hàm thực hiện tô màu đồ thị với số màu đã cho
    public boolean colorGraph(int numColors) {
        Arrays.fill(result, -1);
        if(graphColoringUtil(0, numColors)) {
            return true;
        }
        return false;
    }

    // Hàm in kết quả tô màu
    public void printColoring() {
        System.out.println("Đỉnh : Màu");
        for(int u = 0; u < numVertices; u++) {
            System.out.println(u + " : " + result[u]);
        }
        int maxColor = Arrays.stream(result).max().getAsInt();
        System.out.println("Tổng số màu sử dụng: " + maxColor);
    }

    // Hàm chính để chạy ví dụ
    public static void main(String[] args) {
        /*
         Ví dụ Đồ thị:
             0
            / \
           1---2
           \   |
             3
        */
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        GraphColoringBacktracking gbc = new GraphColoringBacktracking(graph);
        int minColors = gbc.findMinColors();
        System.out.println("Số màu tối thiểu cần sử dụng: " + minColors);

        // Thực hiện tô màu với số màu tối thiểu
        if(gbc.colorGraph(minColors)) {
            gbc.printColoring();
        } else {
            System.out.println("Không tìm được giải pháp với " + minColors + " màu.");
        }
    }
}
