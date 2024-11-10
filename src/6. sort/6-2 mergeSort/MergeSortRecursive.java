


class MergeSortRecursive {
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

    // Phương thức thực hiện Merge Sort đệ quy
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Tính chỉ số giữa
            int mid = left + (right - left) / 2;

            // Sắp xếp đệ quy nửa bên trái
            mergeSort(arr, left, mid);

            // Sắp xếp đệ quy nửa bên phải
            mergeSort(arr, mid + 1, right);

            // Trộn hai nửa đã sắp xếp
            merge(arr, left, mid, right);
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
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Mảng ban đầu:");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Mảng sau khi sắp xếp (Merge Sort Đệ Quy):");
        printArray(arr);
    }
}
