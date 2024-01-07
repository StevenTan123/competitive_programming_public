import java.util.*;
import java.io.*;

public class back_in_black {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("back_in_black_chapter_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();
            boolean[] S_arr = new boolean[N + 1];
            for (int i = 0; i < N; i++) {
                S_arr[i + 1] = S.charAt(i) == '1' ? true : false;
            }
            int Q = Integer.parseInt(in.readLine());
            boolean[] toggle = new boolean[N + 1];
            for (int i = 0; i < Q; i++) {
                int B = Integer.parseInt(in.readLine());
                toggle[B] = !toggle[B];
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (S_arr[i]) {
                    for (int j = i; j <= N; j += i) {
                        S_arr[j] = !S_arr[j];
                    }
                    if (!toggle[i]) {
                        count++;
                    }
                } else {
                    if (toggle[i]) {
                        count++;
                    }
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + count);
        }
        in.close();
        out.close();
    }
}
