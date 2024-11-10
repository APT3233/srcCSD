/**
 * @Permutation_Recursive (Liệt kê hoán vị)
 * 
 * Giả sử chúng ta muốn liệt kê tất cả các hoán vị của n phần tử.
 * @Note: sử dụng đệ quy
 * 
 * @Input: "123"
 * @Output:
            123
            132
            213
            231
            312
            321
 */


public class PermutationsRecursive {

    public static void permute(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            permute(ros, ans + ch);
        }
    }

    public static void main(String[] args) {
        String str = "123";
        permute(str, "");
    }
}
