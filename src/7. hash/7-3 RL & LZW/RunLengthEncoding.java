


class RunLengthEncoding {

    // Hàm mã hóa RLE
    public static String encodeRLE(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();
        int count = 1;
        char prev = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == prev) {
                count++;
            } else {
                encoded.append(count).append(prev);
                prev = current;
                count = 1;
            }
        }

        // Thêm phần cuối cùng
        encoded.append(count).append(prev);

        return encoded.toString();
    }

    // Hàm giải mã RLE
    public static String decodeRLE(String encoded) {
        if (encoded == null || encoded.length() == 0) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();
        int i = 0;

        while (i < encoded.length()) {
            StringBuilder countBuilder = new StringBuilder();

            // Tìm số lượng
            while (i < encoded.length() && Character.isDigit(encoded.charAt(i))) {
                countBuilder.append(encoded.charAt(i));
                i++;
            }

            // Chuyển đổi số lượng từ chuỗi sang số nguyên
            int count = Integer.parseInt(countBuilder.toString());

            // Lấy ký tự sau số lượng
            if (i < encoded.length()) {
                char current = encoded.charAt(i);
                for (int j = 0; j < count; j++) {
                    decoded.append(current);
                }
                i++;
            }
        }

        return decoded.toString();
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        String original = "AAAABBBCCDAA";
        System.out.println("Chuỗi gốc: " + original);

        String encoded = encodeRLE(original);
        System.out.println("Chuỗi sau khi mã hóa RLE: " + encoded);

        String decoded = decodeRLE(encoded);
        System.out.println("Chuỗi sau khi giải mã RLE: " + decoded);
    }
}
