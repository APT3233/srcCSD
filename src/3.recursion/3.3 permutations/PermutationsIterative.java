/**
 * @Permutation_Recursive (Liệt kê hoán vị)
 * 
 * Giả sử chúng ta muốn liệt kê tất cả các hoán vị của n phần tử.
 * @Note: KO sử dụng đệ quy
 * 
 */




public class PermutationsIterative {

    public static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapPermutation(char[] arr, int size, int n){
        if (size == 1){
            System.out.println(String.valueOf(arr));
            return;
        }

        for (int i = 0; i < size; i++){
            heapPermutation(arr, size - 1, n);

            // Nếu kích thước là chẵn, hoán đổi phần tử thứ i với phần tử cuối
            if (size % 2 == 0){
                swap(arr, i, size - 1);
            }
            // Nếu kích thước là lẻ, hoán đổi phần tử đầu với phần tử cuối
            else{
                swap(arr, 0, size - 1);
            }
        }
    }

    public static void main(String[] args) {
        String str = "123";
        int n = str.length();
        heapPermutation(str.toCharArray(), n, n);
    }
}
