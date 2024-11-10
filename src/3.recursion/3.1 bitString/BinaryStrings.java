/**
 * @Binary_Strings
 * Bài toán liệt kê các chuỗi bit 
 * ver1: Sử dụng đệ quy

  * Tree_Diagram
                                   ""
                         /                   \
                       "0"                     "1"
                   /       \                  /   \
              "00"         "01"          "10"      "11"
             /    \       /   \         /   \      /   \
          "000" "001" "010"  "011"   "100" "101" "110" "111"

  */

  /**
   * @OUTPUT:
                000
                001
                010
                011
                100
                101
                110
                111
   */


public class BinaryStrings {
    
    public static void genrateBinaryStrings(String prefix, int n)
    {
        if(prefix.length() == n)
        {
            System.out.println(prefix);
            return;
        }

        genrateBinaryStrings(prefix + "0", n);
        genrateBinaryStrings(prefix + "1", n);

    }

    public static void main(String[] args) {
        int n = 3;
        genrateBinaryStrings("", n);
    }
}
