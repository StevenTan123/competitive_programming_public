import java.util.*;
import java.io.*;

public class back_in_black_chapter_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("back_in_black_chapter_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        
        for (int t = 1; t <= tt; t++) {
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();
            boolean[] S_arr = new boolean[N + 1];
            boolean[] need = new boolean[N + 1];
            for (int i = 0; i < N; i++) {
                S_arr[i + 1] = S.charAt(i) == '1' ? true : false;
            }
            long count = 0;
            for (int i = 1; i <= N; i++) {
                if (S_arr[i]) {
                    need[i] = true;
                    for (int j = i; j <= N; j += i) {
                        S_arr[j] = !S_arr[j];
                    }
                    count++;
                }
            }

            long total = 0;
            int Q = Integer.parseInt(in.readLine());
            boolean[] toggle = new boolean[N + 1];
            for (int i = 0; i < Q; i++) {
                int B = Integer.parseInt(in.readLine());
                toggle[B] = !toggle[B];
                if (need[B]) {
                    if (toggle[B]) {
                        count--;
                    } else {
                        count++;
                    }
                } else {
                    if (toggle[B]) {
                        count++;
                    } else {
                        count--;
                    }
                }
                total += count;
            }

            String res = "Case #" + t + ": ";
            out.println(res + total);
        }
        in.close();
        out.close();
    }
}
