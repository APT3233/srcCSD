


import java.util.*;

class LZWEncoding {

    // Hàm mã hóa LZW
    public static List<Integer> encodeLZW(String input) {
        // Tạo từ điển ban đầu với các ký tự đơn lẻ
        Map<String, Integer> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        String w = "";
        List<Integer> encodedOutput = new ArrayList<>();

        for (char c : input.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                encodedOutput.add(dictionary.get(w));
                // Thêm wc vào từ điển
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        // Thêm mã của chuỗi cuối cùng
        if (!w.equals("")) {
            encodedOutput.add(dictionary.get(w));
        }

        return encodedOutput;
    }

    // Hàm giải mã LZW
    public static String decodeLZW(List<Integer> compressed) {
        // Tạo từ điển ban đầu với các ký tự đơn lẻ
        Map<Integer, String> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        String w = "" + (char) (int) compressed.remove(0);
        StringBuilder decodedOutput = new StringBuilder(w);

        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k)) {
                entry = dictionary.get(k);
            } else if (k == dictSize) {
                entry = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Mã nén không hợp lệ.");
            }

            decodedOutput.append(entry);

            // Thêm w + entry.charAt(0) vào từ điển
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }

        return decodedOutput.toString();
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        String original = "TOBEORNOTTOBEORTOBEORNOT";
        System.out.println("Chuỗi gốc: " + original);

        List<Integer> encoded = encodeLZW(original);
        System.out.println("Chuỗi sau khi mã hóa LZW: " + encoded);

        String decoded = decodeLZW(new ArrayList<>(encoded));
        System.out.println("Chuỗi sau khi giải mã LZW: " + decoded);
    }
}
