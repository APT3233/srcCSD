


class MergeSortIterative {

    // Phương thức để trộn hai nửa mảng
    public static void merge(int[] arr, int left, int mid, int right) {
        // Tính kích thước của hai mảng con
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Tạo các mảng tạm để chứa dữ liệu
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Sao chép dữ liệu vào các mảng tạm
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        // Kết hợp các mảng tạm lại với nhau
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        // Sao chép các phần tử còn lại của L[], nếu có
        while (i < n1) {
            arr[k++] = L[i++];
        }

        // Sao chép các phần tử còn lại của R[], nếu có
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    // Phương thức thực hiện Merge Sort không đệ quy
    public static void mergeSort(int[] arr) {
        int n = arr.length;

        // Bắt đầu từ đoạn con có kích thước 1 và tăng gấp đôi sau mỗi lần lặp
        for (int curr_size = 1; curr_size <= n - 1; curr_size = 2 * curr_size) {

            // Chọn điểm bắt đầu của từng đoạn con
            for (int left_start = 0; left_start < n - 1; left_start += 2 * curr_size) {

                // Chọn điểm giữa và điểm kết thúc của đoạn con
                int mid = Math.min(left_start + curr_size - 1, n - 1);
                int right_end = Math.min(left_start + 2 * curr_size - 1, n - 1);

                // Trộn hai đoạn con lại với nhau
                merge(arr, left_start, mid, right_end);
            }
        }
    }

    // Phương thức in mảng
    public static void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Mảng ban đầu:");
        printArray(arr);

        mergeSort(arr);

        System.out.println("Mảng sau khi sắp xếp (Merge Sort Không Đệ Quy):");
        printArray(arr);
    }
}

