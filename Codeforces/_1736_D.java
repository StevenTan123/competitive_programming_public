import java.util.*;
import java.io.*;

public class _1736_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int count = 0;
            for (int i = 0; i < 2 * n; i++) {
                if (s.charAt(i) == '1') {
                    count++;
                }
            }
            if (count % 2 == 0) {
                ArrayList<Integer> rot = new ArrayList<Integer>();
                count = 0;
                for (int i = 0; i < 2 * n; i += 2) {
                    if (s.charAt(i) != s.charAt(i + 1)) {
                        int zero = i;
                        int one = i + 1;
                        if (s.charAt(i) == '1') {
                            zero = i + 1;
                            one = i;
                        }
                        if (count % 2 == 0) {
                            rot.add(zero);
                        } else {
                            rot.add(one);
                        }
                        count++;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(rot.size());
                sb.append(' ');
                for (int i = 0; i < rot.size(); i++) {
                    sb.append(rot.get(i) + 1);
                    sb.append(' ');
                }
                out.println(sb.toString());

                sb = new StringBuilder();
                for (int i = 0; i < 2 * n; i += 2) {
                    sb.append(i + 1);
                    sb.append(' ');
                }
                out.println(sb.toString());
            } else {
                out.println(-1);
            }
        }
        in.close();
        out.close();
    }
}
