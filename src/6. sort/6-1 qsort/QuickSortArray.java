



class QuickSortArray {

    // Phương thức thực hiện Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Chia mảng và lấy vị trí pivot sau khi sắp xếp
            int pi = partition(arr, low, high);

            // Sắp xếp đệ quy phần bên trái pivot
            quickSort(arr, low, pi - 1);

            // Sắp xếp đệ quy phần bên phải pivot
            quickSort(arr, pi + 1, high);
        }
    }

    // Phương thức phân chia mảng
    public static int partition(int[] arr, int low, int high) {
        // Chọn pivot là phần tử cuối cùng
        int pivot = arr[high];
        int i = (low - 1); // Chỉ số nhỏ hơn pivot

        for (int j = low; j < high; j++) {
            // Nếu phần tử hiện tại nhỏ hơn hoặc bằng pivot
            if (arr[j] <= pivot) {
                i++;

                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Hoán đổi arr[i+1] và arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Phương thức in mảng
    public static void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println("Mảng ban đầu:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Mảng sau khi sắp xếp (Quick Sort):");
        printArray(arr);
    }
}
