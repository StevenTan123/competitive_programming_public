import java.util.*;

public class swarthmore_hex {
    public static void main(String[] args) {
        /*String message = "I love Swarthmore";
        StringBuilder encoding = new StringBuilder();
        for(int i = 0; i < message.length(); i++) {
            encoding.append(Integer.toString((int)message.charAt(i), 16));
        }
        System.out.println(encoding);*/
        String message = "49 20 6c 6f 76 65 20 53 77 61 72 74 68 6d 6f 72 65";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < message.length(); i += 3) {
            char c = (char)Integer.parseInt(message.substring(i, i + 2), 16);
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
