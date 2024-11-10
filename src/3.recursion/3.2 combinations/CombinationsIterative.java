/**
 * @Combinations
 * 
 * @NOTE: Ko sử dụng đệ quy
 */

public class CombinationsIterative {

    public static void combine(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return;

        int[] indices = new int[k];
        // Khởi tạo chỉ số
        for (int i = 0; i < k; i++) {
            indices[i] = i;
        }

        while (true) {
            // In tổ hợp hiện tại
            for (int i = 0; i < k; i++) {
                System.out.print(arr[indices[i]] + " ");
            }
            System.out.println();

            // Tìm chỉ số để tăng
            int i;
            for (i = k - 1; i >= 0; i--) {
                if (indices[i] != i + n - k) {
                    break;
                }
            }

            // Nếu không còn chỉ số nào để tăng
            if (i < 0) {
                break;
            }

            // Tăng chỉ số tại vị trí i
            indices[i]++;

            // Cập nhật các chỉ số phía sau
            for (int j = i + 1; j < k; j++) {
                indices[j] = indices[j - 1] + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 2;
        combine(arr, k);
    }
}
