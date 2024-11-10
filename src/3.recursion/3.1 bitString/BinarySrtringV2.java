/**
 * @Binary_Strings
 * Bài toán liệt kê các chuỗi bit 
 * ver1: Ko sử dụng đệ quy
 */

 
public class BinarySrtringV2 {

    public static void generateBinaryStrings(int n) {
        int total = (int) Math.pow(2, n);
        for (int i = 0; i < total; i++) {
            String binary = Integer.toBinaryString(i);
            while (binary.length() < n) {
                binary = "0" + binary;
            }
            System.out.println(binary);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        generateBinaryStrings(n);
    }

}
